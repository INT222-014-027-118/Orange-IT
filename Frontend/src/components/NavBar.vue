<template>
    <div class="relative">
        <div class="fixed top-0 z-50 w-full bg-white shadow-sm select-none dark:bg-dark_secondary dark:text-gray-100">
            <div class="flex items-center justify-around px-2 mx-auto max-w-7xl h-12 sm:h-20 md:h-20 lg:h-20 sm:px-0">
                <div class="hidden sm:inline-flex lg:w-3/12">
                    <router-link to="/" class="flex items-end transition rounded-full cursor-pointer bg-gradient-to-r hover:from-secondary hover:to-primary hover:text-gray-100">
                        <img src="../assets/orange.svg" alt="orange_icon" class="h-11 md:h-12 ml-2" />
                        <span class="hidden font-bold tracking-tighter md:text-lg lg:text-2xl md:inline-flex md:w-24 lg:w-24 pb-1 -ml-4">range IT</span>
                    </router-link>
                </div>
                <Search class="relative w-full sm:w-6/12 lg:w-5/12 sm:mx-2"></Search>
                <div class="justify-end hidden text-xs md:text-sm lg:text-base sm:inline-flex lg:w-3/12 ">
                    <button class="flex items-center p-1 rounded-full" @click="$router.push('/compare')" :class="[this.$route.name === 'Compare' ? 'text-primary' : '']">
                        <div class="relative">
                            <span class="px-1 pt-1 material-icons"> compare_arrows </span>
                            <div class="absolute px-2 text-xs text-white bg-primary rounded-full -top-1 -right-3 md:-top-2 sm:-right-3">VS</div>
                        </div>
                        <span class="tracking-tight font-semibold">compare</span>
                    </button>

                    <button class="relative flex items-center p-1 rounded-full" @mouseenter="showCart = true" @click="$router.push('/cart')" :class="[this.$route.name === 'Cart' ? 'text-primary' : '']">
                        <div class="relative">
                            <span class="px-1 pt-1" :class="[this.$route.name === 'Cart' ? 'material-icons' : 'material-icons-outlined']"> shopping_cart </span>
                            <div class="absolute px-2 text-xs text-white bg-primary rounded-full -top-1 -right-3 md:-top-2 sm:-right-3">
                                {{ $store.getters.totalInCart == 0 ? "" : $store.getters.totalInCart }}
                            </div>
                        </div>
                        <span class="tracking-tight font-semibold">cart</span>
                        <div class="absolute top-10 left-0 z-20 pt-10 transform -translate-y-10 w-full" @mouseenter="showCart = true" @mouseleave="showCart = false">
                            <div
                                v-show="showCart"
                                class="w-64 py-2 absolute right-0 border border-gray-300 dark:border-gray-500 bg-gray-100 rounded-md shadow-xl text-gray-800 dark:text-gray-200 dark:bg-gray-800 opacity-100 hover:text-black"
                                :class="$store.getters.totalInCart == 0 ? 'hidden' : ''"
                            >
                                <div v-for="cart in $store.getters.cart" :key="cart.name" class="hover:text-primary">
                                    <span>{{ cart.name }}</span>
                                </div>
                            </div>
                        </div>
                    </button>

                    <div class="w-1 h-5 mx-2 my-auto border-r-2 border-gray-300 dark:border-gray-500 md:mx-3 lg:mx-4" />

                    <button v-if="!$store.getters.userinfo" class="flex items-center cursor-pointer" @click="$router.push('/login')" :class="[this.$route.name === 'Login' ? 'text-primary' : '']">
                        <span class="rounded-full text-3xl" :class="[this.$route.name === 'Login' ? 'material-icons' : 'material-icons-outlined']"> person </span>
                        <span class="block pl-2 pr-1 tracking-tight font-semibold">Login</span>
                    </button>
                    <button v-else :class="[this.$route.name === 'purchase' || this.$route.name === 'manageProfile' || this.$route.name === 'Address' ? 'text-primary rounded-full' : '']" class="relative">
                        <div class="flex items-center">
                            <span class="rounded-full text-3xl" :class="[this.$route.name === 'purchase' || this.$route.name === 'manageProfile' || this.$route.name === 'Address' ? 'material-icons' : 'material-icons-outlined']">
                                person
                            </span>
                            <span class="block pl-2 pr-1 tracking-tight font-semibold">{{ $store.getters.userinfo === null ? "" : $store.getters.userinfo.username }}</span>
                        </div>

                        <div class="absolute top-10 left-0 z-20 transform -translate-y-10 w-full" @mouseenter="showMenu = true" @mouseleave="showMenu = false">
                            <router-link
                                :to="{
                                    name: 'purchase',
                                    params: { purchaseDetail: 'purchase' },
                                }"
                                @click="showMenu = true"
                                class="block cursor-pointer h-10"
                            >
                            </router-link>
                            <div
                                v-show="showMenu"
                                class="w-52 py-3 absolute space-y-3 right-0 border border-gray-300 dark:border-gray-500 bg-gray-100 rounded-md shadow-xl text-gray-800 dark:text-gray-200 dark:bg-gray-800 opacity-100 hover:text-black"
                            >
                                <router-link
                                    :to="{
                                        name: 'manageProfile',
                                        params: { manage: 'account' },
                                    }"
                                    @click="showMenu = false"
                                    class="hover:text-primary flex font-semibold capitalize items-center"
                                    :class="[this.$route.name === 'manageProfile' ? 'md:text-primary hover:text-primaryfocus' : '']"
                                    ><span class="material-icons-outlined text-center w-14 md:w-16 ">manage_accounts</span>
                                    <span class="ml-1">my account</span>
                                </router-link>
                                <router-link
                                    :to="{
                                        name: 'purchase',
                                        params: { purchaseDetail: 'purchase' },
                                    }"
                                    @click="showMenu = false"
                                    class="hover:text-primary flex font-semibold capitalize items-center"
                                    :class="[this.$route.name === 'purchase' ? 'md:text-primary hover:text-primaryfocus' : '']"
                                    ><span class="material-icons-outlined text-center w-14 md:w-16 ">inventory</span>
                                    <span class="ml-1">purchase</span>
                                </router-link>
                                <hr class="dark:border-gray-500" />
                                <div class="flex items-center flex-wrap cursor-pointer select-none " @click="changeSetChangeMode()">
                                    <div class="flex justify-center w-14 md:w-16">
                                        <div class="rounded-full w-11 h-6 p-0.5 ring-2 " :class="[this.$store.getters.changeMode == true ? 'bg-neutral ring-primary' : 'bg-dark_secondary ring-gray-200']">
                                            <div
                                                class="rounded-full w-5 h-5 transform duration-300 ease-in-out flex items-center justify-center ring-1 "
                                                :class="[this.$store.getters.changeMode == true ? '-translate-x-0 bg-white text-primary ring-gray-300' : 'translate-x-5 bg-gray-700 text-blue-300 ring-gray-500']"
                                            >
                                                <span class="material-icons-round text-base">{{ this.$store.getters.changeMode == true ? "wb_sunny" : "dark_mode" }}</span>
                                            </div>
                                        </div>
                                    </div>
                                    <span class="font-semibold capitalize ml-1">{{ this.$store.getters.changeMode == true ? "light mode" : "dark mode" }}</span>
                                </div>
                                <hr class="dark:border-gray-500" />
                                <button @click="logout" class="hover:text-red-500 flex capitalize font-bold">
                                    <span class="material-icons-outlined text-center w-14 md:w-16 ">logout</span>
                                    <span class="ml-1">Logout</span>
                                </button>
                            </div>
                        </div>
                    </button>
                </div>
            </div>
        </div>

        <div class="fixed bottom-0 bg-white dark:bg-dark_secondary w-full h-16 sm:hidden text-xs tracking-tighter px-4 py-0.5 z-50 select-none">
            <div class="flex justify-around h-full items-center">
                <button class="flex flex-col items-center w-16 p-1 font-semibold" @click="$router.push('/')" :class="[this.$route.name === 'Home' ? 'bg-primary rounded-full text-white' : '']">
                    <!-- <div class="material-icons">local_mall</div> -->
                    <img v-show="this.$route.name === 'Home'" src="../assets/orange.svg" alt="orange_icon" class="max-h-6" />
                    <div v-show="this.$route.name !== 'Home'" :class="[this.$route.name === 'Home' ? 'material-icons text-primary' : 'material-icons-outlined']">home</div>
                    <span class="">home</span>
                </button>
                <button class="flex flex-col items-center w-16 p-1 font-semibold">
                    <div class="material-icons">
                        <!-- apps -->
                        <!-- manage_search -->
                        grid_view
                    </div>
                    <span class="">categories</span>
                </button>
                <button class="flex flex-col items-center w-16 p-1 font-semibold" @click="$router.push('/cart')" :class="[this.$route.name === 'Cart' ? 'bg-primary rounded-full text-white' : '']">
                    <div :class="[this.$route.name === 'Cart' ? 'material-icons' : 'material-icons-outlined']">shopping_cart</div>
                    <span class="">cart</span>
                </button>

                <button v-if="!$store.getters.userinfo" class="flex items-center cursor-pointer" @click="$router.push('/login')" :class="[this.$route.name === 'Login' ? 'bg-primary rounded-full text-white' : '']">
                    <div class="flex flex-col items-center w-16 p-1 font-semibold">
                        <span :class="[this.$route.name === 'Login' ? 'material-icons' : 'material-icons-outlined']"> person </span>
                        <span class="">login</span>
                    </div>
                </button>

                <router-link
                    :to="{
                        name: 'purchase',
                        params: { purchaseDetail: 'purchase' },
                    }"
                    v-else
                    @click="this.$store.commit('setShowAccountPage', true)"
                    :class="[this.$route.name === 'purchase' || this.$route.name === 'manageProfile' || this.$route.name === 'Address' ? 'bg-primary rounded-full text-white' : '']"
                >
                    <div class="flex flex-col items-center w-16 p-1 font-semibold">
                        <div :class="[this.$route.name === 'purchase' || this.$route.name === 'manageProfile' || this.$route.name === 'Address' ? 'material-icons' : 'material-icons-outlined']">
                            person
                        </div>
                        <span class="">account</span>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
// import Menu from "../components/Menu.vue";
import Search from "../components/Search.vue";
export default {
    props: {
        change: Boolean,
    },
    components: {
        // Menu,
        Search,
    },
    data() {
        return {
            showCart: false,
            showMenu: false,
        };
    },
    methods: {
        switchMode() {
            this.$emit("switch-mode");
        },
        changeSetChangeMode() {
            this.$store.commit("setChangeMode");
            if (this.$store.getters.changeMode == true) {
                localStorage.theme = "light";
                document.getElementById("light");
            } else {
                localStorage.theme = "dark";
                document.getElementById("dark");
            }
            this.mode();
        },
        mode() {
            if (localStorage.theme === "dark" || (!("theme" in localStorage) && window.matchMedia("(prefers-color-scheme: dark)").matches)) {
                this.$store.commit("setChangeMode", false);
                document.documentElement.classList.add("dark");
            } else {
                this.$store.commit("setChangeMode", true);
                document.documentElement.classList.remove("dark");
            }
        },
        logout() {
            if (window.confirm("Are you sure?")) {
                localStorage.removeItem("token");
                localStorage.removeItem("userId");
                this.$store.commit("SET_USERINFO", null);
                this.$router.push("/");
            }
        },
    },
};
</script>

<style></style>
