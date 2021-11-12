<template>
    <div class="relative" v-if="activeNavbar">
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
                            <div>
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    enable-background="new 0 0 24 24"
                                    height="24px"
                                    viewBox="0 0 24 24"
                                    width="24px"
                                    class="fill-current"
                                    :class="[this.$route.name === 'Compare' ? 'fill-current text-primary' : 'dark:text-white']"
                                >
                                    <g><rect fill="none" height="24" width="24" x="0" /></g>
                                    <g>
                                        <g>
                                            <g><path d="M9.01,14H2v2h7.01v3L13,15l-3.99-4V14z M14.99,13v-3H22V8h-7.01V5L11,9L14.99,13z" /></g>
                                        </g>
                                    </g>
                                </svg>
                            </div>
                            <div class="absolute px-1 text-xs text-white bg-primary rounded-full -top-2 -right-3 ring-2 ring-white dark:ring-dark_secondary"> {{this.$store.getters.countCompareProducts == 2?'VS':''}}</div>
                        </div>
                        <span class="tracking-tight font-semibold ml-1">compare</span>
                    </button>

                    <button
                        class="relative items-center p-1 rounded-full hidden sm:inline-flex"
                        @mouseenter="showCart = true"
                        @click="$router.push('/cart')"
                        :class="[this.$route.name === 'Cart' ? 'text-primary' : '']"
                    >
                        <div class="relative">
                            <svg v-if="this.$route.name === 'Cart'" xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px" class="fill-current text-primary">
                                <path d="M0 0h24v24H0z" fill="none" />
                                <path
                                    d="M7 18c-1.1 0-1.99.9-1.99 2S5.9 22 7 22s2-.9 2-2-.9-2-2-2zM1 2v2h2l3.6 7.59-1.35 2.45c-.16.28-.25.61-.25.96 0 1.1.9 2 2 2h12v-2H7.42c-.14 0-.25-.11-.25-.25l.03-.12.9-1.63h7.45c.75 0 1.41-.41 1.75-1.03l3.58-6.49c.08-.14.12-.31.12-.48 0-.55-.45-1-1-1H5.21l-.94-2H1zm16 16c-1.1 0-1.99.9-1.99 2s.89 2 1.99 2 2-.9 2-2-.9-2-2-2z"
                                />
                            </svg>
                            <svg v-else xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px" class="fill-current dark:text-white">
                                <path d="M0 0h24v24H0V0z" fill="none" />
                                <path
                                    d="M15.55 13c.75 0 1.41-.41 1.75-1.03l3.58-6.49c.37-.66-.11-1.48-.87-1.48H5.21l-.94-2H1v2h2l3.6 7.59-1.35 2.44C4.52 15.37 5.48 17 7 17h12v-2H7l1.1-2h7.45zM6.16 6h12.15l-2.76 5H8.53L6.16 6zM7 18c-1.1 0-1.99.9-1.99 2S5.9 22 7 22s2-.9 2-2-.9-2-2-2zm10 0c-1.1 0-1.99.9-1.99 2s.89 2 1.99 2 2-.9 2-2-.9-2-2-2z"
                                />
                            </svg>
                            <div class="absolute px-1 text-xs text-white bg-primary rounded-full -top-2 -right-2 ring-2 ring-white dark:ring-dark_secondary">
                                {{ $store.getters.totalInCart == 0 ? "" : $store.getters.totalInCart }}
                            </div>
                        </div>
                        <span class="tracking-tight font-semibold ml-1">cart</span>
                        <div class="absolute top-10 left-0 z-20 pt-10 transform -translate-y-10 w-full" @mouseenter="showCart = true" @mouseleave="showCart = false">
                            <div
                                v-show="showCart"
                                class="w-72 py-2 absolute right-0 border border-gray-300 dark:border-gray-500 bg-gray-100 rounded-md shadow-xl text-gray-800 dark:text-gray-200 dark:bg-gray-800 opacity-100 hover:text-black"
                                :class="$store.getters.totalInCart == 0 ? 'hidden' : ''"
                            >
                                <div v-for="cartItem in this.$store.getters.cart" :key="cartItem.id" class="flex justify-between items-center">
                                    <span class="w-3/6 text-left pl-2 py-1 whitespace-nowrap truncate text-sm">{{ cartItem.productCart.productName }}</span>
                                    <span class="w-1/6 text-left px-2 py-1 whitespace-nowrap truncate text-xs">{{ cartItem.colors.label }}</span>
                                    <span class="w-2/6 text-right pr-2 py-1 font-semibold overflow-hidden">{{ productPrice(cartItem.productCart.price * cartItem.quantity) }}</span>
                                </div>
                            </div>
                        </div>
                    </button>

                    <div class="w-1 h-5 mx-2 my-auto border-r-2 border-gray-300 dark:border-gray-500 md:mx-3 lg:mx-4 hidden sm:inline-block" />

                    <button class="items-center cursor-pointer relative hidden sm:inline-block" :class="[routerBTN.includes(this.$route.name) ? 'text-primary rounded-full' : '']">
                        <div class="flex items-center">
                            <svg v-if="routerBTN.includes(this.$route.name)" xmlns="http://www.w3.org/2000/svg" height="30px" viewBox="0 0 24 24" width="30px" class="fill-current text-primary">
                                <path d="M0 0h24v24H0z" fill="none" />
                                <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z" />
                            </svg>
                            <svg v-else xmlns="http://www.w3.org/2000/svg" height="30px" viewBox="0 0 24 24" width="30px" class="fill-current dark:text-white">
                                <path d="M0 0h24v24H0V0z" fill="none" />
                                <path
                                    d="M12 6c1.1 0 2 .9 2 2s-.9 2-2 2-2-.9-2-2 .9-2 2-2m0 10c2.7 0 5.8 1.29 6 2H6c.23-.72 3.31-2 6-2m0-12C9.79 4 8 5.79 8 8s1.79 4 4 4 4-1.79 4-4-1.79-4-4-4zm0 10c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"
                                />
                            </svg>
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
                                        $store.getters.userInfo === null ? $router.push('/login') : $router.push({ name: 'manageProfile', params: { manage: 'account' } });
                                    "
                                    class="hover:text-primary flex font-semibold capitalize items-center"
                                >
                                    <span class="material-icons-outlined text-center w-14 md:w-16 ">{{ $store.getters.userInfo === null ? "login" : "manage_accounts" }}</span>
                                    <span class="ml-1">{{ $store.getters.userInfo === null ? "login" : "my account" }}</span>
                                </div>
                                <div
                                    @click="
                                        menuUser = false;
                                        $store.getters.userInfo === null ? $router.push('/register') : $router.push({ name: 'purchase', params: { purchaseDetail: 'purchase' } });
                                    "
                                    class="hover:text-primary flex font-semibold capitalize items-center"
                                >
                                    <span class="material-icons-outlined text-center w-14 md:w-16 "> {{ $store.getters.userInfo === null ? "person_add" : "inventory" }}</span>
                                    <span class="ml-1">{{ $store.getters.userInfo === null ? "singin" : "purchase" }}</span>
                                </div>
                                <hr class="dark:border-gray-500" />
                                <div class="flex items-center flex-wrap cursor-pointer select-none " @click="changeSetChangeMode()">
                                    <div class="flex justify-center w-14 md:w-16">
                                        <div class="rounded-full w-9 h-5 p-0.5 ring-2 " :class="[this.$store.getters.changeMode == true ? 'bg-neutral ring-primary' : 'bg-dark_secondary ring-gray-200']">
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
                                    <button
                                        @click="
                                            logout();
                                            menuUser = false;
                                        "
                                        class="hover:text-red-500 flex capitalize font-bold w-full"
                                    >
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

        <div class="fixed bottom-0 bg-white dark:bg-dark_secondary w-full h-16 sm:hidden text-xs tracking-tighter px-1 py-0.5 z-50 select-none border-t border-primary dark:border-gray-600">
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
                    <div class="absolute px-1 text-xs text-white bg-primary rounded-full -top-1 right-3 ring-2 ring-white dark:ring-dark_secondary">
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
            if (window.confirm("Are you sure?")) {
                this.$store.dispatch("logout");
                this.$router.push("/");
            }
        },
        checkHistory() {
            return history.length;
        },
        removeItemCart(index) {
            this.$store.commit("removeCartItem", index);
        },
        productPrice(price) {
            return new Intl.NumberFormat("th-TH", {
                style: "currency",
                currency: "THB",
                minimumFractionDigits: 0,
                maximumFractionDigits: 2,
            }).format(price);
        },
    },
    computed:{
        activeNavbar(){
            return false
        }
    },
    created() {},
};
</script>

<style></style>
