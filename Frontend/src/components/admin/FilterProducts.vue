<template>
    <div class="relative select-none">
        <div>
            <button
                type="button"
                @click="showOption = !showOption"
                class="inline-flex justify-center w-full rounded-md border border-gray-300 px-4 py-2 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50"
            >
                Options
                <svg class="-mr-1 ml-2 h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                    <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
                </svg>
            </button>
        </div>
        <div v-show="showOption" class="absolute right-0 mt-2 w-56 shadow-lg rounded-md bg-white ring-1 ring-black ring-opacity-5 focus:outline-none">
            <div class="py-1 z-50">
                <a href="#" class="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-200" @click="clickTest">Account settings</a>
                <a href="#" class="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-200">Support</a>
                <a href="#" class="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-200">License</a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            showOption: false,
        };
    },
    methods: {
        clickTest() {
            console.log("gg");
        },
    },
};
</script>

<style></style>
