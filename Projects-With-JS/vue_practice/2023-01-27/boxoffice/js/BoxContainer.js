import { secret } from "./secret.js";

export const boxContainer = {
    template: `
        <v-container fluidr>
            <v-text-field label="조회일자" v-on:click="toggleDate" v-model="pickedDate"></v-text-field>
            <v-row v-if="showDate" justify="center" class="date-control">
                <v-date-picker v-model="pickedDate" v-on:click:date="handleClickDate"></v-date-picker>
            </v-row>

            <div class="movie-info" v-if="!isLoading" v-for="item in kobisData">
                <span class="movie-info-cell">
                    <img v-bind:src="movieImgArr[item.rnum]">
                </span>

                <span class="movie-info-cell">
                    {{item.rank}}위
                </span>

                <span class="movie-info-cell">
                    &lt;{{item.movieNm}}&gt;
                </span>
                
                <span class="movie-info-cell">
                    {{item.audiCnt}}명
                </span>
                
                <span class="movie-info-cell">
                    {{item.openDt}} 개봉
                </span>
            </div>
        </v-container>
    `,
    data() {
        return {
            showDate: false,
            pickedDate: '',
            kobisData: [],
            movieImgArr: [],
            isLoading: true
        }
    },
    watch: {
        pickedDate(val, oldVal) {
            this.getKobisData(val);
        }
    },
    methods: {
        getKobisData(date) {
            this.isLoading = true;

            const dateWithoutDash = date.replace(/\-/g, '');
            axios.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',  {
                params: {
                    key: secret.kobis_key,
                    targetDt: dateWithoutDash
                }
            })
            .then((response) => {
                console.log('[KOBIS] 성공');
                this.kobisData = response.data.boxOfficeResult.dailyBoxOfficeList;

                this.kobisData.forEach(el => {
                    this.getKakaoImg(parseInt(el.rnum), el.movieNm);
                });
            })
            .catch(function (error) {
                console.log('[KOBIS] 실패', error);
            })
            .then(function () {
            });
        },
        getKakaoImg(idx, query) {
            axios.get('https://dapi.kakao.com/v2/search/image',  {
                headers: {
                    Authorization: 'KakaoAK ' + secret.kakao_key
                },
                params: {
                    query
                }
            })
            .then((response) => {
                console.log('[kakao] 성공');
                this.movieImgArr[idx] = response.data.documents[0].thumbnail_url;
                if (idx === 10) {
                    this.isLoading = false;
                }
            })
            .catch(function (error) {
                console.log('[kakao] 실패', error);
            })
            .then(function () {
            });
        },
        toggleDate() {
            this.showDate=!this.showDate;
        },
        handleClickDate(date) {
            this.showDate=false;
        }
    },
    created() {
        const now = new Date();
        const yesterday = new Date(now.setDate(now.getDate() - 1));

        this.pickedDate = (new Date(yesterday - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)
        this.getKobisData(this.pickedDate);
    }
}