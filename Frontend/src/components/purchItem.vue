<template>
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
</template>

<script>
export default {
    data() {
        return {
            api: process.env.VUE_APP_API,
        };
    },
    props: {
        product: Object,
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
