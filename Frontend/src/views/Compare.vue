<template>
    <div class="mx-auto max-w-7xl sm:mt-5" v-if="this.$store.getters.countCompareProducts == 2">
        <div class="p-1 md:mt-0 md:p-2 capitalize">
            <div class="flex flex-wrap justify-between items-center">
                <h1 class="py-3 px-3 text-2xl font-semibold">
                    <span class="material-icons mr-3 text-xl font-bold py-2 px-3 bg-primary text-white rounded-full"> compare_arrows </span>compare products
                </h1>
                <button class="px-3 py-2 my-2 ml-auto btn capitalize" @click="clearProduct">clear product</button>
            </div>
            <!-- <div class="overflow-auto max-w-7xl"> -->
            <!-- component -->
            <div class="tscroll">
                <table class="rounded-md" style="width:99.8%;">
                    <thead>
                        <tr>
                            <th></th>
                            <th class="sm:whitespace-normal sm:w-2/5"></th>
                            <th class="sm:whitespace-normal sm:w-2/5"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="brand in brandName" :key="brand" class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm lg:mb-0">
                            <td class="table_content">
                                {{ brand[0] }}
                            </td>
                            <td class="table_content">
                                <p class="w-60 md:w-auto font-semibold text-center">{{ brand[1] }}</p>
                            </td>
                            <td class="table_content">
                                <p class="w-60 md:w-auto font-semibold text-center">{{ brand[2] }}</p>
                            </td>
                        </tr>
                        <tr v-for="name in productName" :key="name" class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm lg:mb-0">
                            <td class="table_content">
                                {{ name[0] }}
                            </td>
                            <td class="table_content">
                                <p class="w-60 md:w-auto font-semibold text-center">{{ name[1] }}</p>
                            </td>
                            <td class="table_content">
                                <p class="w-60 md:w-auto font-semibold text-center">{{ name[2] }}</p>
                            </td>
                        </tr>
                        <tr v-for="image in images" :key="image" class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm">
                            <td class="table_content">
                                <p class="whitespace-nowrap">{{ image[0] }}</p>
                            </td>
                            <td class="table_content">
                                <!-- <div class="min-w-max">
                                    <img src="http://20.205.201.136/orange-it/image/get/G913-1.png" class="object-cover object-center mx-auto w-60 select-none dark:bg-gray-400 " alt="Product image" />
                                </div>
                                <div>RAZER BLACKSHARK V2 PRO</div> -->
                                <div class="min-w-max self-start">
                                    <img
                                        :src="`${this.api}/image/get/${image[1].source}`"
                                        class="w-36 h-36 md:w-60 md:h-60 mx-auto object-contain object-center rounded-md bg-white ring ring-white"
                                        alt="Product image"
                                    />
                                </div>
                            </td>
                            <td class="table_content">
                                <div class="min-w-max self-start">
                                    <img
                                        :src="`${this.api}/image/get/${image[2].source}`"
                                        class="w-36 h-36 md:w-60 md:h-60 mx-auto object-contain object-center rounded-md bg-white ring ring-white"
                                        alt="Product image"
                                    />
                                </div>
                            </td>
                        </tr>
                        <tr v-for="price in price" :key="price" class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm">
                            <td class="table_content">
                                {{ price[0] }}
                            </td>
                            <td class="table_content">
                                {{ passingPrice(price[1]) }}
                            </td>
                            <td class="table_content">
                                {{ passingPrice(price[2]) }}
                            </td>
                        </tr>
                        <tr v-for="color in colors" :key="color" class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm">
                            <td class="table_content">
                                <p class="whitespace-nowrap">{{ color[0] }}</p>
                            </td>
                            <td class="table_content">
                                <div class="w-full flex flex-wrap">
                                    <div v-for="color in color[1]" :key="color" class="flex flex-col items-center">
                                        <div
                                            class="w-6 h-6 md:w-8 md:h-8 my-1 md:my-2 mx-2 rounded-full border-2"
                                            :style="{
                                                backgroundColor: `#${color.hexCode}`,
                                            }"
                                        ></div>
                                        <span class="text-xs"> {{ color.label }}</span>
                                    </div>
                                </div>
                            </td>
                            <td class="table_content">
                                <div class="w-full flex flex-wrap">
                                    <div v-for="color in color[2]" :key="color" class="flex flex-col items-center">
                                        <div
                                            class="w-6 h-6 md:w-8 md:h-8 my-1 md:my-2 mx-2 rounded-full border-2"
                                            :style="{
                                                backgroundColor: `#${color.hexCode}`,
                                            }"
                                        ></div>
                                        <span class="text-xs"> {{ color.label }}</span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr
                            class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm"
                            v-for="attribute in attributeTable"
                            :key="attribute.attributeTitle"
                        >
                            <td class="table_content">
                                {{ attribute.attributeTitle }}
                            </td>
                            <td class="table_content">
                                {{ attribute.product1 }}
                            </td>
                            <td class="table_content">
                                {{ attribute.product2 }}
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!-- </div> -->
            <!-- <div class="flex flex-col overflow-x-auto">
                <div class="flex p-1">
                    <div class="w-1/5"></div>
                    <div class="text-sm w-full">
                        <h1 class="text-base font-semibold sm:text-2xl lg:text-4xl">product 1</h1>
                    </div>
                    <div class="text-sm w-full">
                        <h1 class="text-base font-semibold sm:text-2xl lg:text-4xl">product 2</h1>
                    </div>
                </div>
                <div class="flex sm:p-1">
                    <div class="w-1/5">image</div>
                    <div class="w-2/5 p-0.5 sm:p-1 md:p-3 flex flex-col justify-center">
                        <img
                            src="https://kanexkane.com/wp-content/uploads/2020/04/kkblog-cover-review-logitech-g-pro-x-keyboard.jpg"
                            class="object-cover object-center select-none dark:bg-gray-400 "
                            alt="Product image"
                        />
                    </div>
                    <div class="w-2/5 p-0.5 sm:p-1 md:p-3 flex flex-col justify-center">
                        <img
                            src="https://kanexkane.com/wp-content/uploads/2020/04/kkblog-cover-review-logitech-g-pro-x-keyboard.jpg"
                            class="object-cover object-center select-none dark:bg-gray-400 "
                            alt="Product image"
                        />
                    </div>
                </div>
                <div class="flex sm:p-1">
                    <div class="w-1/5">color</div>
                    <div class="w-2/5 p-0.5 sm:p-1 md:p-3 flex flex-col justify-center">
                        <div class="w-full flex flex-wrap py-1">
                            <div v-for="color in colors" :key="color" class="flex flex-col items-center">
                                <div
                                    class="w-4 h-4 md:w-8 md:h-8 my-1 md:my-2 mx-2 rounded-full"
                                    :style="{
                                        backgroundColor: `#${color.hexCode}`,
                                    }"
                                ></div>
                                <span class="text-xs"> {{ color.label }}</span>
                            </div>
                        </div>
                    </div>
                    <div class="w-2/5 p-0.5 sm:p-1 md:p-3 flex flex-col justify-center">
                        <div class="w-full flex flex-wrap py-1">
                            <div v-for="color in colors" :key="color" class="flex flex-col items-center">
                                <div
                                    class="w-4 h-4 md:w-8 md:h-8 my-1 md:my-2 mx-2 rounded-full"
                                    :style="{
                                        backgroundColor: `#${color.hexCode}`,
                                    }"
                                ></div>
                                <span class="text-xs"> {{ color.label }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div> -->
        </div>
    </div>
    <div v-else class="h-96">
        <div class="max-w-lg mx-auto flex flex-col items-center justify-center h-full">
            <div class="material-icons text-5xl p-2 bg-primary text-white rounded-full mb-3 select-none">compare_arrows</div>
            <h1 class="font-semibold text-3xl mb-3 text-center">
                Please <span class="font-bold"> {{ 2 - this.$store.getters.countCompareProducts }}</span> add products to compare
            </h1>
            <p class="max-w-lg mx-auto text-lg text-center">
                You can add from pages of products in your interested
            </p>
        </div>
    </div>
</template>

<script>
import axios from "axios";
export default {
    components: {},
    data() {
        return {
            dumy: [],
            colors: [
                {
                    id: 1,
                    label: "Red",
                    hexCode: "ff0000",
                },
                {
                    id: 2,
                    label: "Black",
                    hexCode: "000000",
                },
                {
                    id: 6,
                    label: "Gray",
                    hexCode: "9AA0A8",
                },
            ],
            compareProducts: [],
            api: process.env.VUE_APP_API,
            brandName: {},
            productName: {},
            images: {},
            price: {},
            attributeHeaders: [],
            attributeTable: [],
        };
    },
    methods: {
        passingPrice(price) {
            return new Intl.NumberFormat("th-TH", {
                style: "currency",
                currency: "THB",
                minimumFractionDigits: 0,
                maximumFractionDigits: 2,
            }).format(price);
        },
        scrollToTop() {
            window.scrollTo(0, 0);
        },
        clearProduct() {
            this.$store.commit("resetCompareProducts");
        },
    },
    created() {
        this.scrollToTop();
        if (this.$store.getters.countCompareProducts == 2) {
            this.compareProducts = this.$store.getters.compareProducts;
            this.brandName = { brandName: ["brand", this.compareProducts[0].brandName, this.compareProducts[1].brandName] };
            this.productName = { productName: ["name", this.compareProducts[0].productName, this.compareProducts[1].productName] };
            this.images = { images: ["images", this.compareProducts[0].images[0], this.compareProducts[1].images[0]] };
            this.price = { price: ["price", this.compareProducts[0].price, this.compareProducts[1].price] };
            this.colors = { colors: ["colors", this.compareProducts[0].colors, this.compareProducts[1].colors] };
            axios
                .get(`${this.api}/attribute/list`)
                .then((response) => {
                    this.attributeHeaders = response.data;
                    this.attributeTable = response.data.map((attribute) => {
                        let attributeValue1 = this.compareProducts[0].productsHasAttributes.find((element) => {
                            return attribute.id == element.attributeId;
                        });
                        let attributeValue2 = this.compareProducts[1].productsHasAttributes.find((element) => {
                            return attribute.id == element.attributeId;
                        });

                        return {
                            attributeTitle: attribute.attribute,
                            product1: attributeValue1 == undefined ? null : attributeValue1.attribute_value,
                            product2: attributeValue2 == undefined ? null : attributeValue2.attribute_value,
                        };
                    });
                })
                .then(() => {
                    this.attributeTable = this.attributeTable.filter((element)=>{ return !(element.product1 == null && element.product2 == null)})
                });

            // let specProduct1 = this.compareProducts[1];
        }
    },
};
</script>

<style>
.table_content {
    @apply p-2 md:p-3 border border-b dark:border-gray-500 ring-0;
}
.tscroll {
    width: 100%;
    overflow-x: scroll;
}

.tscroll table td:first-child {
    position: sticky;
    left: 0;
    background-color: white;
}

.dark .tscroll table td:first-child {
    background-color: #22303c;
}
</style>
