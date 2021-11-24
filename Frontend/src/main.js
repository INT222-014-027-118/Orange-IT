import {
    createApp
} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/tailwind.css'
import VueSplide from '@splidejs/vue-splide';
import Carousel from "@/components/Carousel.vue";
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import Loading from '@/components/Loading.vue'
import BaseProduct from "@/components/BaseProduct.vue";
import LDmode from "@/components/LDmode.vue";


const vue = createApp(App)
vue.use(store)
vue.use(router)
vue.use(VueSplide)
vue.use(VueSweetalert2)
vue.component('Carousel', Carousel)
vue.component('Loading', Loading)
vue.component('BaseProduct', BaseProduct)
vue.component('LDmode', LDmode)
vue.mount('#app')