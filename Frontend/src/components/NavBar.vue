<template>
    <div class="relative">
        <div class="fixed top-0 z-50 w-full bg-white shadow-sm select-none dark:bg-dark_secondary dark:text-gray-100 border-b border-primary dark:border-gray-600">
            <div class="flex items-center justify-around px-2 mx-auto max-w-7xl h-14 sm:h-16 md:h-20 lg:h-20 sm:px-0">
                <div class="hidden sm:inline-flex lg:w-3/12">
                    <router-link to="/" class="flex items-end transition rounded-full cursor-pointer bg-gradient-to-r hover:from-secondary hover:to-primary hover:text-gray-100">
                        <img src="../assets/orange.svg" alt="orange_icon" class="h-11 md:h-12 ml-2" />
                        <span class="hidden font-bold tracking-tighter md:text-lg lg:text-2xl md:inline-flex md:w-24 lg:w-24 pb-1 -ml-4">range IT</span>
                    </router-link>
                </div>

                <button
                    class="flex items-center justify-center sm:hidden w-10 h-12 cursor-pointer mr-3 relative"
                    v-show="this.$route.name == 'Product' || !this.$store.getters.showAccountPage"
                    @click="
                        checkHistory();
                        this.$store.getters.showAccountPage ? (checkHistory() >= 3 ? $router.go(-1) : $router.push('/')) : '';
                        this.$store.commit('setShowAccountPage', true);
                    "
                >
                    <span class="material-icons">arrow_back</span>
                </button>

                <Search class="relative w-full sm:w-6/12 lg:w-5/12 sm:mx-2"></Search>

                <div class="justify-end flex text-xs md:text-sm lg:text-base lg:w-3/12 ">
                    <button class="items-center p-1 rounded-full hidden sm:inline-flex" @click="$router.push('/compare')" :class="[this.$route.name === 'Compare' ? 'text-primary' : '']">
                        <div class="relative">
                            <span class="px-1 pt-1 material-icons"> compare_arrows </span>
                            <div class="absolute px-2 text-xs text-white bg-primary rounded-full -top-1 -right-3 md:-top-2 sm:-right-3">VS</div>
                        </div>
                        <span class="tracking-tight font-semibold">compare</span>
                    </button>

                    <button
                        class="relative items-center p-1 rounded-full hidden sm:inline-flex"
                        @mouseenter="showCart = true"
                        @click="$router.push('/cart')"
                        :class="[this.$route.name === 'Cart' ? 'text-primary' : '']"
                    >
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

                    <div class="w-1 h-5 mx-2 my-auto border-r-2 border-gray-300 dark:border-gray-500 md:mx-3 lg:mx-4 hidden sm:inline-block" />

                    <button class="items-center cursor-pointer relative hidden sm:inline-block" :class="[routerBTN.includes(this.$route.name) ? 'text-primary rounded-full' : '']">
                        <div class="flex items-center">
                            <span class="rounded-full text-3xl" :class="[routerBTN.includes(this.$route.name) ? 'material-icons' : 'material-icons-outlined']">
                                person
                            </span>
                            <span class="block pr-1 tracking-tight font-semibold w-16 text-left truncate">{{ $store.getters.userInfo === null ? "Login" : $store.getters.userInfo.username }}</span>
                        </div>
                        <div class="absolute top-10 left-0 z-20 transform -translate-y-10 w-full" @mouseenter="menuUser = true" @mouseleave="menuUser = false">
                            <div
                                @click="
                                    menuUser = false;
                                    $store.getters.userInfo === null ? $router.push('/login') : $router.push({ name: 'purchase', params: { purchaseDetail: 'purchase' } });
                                "
                                class="block cursor-pointer h-10"
                            ></div>
                            <div
                                v-show="menuUser"
                                class="w-48 py-3 absolute space-y-3 right-0 border border-gray-300 dark:border-gray-500 bg-gray-100 rounded-md shadow-xl text-gray-800 dark:text-gray-200 dark:bg-gray-800 opacity-100 hover:text-black"
                            >
                                <div
                                    @click="
                                        menuUser = false;
                                        $store.getters.userInfo === null ? $router.push('/login') : $router.push({ name: 'purchase', params: { purchaseDetail: 'purchase' } });
                                    "
                                    class="hover:text-primary flex font-semibold capitalize items-center"
                                >
                                    <span class="material-icons-outlined text-center w-14 md:w-16 ">{{ $store.getters.userInfo === null ? "login" : "manage_accounts" }}</span>
                                    <span class="ml-1">{{ $store.getters.userInfo === null ? "login" : "my account" }}</span>
                                </div>
                                <div
                                    @click="
                                        menuUser = false;
                                        $store.getters.userInfo === null ? $router.push('/register') : $router.push({ name: 'manageProfile', params: { manage: 'account' } });
                                    "
                                    class="hover:text-primary flex font-semibold capitalize items-center"
                                >
                                    <span class="material-icons-outlined text-center w-14 md:w-16 "> {{ $store.getters.userInfo === null ? "person_add" : "inventory" }}</span>
                                    <span class="ml-1">{{ $store.getters.userInfo === null ? "singin" : "purchase" }}</span>
                                </div>
                                <hr class="dark:border-gray-500" />
                                <div class="flex items-center flex-wrap cursor-pointer select-none " @click="changeSetChangeMode()">
                                    <div class="flex justify-center w-14 md:w-16">
                                        <div
                                            class="rounded-full w-9 h-5 p-0.5 ring-2 "
                                            :class="[this.$store.getters.changeMode == true ? 'bg-neutral ring-primary' : 'bg-dark_secondary ring-gray-200']"
                                        >
                                            <div
                                                class="rounded-full w-4 h-4 transform duration-300 ease-in-out flex items-center justify-center ring-1 text-white"
                                                :class="[this.$store.getters.changeMode == true ? '-translate-x-0 bg-primary ring-primary' : 'translate-x-4 bg-blue-500 ring-blue-500']"
                                            >
                                                <span class="material-icons-round text-sm">{{ this.$store.getters.changeMode == true ? "wb_sunny" : "dark_mode" }}</span>
                                            </div>
                                        </div>
                                    </div>
                                    <span class="font-semibold capitalize ml-1">{{ this.$store.getters.changeMode == true ? "light mode" : "dark mode" }}</span>
                                </div>
                                <div v-if="$store.getters.userInfo">
                                    <hr class="dark:border-gray-500 mb-3" />
                                    <button @click="logout" class="hover:text-red-500 flex capitalize font-bold">
                                        <span class="material-icons-outlined text-center w-14 md:w-16 ">logout</span>
                                        <span class="ml-1">Logout</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </button>
                </div>

                <div class="flex items-center justify-center sm:hidden cursor-pointer ml-2">
                    <button class="material-icons px-2 py-4" @click="moreVert = !moreVert">more_vert</button>
                    <div
                        v-show="moreVert"
                        class="w-28 py-1 absolute top-12 right-2 border border-gray-300 dark:border-gray-500 bg-gray-100 rounded-md shadow-xl text-gray-800 dark:text-gray-200 dark:bg-gray-800 opacity-100 hover:text-black"
                    >
                        <button
                            @click="
                                $router.push('/');
                                moreVert = !moreVert;
                            "
                            class="hover:text-primary w-full py-2 font-semibold capitalize "
                        >
                            <p class="text-left ml-3">home</p>
                        </button>
                        <button
                            @click="
                                moreVert = !moreVert;
                                $store.getters.userInfo == null ? $router.push('/login') : $router.push({ name: 'purchase', params: { purchaseDetail: 'purchase' } });
                            "
                            class="hover:text-primary w-full py-2 font-semibold capitalize"
                        >
                            <p class="text-left ml-3">{{ $store.getters.userInfo == null ? "login" : "my account" }}</p>
                        </button>
                        <hr class="dark:border-gray-500" />
                        <button
                            @click="
                                moreVert = !moreVert;
                                changeSetChangeMode();
                            "
                            class="hover:text-primary w-full py-2 font-semibold capitalize"
                        >
                            <p class="text-left ml-3">{{ this.$store.getters.changeMode == true ? "dark mode" : "light mode" }}</p>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="fixed bottom-0 bg-white dark:bg-dark_secondary w-full h-16 sm:hidden text-xs tracking-tighter px-4 py-0.5 z-50 select-none border-t border-primary dark:border-gray-600">
            <div class="flex justify-around h-full items-center">
                <button class="flex flex-col items-center w-16 p-1 font-semibold" @click="$router.push('/')" :class="[this.$route.name === 'Home' ? 'text-primary' : '']">
                    <img v-show="this.$route.name === 'Home'" src="../assets/orange.svg" alt="orange_icon" class="max-h-6" />
                    <div v-show="this.$route.name !== 'Home'" :class="[this.$route.name === 'Home' ? 'material-icons text-primary' : 'material-icons-outlined']">home</div>
                    <span class="">home</span>
                </button>
                <button class="flex flex-col items-center w-16 p-1 font-semibold">
                    <div class="material-icons">
                        grid_view
                    </div>
                    <span class="">categories</span>
                </button>
                <button class="flex flex-col items-center w-16 p-1 font-semibold relative" @click="$router.push('/cart')" :class="[this.$route.name === 'Cart' ? 'text-primary' : '']">
                    <div :class="[this.$route.name === 'Cart' ? 'material-icons' : 'material-icons-outlined']">shopping_cart</div>
                    <span class="">cart</span>
                    <div class="absolute px-2 text-xs text-white bg-primary rounded-full -top-1 right-2 md:-top-2 sm:-right-3">
                        {{ $store.getters.totalInCart == 0 ? "" : $store.getters.totalInCart }}
                    </div>
                </button>
                <button
                    @click="
                        this.$store.commit('setShowAccountPage', true);
                        $store.getters.userInfo === null ? $router.push('/login') : $router.push({ name: 'purchase', params: { purchaseDetail: 'purchase' } });
                    "
                    :class="[routerBTN.includes(this.$route.name) ? 'text-primary' : '']"
                >
                    <div class="flex flex-col items-center w-16 p-1 font-semibold">
                        <div :class="[routerBTN.includes(this.$route.name) ? 'material-icons' : 'material-icons-outlined']">person</div>
                        <span class="">{{ $store.getters.userInfo === null ? "login" : "account" }}</span>
                    </div>
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import Search from "../components/Search.vue";
export default {
    props: {
        change: Boolean,
    },
    components: {
        Search,
    },
    data() {
        return {
            showCart: false,
            menuUser: false,
            moreVert: false,
            routerBTN: ["Login", "purchase", "manageProfile", "Address"],
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
            if (this.$store.dispatch("logout")) {
                this.$router.push("/");
            }
        },
        checkHistory() {
            return history.length;
        },
    },
    created() {},
};
</script>

<style></style>
