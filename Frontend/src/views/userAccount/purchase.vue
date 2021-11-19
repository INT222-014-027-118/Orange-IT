<template>
    <div class="">
        <h1 class="text-3xl font-semibold p-3 capitalize">purchase</h1>
        <div class="sticky top-14 sm:top-16 md:top-20 bg-white dark:bg-dark_tertiary shadow-sm">
            <hr class="dark:border-gray-500" />
            <div class="flex justify-between sm:justify-around overflow-x-auto">
                <div
                    v-for="tab in tabs"
                    :key="tab"
                    @click="selectTab = tab"
                    class="text-gray-600 text-center cursor-pointer whitespace-nowrap text-base px-4 py-4 md:py-4 md:px-6 hover:text-primary  focus:outline-none"
                    :class="[selectTab.id == tab.id ? 'text-primary border-b-2 font-medium border-primary' : 'dark:text-gray-50']"
                >
                    {{ tab.title }}
                </div>
            </div>
            <hr class="dark:border-gray-500" />
        </div>
        <div class="py-5" v-if="selectTab.id == 1">
            <div class="px-0 sm:px-2 mb-3" v-for="product in this.$store.getters.cart" :key="product.id">
                <div class="overflow-hidden mb-3">
                    <div class="flex flex-shrink-0 items-center">
                        <div class="self-start border border-gray-200 rounded-md w-14 h-14 sm:w-14 sm:h-14 md:w-20 md:h-20 flex flex-shrink-0 overflow-hidden">
                            <img :src="`${this.api}/image/get/${product.productCart.images[0].source}`" class="object-cover object-center w-full h-full" alt="Product image" />
                        </div>
                        <div class="ml-2 w-full">
                            <p class="font-bold">{{ product.productCart.productName }}</p>
                            <div class="flex justify-between flex-wrap">
                                <div>
                                    <p class="">color: {{ product.colors.label }}</p>
                                    <p class="">{{ passingPrice(product.productCart.price) }} x {{ product.quantity }}</p>
                                </div>
                                <p class="text-lg sm:text-2xl font-semibold self-end ml-auto pb-0.5">{{ passingPrice(product.productCart.price * product.quantity) }}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="flex justify-end border-b-2 pb-3 text-white dark:border-gray-500">
                    <button class="bg-secondary hover:bg-yellow-500 transition-colors px-3 py-2 mx-2 capitalize rounded-md whitespace-nowrap" @click="$router.push('/review')">review</button>
                    <button
                        class="btn px-6 py-2 ml-2 capitalize rounded-md whitespace-nowrap"
                        @click="
                            $router.push({
                                name: 'Product',
                                params: { productName: product.productCart.productName == '' ? 'Product name is not defined' : product.productCart.productName, productId: product.productCart.id },
                            })
                        "
                    >
                        buy again
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            tabs: [
                { id: 1, title: "all" },
                { id: 2, title: "to be delivered" },
                { id: 3, title: "to be receive" },
                { id: 4, title: "done" },
            ],
            selectTab: { id: 1, title: "all" },
            api: process.env.VUE_APP_API,
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
    },
};
</script>

<style></style>
