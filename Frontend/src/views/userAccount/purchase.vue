<template>
    <div class="">
        <h1 class="text-3xl font-semibold p-3 capitalize">purchase</h1>
        <div class="sticky top-14 sm:top-16 md:top-20 bg-white dark:bg-dark_tertiary">
            <hr />
            <div class="flex justify-between sm:justify-around overflow-x-auto  ">
                <div
                    v-for="tab in tabs"
                    :key="tab"
                    @click="selectTab = tab"
                    class="text-gray-600 text-center cursor-pointer whitespace-nowrap text-base px-4 py-2 md:py-4 md:px-6 hover:text-primary focus:outline-none"
                    :class="[selectTab.id == tab.id ? 'text-primary border-b-2 font-medium border-primary' : '']"
                >
                    {{ tab.title }}
                </div>
            </div>
            <hr />
        </div>
        status:{{ selectTab }}
        <div class="py-5" v-if="selectTab.id == 1">
            <div class="px-0 sm:px-2 mb-3" v-for="product in this.$store.getters.cart" :key="product.id" >
                <div class="overflow-hidden mb-3">
                    <div class="font-bold flex flex-shrink-0 items-center">
                        <div class="border border-gray-200 rounded-md w-14 h-14 sm:w-14 sm:h-14 flex flex-shrink-0 overflow-hidden">
                            <img :src="`${this.api}/image/get/${product.productCart.images[0].source}`" class="object-cover object-center w-full h-full" alt="Product image" />
                        </div>
                        <span class="ml-2">{{ product.productCart.productName }}</span>
                    </div>
                    <!-- <div class="w-full overflow-hidden flex justify-between items-center py-3"> -->
                    <div class="px-1 sm:px-3 py-2">
                        <p class="">color: {{ product.colors.label }}</p>
                        <div class="flex justify-between">
                            <span class="">฿{{ product.productCart.price }} x {{ product.quantity }}</span>
                            <span class="text-lg font-semibold">฿{{ product.productCart.price * product.quantity }}</span>
                        </div>
                    </div>
                    <!-- </div> -->
                </div>
                <div class="flex justify-end border-b-2 pb-3 text-white">
                    <button class="bg-primary px-3 py-2 mx-3">review</button>
                    <button class="bg-secondary px-3 py-2 mx-3">buy again</button>
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
};
</script>

<style></style>
