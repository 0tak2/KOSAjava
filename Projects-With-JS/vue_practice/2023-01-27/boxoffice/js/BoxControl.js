export const boxControl = {
    template: `
        <div>
            <v-row justify="center" class="main-control">
                <v-text-field label="조회일자" v-on:click="toggleDate" v-model="dateFromParent"></v-text-field>
                <v-btn elevation="2" class="search-btn" v-on:click="handleSearchBtn">조회</v-btn>
            </v-row>

            <v-row v-if="showDate" justify="center" class="date-control">
                <v-date-picker v-model="dateFromParent" v-on:click:date="handleClickDate"></v-date-picker>
            </v-row>
        </div>
        `,
    data() {
        return {
            showDate: false,
            dateFromParent: this.pickedDate
        }
    },
    model: {
        prop: 'pickedDate',
        event: 'change'
    },
    props: {
        pickedDate: String
    },
    methods: {
        toggleDate() {
            this.showDate=!this.showDate;
        },
        handleClickDate(date) {
            // this.$emit('request'); // 바로 렌더링이 되지 않는 문제
            this.showDate=false;
        },
        handleSearchBtn() {
            this.$emit('request'); 
        }
    },
    watch: {
        dateFromParent(val, oldVal) {
            this.$emit('change', val);  
        }
    }
}