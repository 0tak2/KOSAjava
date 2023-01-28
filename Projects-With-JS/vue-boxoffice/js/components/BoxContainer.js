import { secret } from "../secret.js";
import { boxControl } from "./BoxControl.js";
import { boxTable } from "./BoxTable.js";

export const boxContainer = {
    components: {
        'box-control': boxControl,
        'box-table': boxTable
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
                v-model="pickedDate"
                v-on:request="onRequest">
            </box-control>

            <v-btn
                elevation="2"
                color="error"
                class="deleteSelected"
                v-on:click="deleteSelected"
            >
                선택 삭제
            </v-btn>

            <box-table
                v-bind:kobis-data="kobisData"
                v-bind:movie-img-data="movieImgArr"
                v-bind:selected="selected"
                v-on:deleteOne="deleteOne">
            </box-table>
        </v-container>
    `,
    data() {
        return {
            isError: false,
            errmsg: '',
            pickedDate: '',
            kobisData: [],
            movieImgArr: [],
            isLoading: true,
            selected: new Array(10).fill(false)
        }
    },
    methods: {
        deleteSelected() {
            this.selected.forEach((val, i) => {
                if (val) {
                    this.kobisData = this.kobisData.filter(item => parseInt(item.rnum)-1 !== i);
                    this.selected = new Array(10).fill(false)
                }
            })
        },
        deleteOne(idx) {
            this.kobisData = this.kobisData.filter(item => item.rnum !== idx);
        },
        updateKobisData(newData) {
            this.kobisData = newData
        },
        onRequest() {
            this.getKobisData(this.pickedDate);
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
                console.log('[KOBIS] 성공');
                this.kobisData = response.data.boxOfficeResult.dailyBoxOfficeList;

                this.isLoading = false; // 이미지 검색이 완료되기 전에 화면 표시

                this.kobisData.forEach(el => {
                    this.getKakaoImg(parseInt(el.rnum - 1), el.movieNm);
                });
            })
            .catch((error) => {
                console.log('[KOBIS] 실패', error);
                this.isError = true;
                this.errmsg = error;
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
                this.$set(this.movieImgArr, idx, response.data.documents[0].thumbnail_url);
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
    created() {
        const now = new Date();
        const yesterday = new Date(now.setDate(now.getDate() - 1));

        this.pickedDate = (new Date(yesterday - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)
        this.getKobisData(this.pickedDate);
    }
}