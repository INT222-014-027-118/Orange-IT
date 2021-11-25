<template>
    <div class="cursor-default ">
        <NavBar />
        <router-view v-if="!this.$store.getters.isAdmin" class="mt-14 sm:mt-16 md:mt-20 lg:mt-20 mb-16" :key="[$route.fullPath]" />
        <router-view v-else :key="[$route.fullPath]" />
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
        updateStatus() {
            this.activeNavbar = false;
        },
    },
    created() {
        if (this.$store.getters.isLogin) {
            this.$store.dispatch("loadUserData").then(() => {
                if (this.$store.getters.isAdmin) {
                    this.$store.dispatch("loadProducts");
                    this.$store.dispatch("loadUsers");
                } else {
                    this.$store.dispatch("loadProducts");
                    this.$store.dispatch("loadCartData");
                }
            });
        } else {
            this.$store.dispatch("loadProducts");
            this.$store.dispatch("loadCartData");
        }
    },
    mounted() {},
    updated() {},
};
</script>
<style>
* {
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.dark:root {
    color-scheme: dark;
}
.light:root {
    color-scheme: light;
}
</style>
