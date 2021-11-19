<template>
    <div>
        <side-menu />
        <div class="ml-14 sm:ml-32 md:ml-52 lg:ml-60">
            <router-view />
        </div>
    </div>
</template>

<script>
import SideMenu from "../../components/admin/SideMenu.vue";
export default {
    components: { SideMenu },
    comments: {
        SideMenu,
    },
    created() {
        // this.$store.dispatch("loadProducts");
    },
};
</script>

<style></style>
