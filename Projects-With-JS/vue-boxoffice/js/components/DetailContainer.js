import { secret } from "../secret.js";

export const detailContainer= {
    template: `
        <div class="detail-container">
                <v-card
                    elevation="15"
                    v-bind:loading="isLoading"
                >
                    <v-card-title class="text-h5 grey lighten-2">
                    상세정보
                    </v-card-title>

                    <v-card-text>
                        <div class="thumbnail-img-detail">
                            <img v-bind:src="this.movieImg">
                        </div>
                        <ul>
                            <li>표제: {{movieInfo.movieNm}}</li>
                            <li>영제: {{movieInfo.movieNmEn}}</li>
                            <li>제작연도: {{movieInfo.prdtYear}}</li>
                            <li>개봉연도: {{movieInfo.openDt}}</li>
                            <li>상영시간: {{movieInfo.showTm}}</li>
                            <li>감독:
                                <span v-for="director in movieInfo.directors">{{ director.peopleNm }}({{ director.peopleNmEn }}) </span>
                            </li>
                            <li>배우:
                                <span v-for="actor in movieInfo.actors">{{ actor.peopleNm }} </span>
                            </li>
                        </ul>
                    </v-card-text>

                    <v-divider></v-divider>

                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="primary"
                        text
                        v-on:click="handleCloseBtn"
                    >
                        닫기
                    </v-btn>
                    </v-card-actions>
                </v-card>
        </div>
    `,
    props: {
        movieCd: String
    },
    data() {
        return {
            movieInfo: {},
            movieImg: '',
            isLoading: true
        }
    },
    methods: {
        handleCloseBtn() {
            this.movieInfo = {};
            this.$emit('close');
        },
        getDetail(code) {
            axios.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json',  {
                params: {
                    key: secret.kobis_key,
                    movieCd: code
                }
            })
            .then((response) => {
                this.movieInfo = response.data.movieInfoResult.movieInfo;
                console.log('[KOBIS-DetailInfo] 성공', this.movieInfo);

                this.getKakaoImg(this.movieInfo.movieNm);
            })
            .catch((error) => {
                console.log('[KOBIS-DetailInfo] 실패', error);
                this.isError = true;
                this.errmsg = error;
            })
            .then(function () {
            });
        },
        getKakaoImg(query) {
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
                this.movieImg = response.data.documents[0].thumbnail_url;
                this.isLoading = false;
            })
            .catch((error) => {
                console.log('[kakao] 실패', error);
                this.isError = true;
                this.errmsg = error;
            })
            .then(function () {
            });
        }
    },
    watch: {
        movieCd(val, oldVal) {
            this.getDetail(val);
        }
    },
    created() {
        this.getDetail(this.movieCd);
    }
}