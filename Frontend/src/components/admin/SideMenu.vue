<template>
    <div class="w-60 fixed top-0 z-40 h-screen bg-white shadow-md select-none dark:bg-dark_secondary dark:text-gray-100 dark:border-gray-600">
        <div class="mt-3 flex justify-center">
            <router-link to="/admin" class="px-10 py-1 flex items-end transition rounded-md cursor-pointer bg-gradient-to-r hover:from-secondary hover:to-primary hover:text-gray-100" @click="clearMenu">
                <img src="../../assets/orange.svg" alt="orange_icon" class="h-11 md:h-12" />
                <span class="hidden font-bold tracking-tighter md:text-lg lg:text-2xl md:inline-flex md:w-24 lg:w-24 pb-1 -ml-4">range IT</span>
                <span class="hidden tracking-tighter md:text-xs lg:text-sm  md:inline-flex md:-ml-8 lg:-ml-3">Admin</span>
            </router-link>
        </div>
        <div class="mt-4">
            <side-menu-item v-for="(menu, index) in $store.getters.menuList" :key="menu" :label="menu.label" :link="menu.link" :active="menu.active" :icon="menu.icon" @chooseMenu="chooseMenu(index)" />
        </div>
        <button @click="logout()" class="hover:text-red-500 flex capitalize font-bold w-full">
            <span class="material-icons-outlined text-center w-14 md:w-16 ">logout</span>
            <span class="ml-1">Logout</span>
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
            menuList: [{ label: "Manage product", active: false, link: "manageProducts" }],
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
