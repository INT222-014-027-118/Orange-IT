<template>
    <div class="w-14 sm:w-32 md:w-52 lg:w-60 fixed top-0 z-40 h-screen bg-white shadow-md select-none dark:bg-dark_secondary dark:text-gray-100 dark:border-gray-600">
        <div class="mt-3 flex justify-center">
            <router-link to="/admin/manage-orders" class="px-1 sm:px-10 py-1 flex items-end transition rounded-md cursor-pointer bg-gradient-to-r hover:from-secondary hover:to-primary hover:text-gray-100" @click="clearMenu">
                <img src="../../assets/orange.svg" alt="orange_icon" class="h-11 md:h-12" />
                <span class="hidden font-bold tracking-tighter md:text-lg lg:text-2xl md:inline-flex md:w-24 lg:w-24 pb-1 -ml-4">range IT</span>
                <span class="hidden tracking-tighter md:text-xs lg:text-sm md:inline-flex md:-ml-8 lg:-ml-3">Admin</span>
            </router-link>
        </div>
        <div class="mt-4">
            <side-menu-item v-for="(menu, index) in $store.getters.menuList" :key="menu" :label="menu.label" :link="menu.link" :active="menu.active" :icon="menu.icon" @chooseMenu="chooseMenu(index)" />
            <LDmode />
        </div>

        <button @click="logout()" class="hover:text-red-500 flex items-center justify-center sm:justify-start font-bold w-full py-2 my-2">
            <span class="material-icons-outlined sm:ml-3 md:ml-6">logout</span>
            <span class="ml-3 text-sm md:text-base lg:text-lg hidden sm:inline-block font-semibold leading-5">Logout</span>
        </button>
    </div>
</template>
<script>
import SideMenuItem from "./SideMenuItem.vue";

export default {
    components: {
        SideMenuItem,
    },
    data() {
        return {
            // menuList: [{ label: "Manage product", active: false, link: "manageProducts" }],
        };
    },
    methods: {
        chooseMenu(index) {
            this.$store.dispatch("chooseMenu", index);
        },
        clearMenu() {
            this.$store.commit("clearActiveMenu");
        },
        logout() {
            if (window.confirm("Are you sure?")) {
                this.$store.dispatch("logout");
                this.$router.push("/");
                window.location.reload();
            }
        },
    },
    created() {},
};
</script>

<style></style>
