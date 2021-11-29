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

        <!-- <div class="py-5" v-if="selectTab.id == 1">
            <div class="px-0 sm:px-2 pb-3" v-for="product in this.$store.getters.cart" :key="product.id">
                <PurchItem :product="product" />
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
        </div> -->

        <div class="py-5" v-if="selectTab.id == 1">
            <div class="px-0 sm:px-2 pb-3 border-b-2 mb-3" v-for="order in this.$store.getters.orders" :key="order.id">
                <order :order="order" />
            </div>
        </div>
        
        <div class="py-5" v-if="selectTab.id == 2">go</div>
        <div class="py-5" v-if="selectTab.id == 3">gogo</div>
    </div>
</template>

<script>
import Order from "../../components/Order.vue";
export default {
    components: {
        Order,
    },
    data() {
        return {
            tabs: [
                { id: 1, title: "to be delivered" },
                { id: 2, title: "to be receive" },
                { id: 3, title: "done" },
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
    created() {
         this.$store.dispatch("loadOrders");
    },
};
</script>

<style></style>
