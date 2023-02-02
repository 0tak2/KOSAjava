<template>
  <v-app>
    <v-app-bar app>
      <h1 class="title-appbar">일간 박스오피스 순위</h1>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>

    <v-footer app class="footer">
      <small>박스오피스</small>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
body {
  font-family: 'Noto Sans KR', sans-serif;
}

h1.title-appbar {
  font-weight: bold;
  font-size: large;
}

.footer {
    height: 40px;
    text-align: center;
}

/* vuetify 컴포넌트 관련 스타일 오버라이딩 */
.v-chip {
    margin: 2px;
}

/* 영화 상세 정보 스크롤 */
.v-card {
    display: flex !important;
    flex-direction: column;
}

.v-card__text {
    flex-grow: 1;
    overflow: auto;
}
</style>
