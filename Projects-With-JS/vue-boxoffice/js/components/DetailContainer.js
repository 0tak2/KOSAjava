import { secret } from "../secret.js";
import { detailContent } from "./DetailContent.js"; 

export const detailContainer = {
    template: `
        <div class="detail-container">
                <v-card
                    elevation="15"
                    v-bind:loading="isLoading"
                    height="600px"
                    class="scrollable"
                >
                    <v-card-title class="text-h5 grey lighten-2">
                    상세정보
                    </v-card-title>

                    <detail-content
                        v-show="!isLoading"
                        v-bind:movieInfo="movieInfo"
                    ></detail-content

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
    components: {
        'detail-content': detailContent
    },
    data() {
        return {
            movieInfo: {},
            isLoading: true
        }
    },
    props: {
        movieCd: String
    },
    methods: {
        handleCloseBtn() {
            this.$emit('close');
        },
        getKobisDetail(code) {
            this.isLoading = true;
            axios.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json',  {
                params: {
                    key: secret.kobis_key,
                    movieCd: code
                }
            })
            .then((response) => {
                let movieInfoTailed = response.data.movieInfoResult.movieInfo;
                const openDtRaw = movieInfoTailed.openDt;
                const year = openDtRaw.slice(0, 4);
                const months = openDtRaw.slice(4, 6);
                const day = openDtRaw.slice(6);
                movieInfoTailed.openDt = year + '년 ' + months + '월 ' + day + '일';

                this.movieInfo = movieInfoTailed;
                console.log('[KOBIS-DetailInfo] 성공');

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
                this.$set(this.movieInfo, 'imgurl', response.data.documents[0].thumbnail_url);
                console.log('[Kakao-DetailInfo] 성공');
                this.isLoading = false;
            })
            .catch((error) => {
                console.log('[Kakao-DetailInfo] 실패', error);
                this.isError = true;
                this.errmsg = error;
            })
            .then(function () {
            });
        },
    },
    watch: {
        movieCd(val, oldVal) {
            if (val !== '') {
                this.getKobisDetail(val);
            }
        }
    },
    created() {
        if (this.movieCd !== '') {
            this.getKobisDetail(this.movieCd);
        }
    }
}