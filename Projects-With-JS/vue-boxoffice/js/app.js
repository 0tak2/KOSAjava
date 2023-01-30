import { boxContainer } from './components/BoxContainer.js'

let router = new VueRouter({
    routes: [
        {
            path: '/',
            name: 'main',
            component: boxContainer
        },
        {
            path: '/:date',
            name: 'mainWithDate',
            component: boxContainer
        },
        {
            path: '/:date/:movieCd',
            name: 'mainWithDateAndCd',
            component: boxContainer
        },
    ]
});

new Vue({
    el: '#app',
    router,
    vuetify: new Vuetify(),
    components: {
        boxContainer
    },
    template: `
    <v-app>
        <v-app-bar app>
            박스오피스 - 일간순위
        </v-app-bar>

        <v-main>
            <router-view></router-view>
        </v-main>

        <v-footer app>
            <small>박스오피스</small>
        </v-footer>
    </v-app>
    `
});