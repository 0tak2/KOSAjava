import Vue from 'vue';
import VueRouter from 'vue-router';
import BoxContainer from '@/components/BoxContainer.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'main',
    component: BoxContainer,
  },
  {
    path: '/:date',
    name: 'mainWithDate',
    component: BoxContainer,
  },
  {
    path: '/:date/:movieCd',
    name: 'mainWithDateAndCd',
    component: BoxContainer,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
