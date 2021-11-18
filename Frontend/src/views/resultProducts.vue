<template>
    <div>
        <div class="bg-black bg-opacity-30 absolute z-30 top-0 h-screen w-screen" :class="[showFilter ? 'hidden' : 'inline-block lg:hidden']"></div>
        <div class="container flex py-14 lg:px-0 lg:py-5 mx-auto lg:max-w-7xl">
            <div class="lg:w-3/12 relative">
                <div class="absolute left-0 z-30 w-screen lg:bg-transparent">
                    <div class="fixed left-2 top-16 sm:top-16 md:top-24 sm:mt-2 md:-mt-2 lg:hidden lg:mt-auto">
                        <button
                            class="p-2 bg-white dark:bg-dark_tertiary rounded-md shadow-md select-none cursor-pointer flex items-center border border-secondary"
                            @click="
                                showFilter = !showFilter;
                                showCat = true;
                            "
                        >
                            <span class="material-icons" :class="[showFilter ? '' : 'font-bold']">{{ showFilter ? "filter_list" : "close" }}</span>
                            <span class="px-2 capitalize" :class="[showFilter ? '' : 'font-bold']">{{ showFilter ? "filter" : "close" }}</span>
                        </button>
                    </div>

                    <div
                        class="fixed z-50 left-0 sm:left-2 sm:mt-1 md:mt-0 lg:left-auto px-3 pb-3 bg-white dark:bg-dark_tertiary rounded-md shadow-2xl lg:shadow-md select-none lg:w-3/12 w-screen sm:max-w-xs h-3/5 lg:h-auto overflow-y-visible overflow-hidden border lg:border-0 border-secondary scrollbar"
                        :class="[{ hidden: showFilter }, 'lg:block']"
                    >
                        <div class="border-b-2 dark:border-gray-500">
                            <div
                                class="flex items-center justify-between py-3 cursor-pointer"
                                @click="
                                    showCat = !showCat;
                                    loadFilterCategory();
                                "
                            >
                                <span class="font-semibold">Category</span> <span class="text-base font-bold">+</span>
                            </div>
                            <div class="p-1 mb-3 space-y-2 rounded-md bg-gray-50 dark:bg-dark_secondary" v-show="showCat">
                                <button
                                    id=""
                                    class="flex items-center w-full px-3 py-1 rounded-md hover:bg-gray-200 dark:hover:bg-dark_tertiary"
                                    :class="[catName == this.currentCategoryName ? 'bg-primary text-white hover:bg-primaryfocus' : '']"
                                    v-for="catName in categoriesList"
                                    :key="catName"
                                    @click="$router.push({ name: 'resultProducts', params: { currentCategoryName: catName } })"
                                >
                                    <!-- <input type="checkbox" class="mr-4 rounded-md form-checkbox checked:bg-primary" disabled v-model="selectCategory" :value="catName" /> {{ catName }} -->
                                    <div class="mr-4 rounded-md checked:bg-primary">{{ catName }}</div>
                                </button>
                            </div>
                        </div>
                        <!-- <div class="border-b-2 dark:border-gray-500">
                            <div class="flex items-center justify-between py-3 cursor-pointer" @click="showType = !showType">
                                <span class="font-semibold">Type</span> <span class="text-base font-bold">+</span>
                            </div>
                            <div class="p-1 mb-3 space-y-2 rounded-md bg-gray-50 dark:bg-dark_secondary" v-show="showType">
                                <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200 dark:hover:bg-dark_tertiary"
                                    ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="selectCategory" value="" />
                                </label>
                                <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                                    ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="selectCategory" value="" />
                                </label>
                                <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                                    ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="selectCategory" value="" />
                                </label>
                            </div>
                        </div> -->
                        <div class="border-b-2 dark:border-gray-500">
                            <div class="flex items-center justify-between py-3 cursor-pointer" @click="showPrice = !showPrice">
                                <span class="font-semibold">Price</span> <span class="text-base font-bold">+</span>
                            </div>
                            <div class="p-1 mb-3 rounded-md bg-gray-50 dark:bg-dark_secondary flex w-full" v-show="showPrice">
                                <label for="" class="flex flex-col items-center w-1/2 mx-1 font-thin">
                                    min
                                    <input type="number" class="px-1 pb-0.5 w-full h-8 ring-1 ring-inset ring-primary focus:outline-none rounded-md" />
                                </label>
                                <div class="self-end py-1">-</div>
                                <label for="" class="flex flex-col items-center w-1/2 mx-1 font-thin">
                                    max
                                    <input type="number" class="px-1 pb-0.5 w-full h-8 ring-1 ring-inset ring-primary focus:outline-none rounded-md" />
                                </label>

                                <button type="button" class="py-1 px-2 h-8 w-3/12 self-end bg-primary hover:bg-primaryfocus rounded-md text-white">OK</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="w-full lg:w-9/12 pr-2 pl-2 sm:pl-2 lg:pr-1">
                <div v-if="$store.getters.products.length !== 0" class="grid gap-1 md:gap-2 grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 col-span-4">
                    <router-link
                        :to="{
                            name: 'Product',
                            params: { productName: product.productName == '' ? 'Product name is not defined' : product.productName, productId: product.id },
                        }"
                        v-for="product in $store.getters.products"
                        :key="product.id"
                    >
                        <BaseProduct :product="product" @endload="endload()" />
                    </router-link>
                </div>
                <div v-else-if="!loading" class="animate-pulse text-2xl font-bold tracking-wide text-center">loading...</div>
                <div v-else class="text-2xl font-bold tracking-wide text-center">No result</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            showFilter: true,
            showCat: true,
            showType: false,
            showPrice: false,
            loading: false,
            selectCategory: [],

            categoriesList: {},
        };
    },
    props: {
        currentCategoryName: String,
    },

    methods: {
        scrollToTop() {
            window.scrollTo(0, 0);
        },
        endload() {
            this.loadFilterCategory();
            this.loading = true;
        },
        // getAllProduct() {
        //     this.$store.getters.products;
        // },
        loadFilterCategory() {
            // const allproduct = "all product";
            this.$store.dispatch("loadcategories");
            this.categoriesList = this.$store.getters.sortCategories;
            // this.categoriesList = [allproduct].concat(this.categoriesList);
            if (this.currentCategoryName === "all product") {
                this.selectCategory = this.categoriesList;
            }
        },
    },
    mounted() {
        // setTimeout(() => {
        //     this.categoriesList = this.$store.getters.sortCategories;
        // }, 100);
    },
    created() {
        this.loadFilterCategory();
        this.categoriesList = this.$store.getters.sortCategories;
        if (this.currentCategoryName !== "all product") {
            this.$store.dispatch("loadProductsByCategory", this.currentCategoryName);
            this.selectCategory.push(this.currentCategoryName);
        } else {
            this.$store.dispatch("loadProducts");
            this.selectCategory = this.categoriesList;
        }
    },
};
</script>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

/* Firefox */
input[type="number"] {
    -moz-appearance: textfield;
}

/* width */
.scrollbar::-webkit-scrollbar {
    width: 5px;
}

/* Track */
.scrollbar::-webkit-scrollbar-track {
    background: transparent;
    margin: 8px 8px;
}

/* Handle */
.scrollbar::-webkit-scrollbar-thumb {
    background: #ffb703;
    border-radius: 50px;
}

/* Handle on hover */
.scrollbar::-webkit-scrollbar-thumb:hover {
    background: #f88100;
    border-radius: 50px;
}

[type="checkbox"]:checked:hover,
[type="checkbox"]:checked:focus,
[type="radio"]:checked:hover,
[type="radio"]:checked:focus {
    border-color: transparent;
    background-color: #f35b04;
    cursor: pointer;
}
</style>
