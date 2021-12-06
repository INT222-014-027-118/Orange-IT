<template>
    <div class="flex items-center cursor-pointer select-none flex-nowrap" :class="[this.$store.getters.isAdmin ? 'mt-2 py-3 w-full shadow-sm' : '']" @click="changeSetChangeMode()">
        <div class="justify-center" :class="[this.$store.getters.isAdmin ? 'flex sm:ml-3 md:ml-6 w-full sm:w-auto' : routerBTN.includes(this.$route.name) ? 'w-14 md:w-16 flex' : 'w-14 md:w-16 hidden sm:inline-flex']">
            <div
                class="rounded-full ring-2"
                :class="[this.$store.getters.isAdmin ? '' : 'w-9 h-5 p-0.5 ring-2'] + [this.$store.getters.changeMode == true ? 'bg-neutral ring-primary' : 'bg-dark_secondary ring-gray-200']"
            >
                <div
                    class="rounded-full w-4 h-4 transform duration-300 ease-in-out flex items-center justify-center ring-1 text-white"
                    :class="
                        [this.$store.getters.isAdmin ? 'w-5 h-5 p-0.5' : ''] + [this.$store.getters.changeMode == true ? '-translate-x-0 bg-primary ring-primary' : 'translate-x-4 bg-blue-500 ring-blue-500']
                    "
                >
                    <span class="material-icons-round text-sm">{{ this.$store.getters.changeMode == true ? "wb_sunny" : "dark_mode" }}</span>
                </div>
            </div>
        </div>
        <span class="font-semibold capitalize" :class="[this.$store.getters.isAdmin ? 'text-sm md:text-base text-left lg:text-lg hidden sm:inline-block ml-3' : routerBTN.includes(this.$route.name) ? 'ml-1 whitespace-nowrap':'py-2 ml-3 sm:py-0 sm:ml-1']">
            {{ this.$store.getters.changeMode == true ? "light mode" : "dark mode" }}
        </span>
    </div>
</template>

<script>
export default {
    data() {
        return {
            routerBTN: ["Login", "purchase", "manageProfile", "Address"],
        };
    },
    methods: {
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
    },
    created() {
        this.mode();
    },
};
</script>

<style></style>
