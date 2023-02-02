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
            <v-date-picker v-model="pickedDate" v-on:click:date="handleClickDate"></v-date-picker>
        </v-row>
    </div>
</template>

<script>
export default {
  data() {
    return {
      showDate: false,
      pickedDate: this.$route.params.date,
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
