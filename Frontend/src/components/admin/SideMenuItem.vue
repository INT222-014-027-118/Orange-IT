<template>
    <button class="text-left py-2 w-full shadow-sm flex items-center" :class="[active ? 'bg-yellow-400 text-white' : '']" @click="chooseMenu">
        <span class="ml-6 material-icons-round">{{icon}}</span>
        <span class="ml-3 text-lg font-semibold">{{ label }}</span>
    </button>
</template>
<script>
export default {
    data() {
        return {};
    },
    props: {
        label: String,
        link: String,
        icon:String,
        active: Boolean,
    },
    methods: {
        chooseMenu() {
            this.$router.push(`/admin/${this.link}`);
            this.$emit("chooseMenu");
        },
    },
};
</script>

<style></style>
