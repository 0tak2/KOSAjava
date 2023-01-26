import { secret } from "./secret.js";

export const boxContainer = {
    template: `
        <v-container fluidr>
            <v-text-field label="조회일자" v-on:click="toggleDate" v-model="pickedDate"></v-text-field>
            <v-row v-if="showDate" justify="center">
                <v-date-picker v-model="pickedDate" v-on:click:date="handleClickDate"></v-date-picker>
            </v-row>

            <div v-for="item in kobisData">
                {{item.mobieNm}}
            </div>
        </v-container>
    `,
    data() {
        return {
            showDate: false,
            pickedDate: '',
            kobisData: []
        }
    },
    watch: {
        pickedDate(val, oldVal) {
            this.getKobisData(val);
        }
    },
    methods: {
        getKobisData(date) {
            const dateWithoutDash = date.replace(/\-/g, '');
            axios.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',  {
                params: {
                    key: secret.kobis_key,
                    targetDt: dateWithoutDash
                }
            })
            .then(function (response) {
                // 성공 핸들링
                console.log(response.data.boxOfficeResult.dailyBoxOfficeList);
                this.kobisData = response.data.boxOfficeResult.dailyBoxOfficeList;
            })
            .catch(function (error) {
                // 에러 핸들링
                console.log(error);
            })
            .then(function () {
                // 항상 실행되는 영역
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