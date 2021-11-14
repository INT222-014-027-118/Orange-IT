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
            activeNavbar:true
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
    },
    created() {
        if (this.$store.getters.isLogin) {
            this.$store.dispatch("loadUserData");
            this.$store.dispatch("loadUserAddresses");
        }
        this.$store.dispatch("loadCartData");
        this.mode();
    },
    mounted() {
       if (this.$store.getters.isLogin) {
           if(this.$store.getters.isAdmin){
               this.activeNavbar = false
           }
        }
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
