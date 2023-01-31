import { secret } from "../secret.js";
import { boxControl } from "./BoxControl.js";
import { boxTable } from "./BoxTable.js";
import { detailContainer } from "./DetailContainer.js";

export const boxContainer = {
    components: {
        'box-control': boxControl,
        'box-table': boxTable,
        'detail-container': detailContainer
    },
    template: `
        <v-container fluidr>
            <v-overlay v-bind:value="isLoading">
                <v-progress-circular
                    indeterminate
                    color="primary"
                    v-if="!isError"
                ></v-progress-circular>
                <div v-if="isError" class='err'>
                    오류가 발생했습니다. 새로 고침하여 다시 시도해보십시오.<br>
                    무시하고 이 창을 닫으려면 아래 버튼을 클릭하십시오.<br>
                    {{ errmsg }}
                    <br><br>

                    <v-btn
                        color="error"
                        v-on:click="() => {this.isError=false; this.isLoading=false;}"
                    >오류 무시</v-btn>
                </div>
            </v-overlay>

            <box-control
                v-on:request="onRequest">
            </box-control>

            <v-btn
                elevation="2"
                color="error"
                class="deleteSelected"
                v-on:click="onDeleteSelected"
            >
                선택 삭제
            </v-btn>

            <box-table
                v-bind:kobis-data="kobisData"
                v-model:selected="selected"
                v-on:deleteOne="onDeleteOne"
                v-on:showDetail="onShowDetail">
            </box-table>

            <v-dialog
                v-model="showDetail"
                max-width="300"
                v-on:click:outside="onDetailClose"
            >
                <detail-container
                    v-on:close="onDetailClose"
                    v-bind:movieCd="detailMovieCd"
                >
                </detail-container>
            </v-dialog>
        </v-container>
    `,
    data() {
        return {
            isError: false,
            errmsg: '',
            pickedDate: '',
            kobisData: [],
            isLoading: true,
            selected: [],
            showDetail: false,
            detailMovieCd: ''
        }
    },
    methods: {
        onDeleteSelected() {
            this.selected.forEach((val, i) => {
                if (val) {
                    this.kobisData = this.kobisData.filter(item => parseInt(item.rnum)-1 !== i);
                    this.selected = [];
                }
            });
        },
        onDeleteOne(idx) {
            this.kobisData = this.kobisData.filter(item => item.rnum !== idx);
        },
        onShowDetail(mvcode) {
            if (this.$route.params.movieCd !== this.pickedDate) {
                this.$router.push({ name: 'mainWithDateAndCd', params: { date: this.pickedDate, movieCd: mvcode } });
            }
        },
        updateKobisData(newData) {
            this.kobisData = newData;
        },
        onRequest(date) {
            this.pickedDate = date;
            if (this.$route.params.date !== this.pickedDate) {
                this.$router.push({ name: 'mainWithDate', params: { date: this.pickedDate } });
            }
        },
        getKobisData(date) {
            this.isLoading = true;

            const year = date.split('-')[0]
            const month = '0'.concat(date.split('-')[1]).slice(-2);
            const days = '0'.concat(date.split('-')[2]).slice(-2);
            const dateWithoutDash = [year, month, days].join('');
            
            axios.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',  {
                params: {
                    key: secret.kobis_key,
                    targetDt: dateWithoutDash
                }
            })
            .then((response) => {
                console.log('[KOBIS-Main] 성공');
                this.kobisData = response.data.boxOfficeResult.dailyBoxOfficeList;

                this.isLoading = false; // 이미지 검색이 완료되기 전에 화면 표시

                this.kobisData.forEach(el => {
                    this.getKakaoImg(el.movieNm, el);
                });
            })
            .catch((error) => {
                console.log('[KOBIS-Main] 실패', error);
                this.isError = true;
                this.errmsg = error;
            })
            .then(function () {
            });
        },
        getKakaoImg(query, el) {
            axios.get('https://dapi.kakao.com/v2/search/image',  {
                headers: {
                    Authorization: 'KakaoAK ' + secret.kakao_key
                },
                params: {
                    query
                }
            })
            .then((response) => {
                this.$set(el, 'imgurl', response.data.documents[0].thumbnail_url);
                console.log('[Kakao-Main] 성공');
                this.isDetailLoading = false;
            })
            .catch((error) => {
                console.log('[Kakao-Main] 실패', error);
                this.isError = true;
                this.errmsg = error;
            })
            .then(function () {
            });
        },
        onChangeDateParam(route) {
            if (route.params.date === undefined) {
                const now = new Date();
                const yesterday = new Date(now.setDate(now.getDate() - 1));
                this.pickedDate = (new Date(yesterday - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10);
                this.$router.push({ name: 'mainWithDate', params: { date: this.pickedDate } });
            } else {
                this.pickedDate = route.params.date;
            }
    
            this.getKobisData(this.pickedDate);
        },
        onChangeMovieCdParam(route) {
            if (route.params.movieCd === undefined) {
                this.detailMovieCd = '';
            } else {
                this.detailMovieCd = route.params.movieCd;
            }
        },
        onDetailClose() {
            this.detailMovieInfo = {};
            this.$router.push({ name: 'mainWithDate', params: { date: this.pickedDate } });
        }
    },
    watch: {
        $route(to, from) {
            if(to.params.date !== from.params.date)
                this.onChangeDateParam(to);
            
            if(to.params.movieCd !== from.params.movieCd)
                this.onChangeMovieCdParam(to);
        },
        detailMovieCd(val, oldVal) {
            if(val !== '') {
                this.showDetail = true;
            } else {
                this.showDetail = false;
            }
        }
    },
    created() {
        this.onChangeDateParam(this.$route);
        this.onChangeMovieCdParam(this.$route);
    }
}