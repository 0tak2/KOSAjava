export const boxTable= {
    template: `
        <v-simple-table>
                <thead>
                    <tr>
                        <th>선택</th>
                        <th>순위</th>
                        <th>포스터</th>
                        <th>표제</th>
                        <th>관람객수</th>
                        <th>개봉일</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <tr
                        v-for="item in kobisData"
                        v-bind:key="(item.rnum - 1)"
                    >
                        <td><input type="checkbox" v-model="selected[item.rnum - 1]"></td>
                        <td>{{ item.rank }}</td>
                        <td><img v-bind:src="movieImgData[item.rnum - 1]"></td>
                        <td>{{ item.movieNm }}</td>
                        <td>{{ Number(item.audiAcc).toLocaleString() }}명</td>
                        <td>{{ item.openDt }}</td>
                        <td>
                            <v-btn small depressed color="error" v-on:click="handleDeleteBtn(item.rnum)">삭제</v-btn>
                        </td>
                    </tr>
                </tbody>
        </v-simple-table>
    `,
    props: {
        kobisData: Array,
        movieImgData: Array,
        selected: Array
    },
    methods: {
        handleDeleteBtn(idx) {
            this.$emit('deleteOne', idx);
        }
    }
}