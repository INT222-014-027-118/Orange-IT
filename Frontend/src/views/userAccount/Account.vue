<template>
    <div class="flex p-1 py-3 mx-auto max-w-7xl" v-if="$store.getters.userInfo !== null">
        <div
            :class="[this.$store.getters.showAccountPage ? 'w-full sm:w-4/12 lg:w-3/12' : 'hidden sm:block']"
            class="sm:mx-1 w-full overflow-hidden sm:w-4/12 lg:w-3/12 top-20 px-3 py-3 md:px-4 bg-white dark:bg-dark_tertiary rounded-md shadow-md h-full"
        >
            <div class="">
                <div class="flex items-center">
                    <div class="self-start py-1">
                        <!-- <img
                        class="object-cover w-20 h-20 p-1 border-2 rounded-full border-primary md:w-24 md:h-24"
                        src="https://images.unsplash.com/photo-1502791451862-7bd8c1df43a7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=700&q=80"
                        alt="profile"
                    /> -->
                        <span class="w-14 h-14 text-5xl text-center border-2 rounded-full material-icons border-primary"> person </span>
                    </div>
                    <div class="w-full ml-2 overflow-hidden overflow-ellipsis">
                        <div class="md:flex md:flex-wrap md:items-center">
                            <h2 class="inline-block mb-2 lg:text-xl sm:mr-5 sm:mb-0 truncate">
                                {{ $store.getters.userInfo === null ? "" : $store.getters.userInfo.username }}
                            </h2>
                            <router-link
                                :to="{
                                    name: 'manageProfile',
                                    params: { manage: 'account' },
                                }"
                                @click="changetShowAccountPage"
                                class="block px-2 py-0.5 text-sm font-semibold text-center text-white bg-primary hover:bg-primaryfocus transition-colors rounded md:inline-block"
                                >Edit</router-link
                            >
                        </div>
                    </div>
                </div>
                <div class="px-1 py-3 border-b dark:border-gray-500">
                    <p class="text-base md:text-xl uppercase font-semibold">point : {{ $store.getters.userInfo === null ? "" : $store.getters.userInfo.points }}</p>
                </div>
            </div>

            <div class="">
                <div class="pt-5 pb-4 space-y-5">
                    <router-link
                        :to="{
                            name: 'manageProfile',
                            params: { manage: 'account' },
                        }"
                        @click="changetShowAccountPage"
                        class="hover:text-primary flex font-semibold capitalize truncate"
                        :class="[this.$route.name === 'manageProfile' ? 'md:text-primary hover:text-primaryfocus' : '']"
                        ><span class="material-icons-outlined text-center w-14 md:w-16 ">manage_accounts</span>
                        <span class="ml-1">my account</span>
                    </router-link>
                    <router-link
                        :to="{
                            name: 'Address',
                            params: { address: 'address' },
                        }"
                        @click="changetShowAccountPage"
                        class="hover:text-primary flex font-semibold capitalize items-center"
                        :class="[this.$route.name === 'Address' ? 'md:text-primary hover:text-primaryfocus' : '']"
                    >
                        <span class="material-icons-outlined text-center w-14 md:w-16 ">location_on</span>
                        <span class="ml-1">address</span>
                    </router-link>
                    <router-link
                        :to="{
                            name: 'purchase',
                            params: { purchaseDetail: 'purchase' },
                        }"
                        @click="changetShowAccountPage"
                        class="hover:text-primary flex font-semibold capitalize"
                        :class="[this.$route.name === 'purchase' ? 'md:text-primary hover:text-primaryfocus' : '']"
                        ><span class="material-icons-outlined text-center w-14 md:w-16 ">inventory</span>
                        <span class="ml-1">purchase</span>
                    </router-link>
                    <hr class="dark:border-gray-500" />
                    <LDmode />
                    <hr class="dark:border-gray-500" />
                    <button @click="logout" class="hover:text-red-500 flex capitalize font-bold w-full">
                        <span class="material-icons-outlined text-center w-14 md:w-16 ">logout</span>
                        <span class="ml-1">Logout</span>
                    </button>
                </div>
            </div>
        </div>

        <router-view
            class="sm:mx-1 top-20 px-3 py-3 bg-white dark:bg-dark_tertiary rounded-md shadow-md h-full"
            :class="[!this.$store.getters.showAccountPage ? 'block w-full sm:w-8/12 lg:w-9/12' : 'hidden sm:block sm:w-8/12 lg:w-9/12']"
        />
    </div>
</template>

<script>
export default {
    data() {
        return {};
    },
    methods: {
        changetShowAccountPage() {
            this.$store.commit("setShowAccountPage");
        },
        logout() {
            if (window.confirm("Are you sure?")) {
                this.$store.dispatch("logout");
                this.$router.push("/");
            }
        },
        scrollToTop() {
            window.scrollTo(0, 0);
        },
    },
    mounted() {
        this.scrollToTop();
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
