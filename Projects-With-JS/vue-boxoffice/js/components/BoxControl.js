export const boxControl = {
    template: `
        <div>
            <v-row justify="center" class="main-control">
                <v-text-field label="조회일자" v-on:click="toggleDate" v-on:keydown="onKeydown" v-model="pickedDate"></v-text-field>
                <v-btn elevation="2" class="search-btn" v-on:click="handleSearchBtn">조회</v-btn>
            </v-row>

            <v-row v-if="showDate" justify="center" class="date-control">
                <v-date-picker v-model="pickedDate" v-on:click:date="handleClickDate"></v-date-picker>
            </v-row>
        </div>
        `,
    data() {
        return {
            showDate: false,
            pickedDate: this.$route.params.date
        }
    },
    methods: {
        toggleDate() {
            this.showDate=!this.showDate;
        },
        onKeydown(keyboardEvent) {
            if (keyboardEvent.key === 'Enter') {
                this.handleSearchBtn();
            }
        },
        handleClickDate(date) {
            this.showDate=false;
        },
        handleSearchBtn() {
            this.$emit('request', this.pickedDate); 
            this.handleClickDate();
        }
    }
}