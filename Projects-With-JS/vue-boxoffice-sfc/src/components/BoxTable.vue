<template>
    <div>
        <v-simple-table>
                <thead>
                    <tr>
                        <th>선택</th>
                        <th>순위</th>
                        <th>이미지</th>
                        <th>표제</th>
                        <th>관람객수</th>
                        <th>개봉일</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <tr
                        v-for="row in kobisData"
                        v-bind:key="(row.rnum - 1)"
                    >
                        <td><input type="checkbox" v-model="selected[row.rnum - 1]"></td>
                        <td>{{ row.rank }}</td>
                        <td><img v-bind:src="row.imgurl" v-bind:alt="row.movieNm"></td>
                        <td>
                            <span class="title-link"
                                v-on:click.stop="handleDetailBtn(row.movieCd)"
                            >
                                {{ row.movieNm }}
                            </span>
                        </td>
                        <td>{{ Number(row.audiAcc).toLocaleString() }}명</td>
                        <td>{{ row.openDt }}</td>
                        <td>
                            <v-btn small depressed color="error"
                                    v-on:click="handleDeleteBtn(row.rnum)"
                            >
                                    삭제
                            </v-btn>
                        </td>
                    </tr>
                </tbody>
        </v-simple-table>
        <div v-if="noData" class="no-data">
            입력한 조건에 해당하는 데이터가 없습니다.
        </div>
    </div>
</template>

<script>
/* eslint-disable vuejs-accessibility/form-control-has-label */
/* eslint-disable vue/no-mutating-props */
/* eslint-disable vuejs-accessibility/click-events-have-key-events */

export default {
  props: {
    kobisData: Array,
    selected: Array,
  },
  data() {
    return {
      noData: false,
      dialog: false,
    };
  },
  model: {
    prop: 'selected',
    event: 'select',
  },
  methods: {
    handleDeleteBtn(idx) {
      this.$emit('deleteOne', idx);
    },
    handleDetailBtn(movieCd) {
      this.$emit('showDetail', movieCd);
    },
  },
  updated() {
    if (this.kobisData.length === 0) {
      this.noData = true;
    } else {
      this.noData = false;
    }
  },
};
</script>

<style scoped>
.no-data {
    text-align: center;
    width: 100%;
    margin-top: 10px;
}

.title-link {
    text-decoration-line: underline;
    cursor: pointer;
}
</style>
