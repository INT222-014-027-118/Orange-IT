<template>
    <div class="cursor-default">
        <NavBar />
        <router-view :class="[activeNavbar ? 'mt-14 sm:mt-16 md:mt-20 lg:mt-20 mb-16' : '']" :key="[$route.fullPath]" />
        <!-- <Footer class="h-20 bg-gray-300 text-center w-full" v-if="this.$route.name !== 'Login' && this.$route.name !== 'Register'">footer</Footer> -->
    </div>
</template>

<script>
import NavBar from "./components/NavBar.vue";
// import Footer from './components/Footer.vue'
export default {
    components: {
        NavBar,
        // Footer
    },
    data() {
        return {
            changeMode: Boolean,
        };
    },
    methods: {
        mode() {
            if (localStorage.theme === "dark" || (!("theme" in localStorage) && window.matchMedia("(prefers-color-scheme: dark)").matches)) {
                this.$store.commit("setChangeMode", false);
                document.documentElement.classList.add("dark");
            } else {
                this.$store.commit("setChangeMode", true);
                document.documentElement.classList.remove("dark");
            }
        },
    },
    computed: {
        activeNavbar() {
            return this.$store.getters.isAdmin === null && this.$store.getters.isAdmin == false ? false : true;
        },
    },
    created() {
        if (this.$store.getters.isLogin) {
            this.$store.dispatch("loadUserData");
            this.$store.dispatch("loadUserAddresses");
        }
        this.$store.dispatch("loadCartData");
        // console.log(this.$store.getters.activeNavBar);
        // if (localStorage.getItem("cart")) {
        //     let products = [];
        //     products = JSON.parse(localStorage.getItem("cart"));
        //     console.log(JSON.parse(localStorage.getItem("cart")));
        //     console.log(this.$store.getters.cart);
        //     this.$store.commit("fullCartItem", products);
        // }
        this.mode();
    },
};
</script>
<style>
.dark:root {
    color-scheme: dark;
}
.light:root {
    color-scheme: light;
}
</style>
