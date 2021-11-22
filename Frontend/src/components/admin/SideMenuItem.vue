<template>
    <button class="py-2 w-full shadow-sm flex justify-center sm:justify-start items-center " :class="[active ? 'bg-primary text-white' : 'hover:text-primary']" @click="chooseMenu">
        <span class="sm:ml-3 md:ml-6 material-icons-round">{{ icon }}</span>
        <span class="ml-3 text-sm md:text-base lg:text-lg hidden sm:inline-block font-semibold leading-5">{{ label }}</span>
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
        icon: String,
        active: Boolean,
    },
    methods: {
        chooseMenu() {
            // this.$router.push(`/admin/${this.link}`);
            this.$router.push(`/admin/${this.link}`);
            this.$emit("chooseMenu");
        },
    },
};
</script>

<style></style>
