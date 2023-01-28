import { boxContainer } from './components/BoxContainer.js'

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    components: {
        'box-container': boxContainer
    },
    template: `
    <v-app>
        <v-app-bar app>
            박스오피스 - 일간순위
        </v-app-bar>

        <v-main>
            <box-container></box-container>
        </v-main>

        <v-footer app>
            <small>박스오피스</small>
        </v-footer>
    </v-app>
    `
});