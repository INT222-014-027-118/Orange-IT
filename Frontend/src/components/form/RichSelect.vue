<template>
    <div class="relative">
        <div class="inline-block relative w-full">
            <button type="button" class="w-full flex text-left justify-between items-center input-theme" value="Option A" @click="showOP">
                <span class="block truncate" :class="[choosed === '' ? 'text-gray-300 dark:text-gray-500' : ' text-black dark:text-gray-100']">{{ choosed == "" ? "Please select or input" : choosed }}</span
                ><svg v-if="!showOption" fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="fill-current flex-shrink-0 ml-1 h-4 w-4 text-gray-600 dark:text-gray-300">
                    <path
                        clip-rule="evenodd"
                        fill-rule="evenodd"
                        d="M10 3a1 1 0 01.707.293l3 3a1 1 0 01-1.414 1.414L10 5.414 7.707 7.707a1 1 0 01-1.414-1.414l3-3A1 1 0 0110 3zm-3.707 9.293a1 1 0 011.414 0L10 14.586l2.293-2.293a1 1 0 011.414 1.414l-3 3a1 1 0 01-1.414 0l-3-3a1 1 0 010-1.414z"
                    ></path>
                </svg>
                <svg v-else xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="fill-current flex-shrink-0 ml-1 h-4 w-4 text-gray-600 dark:text-gray-300">
                    <path
                        d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zm121.6 313.1c4.7 4.7 4.7 12.3 0 17L338 377.6c-4.7 4.7-12.3 4.7-17 0L256 312l-65.1 65.6c-4.7 4.7-12.3 4.7-17 0L134.4 338c-4.7-4.7-4.7-12.3 0-17l65.6-65-65.6-65.1c-4.7-4.7-4.7-12.3 0-17l39.6-39.6c4.7-4.7 12.3-4.7 17 0l65 65.7 65.1-65.6c4.7-4.7 12.3-4.7 17 0l39.6 39.6c4.7 4.7 4.7 12.3 0 17L312 256l65.6 65.1z"
                    />
                </svg>
            </button>
        </div>
        <div
            class="absolute top-12 w-full z-40 -mt-1 border rounded-md border-primary shadow-lg transition ease-in duration-75 opacity-100 bg-white dark:bg-gray-700"
            :class="[showOption ? 'visible' : 'invisible']"
        >
            <div class="w-full p-2 placeholder-gray-400 flex items-center">
                <input placeholder="Search..." type="search" maxlength="40" class="inline w-full px-3 py-2 h-9 input-theme" v-model="text" ref="inputText" @keydown.enter="gogo" />
                <div class="" v-show="showAdd">
                    <button type="button" class="bg-green-600 px-2 py-2 ml-2 h-9 rounded-md text-white text-sm" @click="AddOption">Add</button>
                </div>
            </div>
            <ul class="overflow-auto rounded-sm" style="max-height: 200px;">
                <li
                    data-type="option"
                    class="cursor-pointer "
                    v-for="(attribute, index) in search"
                    :key="attribute"
                    :class="[attribute.active ? 'font-semibold bg-primary text-white hover:bg-primaryfocus ' : 'hover:bg-gray-200 dark:hover:bg-dark_tertiary']"
                >
                    <div class="flex justify-between items-center px-3 py-2" @click="selectToNa(index)" v-if="attribute.show">
                        <span class="truncate block ">{{ attribute.attribute }}</span>
                        <svg fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="fill-current h-4 w-4" :class="{ hidden: !attribute.active }">
                            <polygon points="0 11 2 9 7 14 18 3 20 5 7 18"></polygon>
                        </svg>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    props: {},
    data() {
        return {
            showOption: false,
            showAdd: false,
            attributes: [],
            choosed: "",
            text: "",
        };
    },
    methods: {
        selectToNa(index) {
            for (let i = 0; i < this.search.length; i++) {
                if (i == index) {
                    this.search[i].active = true;
                    this.choosed = this.search[i].attribute;
                    this.$emit("selectAttribute", this.search[i]);
                } else {
                    this.search[i].active = false;
                }
            }
            this.showOption = false;
        },
        showOP() {
            this.$store.dispatch("loadAttirbute");
            this.showOption = !this.showOption;
            if (this.attributes.length == 0) {
                this.attributes = this.$store.getters.attributes;
            } else if (this.attributes.length !== this.$store.getters.attributes.length) {
                this.attributes = this.$store.getters.attributes;
            }
            setTimeout(() => {
                this.$refs.inputText.focus();
            }, 500);
        },
        AddOption() {
            if (!this.text == "" && !this.text == this.attributes.filter((t) => t.attribute.toLowerCase().includes(this.text.toLowerCase()))) {
                let newattribute = { id: 1, attribute: this.text };
                this.$store.dispatch("addAttribute", newattribute);
                setTimeout(() => {
                    this.attributes = this.$store.getters.attributes;
                }, 500);
            }
        },
    },
    computed: {
        search() {
            this.attributes.map((t) => {
                if (!t.attribute.toLowerCase().includes(this.text.toLowerCase())) {
                    t.show = false;
                    this.showAdd = true;
                } else {
                    t.show = true;
                    this.showAdd = false;
                }
            });
            return this.attributes;
        },
    },
};
</script>

<style></style>
