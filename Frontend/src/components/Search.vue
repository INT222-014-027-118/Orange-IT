<template>
    <div class="flex items-center rounded-3xl relative">
        <input
            type="search"
            v-model="text_Search"
            class="mx-0.5 pt-1 pb-1 pl-10 pr-5 md:pl-10 rounded-2xl focus:outline-none w-full ring-2 ring-primary  focus:bg-gray-50 dark:focus:bg-dark_secondary  bg-gray-100 dark:bg-dark_secondary"
            @keypress.enter="gogo"
        />
        <button class="rounded-3xl px-3 absolute">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px" class="fill-current text-primary">
                <path d="M0 0h24v24H0V0z" fill="none" />
                <path
                    d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"
                />
            </svg>
        </button>
        <div class="absolute top-10 w-full bg-red-100 rounded-lg" v-show="text_Search != ''">
            <div class="p-2">
                <p>text</p>
                <p>text</p>
                <p>text</p>
                <p>text</p>
                <p>text</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            text_Search: "",
        };
    },
    methods: {
        gogo() {
            console.log(this.text_Search);
        },
    },
    created() {},
};
</script>

<style>
input[type="search"]::-webkit-search-cancel-button {
    -webkit-appearance: none;
    height: 1em;
    width: 1em;
    border-radius: 50em;
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='rgb(236, 105, 7)'  viewBox='0 0 512 512'%3E%3Cpath d='M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zm121.6 313.1c4.7 4.7 4.7 12.3 0 17L338 377.6c-4.7 4.7-12.3 4.7-17 0L256 312l-65.1 65.6c-4.7 4.7-12.3 4.7-17 0L134.4 338c-4.7-4.7-4.7-12.3 0-17l65.6-65-65.6-65.1c-4.7-4.7-4.7-12.3 0-17l39.6-39.6c4.7-4.7 12.3-4.7 17 0l65 65.7 65.1-65.6c4.7-4.7 12.3-4.7 17 0l39.6 39.6c4.7 4.7 4.7 12.3 0 17L312 256l65.6 65.1z'/%3E%3C/svg%3E");
    background-size: contain;
    opacity: 0;
    pointer-events: none;
}

input[type="search"]:focus::-webkit-search-cancel-button {
    opacity: 1;
    pointer-events: all;
}

input[type="search"].dark::-webkit-search-cancel-button {
    filter: invert(1);
}
</style>
