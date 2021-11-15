<template>
    <div>
        <side-menu />
        <div class="ml-60">
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
};
</script>

<style></style>
