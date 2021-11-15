<template>
    <div class="flex flex-shrink-0 items-center">
        <div class="self-start border border-gray-200 rounded-md w-14 h-14 sm:w-14 sm:h-14 md:w-20 md:h-20 flex flex-shrink-0 overflow-hidden">
            <img :src="`${this.api}/image/get/${product.images[0].source}`" class="object-cover object-center w-full h-full" alt="Product image" />
        </div>
        <div class="ml-2 w-full">
            <p class="text-xs">{{ product.productName }}</p>
            <div class="flex justify-between flex-wrap">
                
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
    created() {
        
    },
};
</script>

<style></style>
