<template>
    <div>
        <v-row justify="center" class="main-control">
            <v-text-field label="조회일자"
                        v-on:click="toggleDate"
                        v-on:keydown="onKeydown"
                        v-model="pickedDate"></v-text-field>
            <v-btn elevation="2" class="search-btn" v-on:click="handleSearchBtn">조회</v-btn>
        </v-row>

        <v-row v-if="showDate" justify="center" class="date-control">
            <v-date-picker v-model="safeDate" v-on:click:date="handleClickDate"></v-date-picker>
        </v-row>
    </div>
</template>

<script>
/* eslint-disable no-new */

export default {
  data() {
    return {
      showDate: false,
      pickedDate: this.$route.params.date,
      safeDate: this.$route.params.date,
    };
  },
  methods: {
    toggleDate() {
      this.showDate = !this.showDate;
    },
    onKeydown(keyboardEvent) {
      if (keyboardEvent.key === 'Enter') {
        this.handleSearchBtn();
      }
    },
    handleClickDate() {
      this.showDate = false;
    },
    handleSearchBtn() {
      this.$emit('request', this.pickedDate);
      this.handleClickDate();
    },
  },
  watch: {
    pickedDate(val) {
      let inputDate = val;
      if (inputDate.search(/2\d\d\d-[0-1]*\d-[0-3]*\d/g) > -1) { // 날짜 유효성 검사
        const year = inputDate.split('-')[0];
        let month = inputDate.split('-')[1];
        let day = inputDate.split('-')[2];

        if (Number(month) < 1 || Number(month) > 12) {
          return;
        }
        if (Number(day) < 1 || Number(day) > 31) {
          return;
        }

        if (month.length === 1) { // 자릿수 확인
          month = `0${month}`;
          inputDate = `${year}-${month}-${day}`;
        }
        if (day.length === 1) {
          day = `0${day}`;
          inputDate = `${year}-${month}-${day}`;
        }

        this.safeDate = inputDate; // 검사 성공시 날짜 컨트롤에 바인딩
      }
    },
    safeDate(val) {
      this.pickedDate = val;
    },
  },
};
</script>

<style scoped>
.date-control {
    position: absolute;
    z-index: 8;
    top: 85px;
}

.main-control {
    margin-top: 30px;
    margin-bottom: 30px;
}

.search-btn {
    margin-top: 10px;
    margin-left: 10px;
}
</style>
