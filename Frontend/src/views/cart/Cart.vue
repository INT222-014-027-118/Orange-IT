<template>
    <div class="mx-auto max-w-7xl sm:mt-5">
        <div class="relative grid grid-cols-3 gap-3 px-1 md:mt-0 md:p-2 lg:mx-auto">
            <div class="flex-col col-span-3 md:col-span-2 felx">
                <h1 class="py-3 px-3 text-2lg md:text-3xl font-semibold flex items-center">
                    <span class="material-icons mr-2 text-xl py-2 px-3 bg-primary text-white rounded-full"> shopping_cart </span>Cart
                </h1>
                <div v-if="!this.$store.getters.cart.length == 0">
                    <div v-for="(product, index) in this.$store.getters.cart" :key="product.id">
                        <cart-item :product="product" :index="index" />
                    </div>
                </div>
                <div v-else class="h-full py-10">
                    <p class="max-w-lg mx-auto text-center h-full text-2xl capitalize">no product in cart</p>
                </div>
            </div>

            <div class="col-span-3 pb-28 sm:pb-10 md:pb-0 md:col-span-1 ">
                <h1 class="py-3 text-lg md:text-3xl font-semibold hidden md:block">Order summary</h1>
                <div class="w-full md:px-5 md:pb-5 bg-white dark:bg-dark_tertiary rounded-md lg:px-6 shadow-md">
                    <!-- <div class="flex justify-between py-4 border-b">
                        <div class="text-gray-500 dark:text-gray-300">Subtotal</div>
                        <div class="font-semibold">฿ 999999</div>
                    </div> -->
                    <!-- <div class="flex justify-between py-4 border-b">
                        <div class="text-gray-500 dark:text-gray-300">Shipping estimate</div>
                        <div class="font-semibold">฿ 999999</div>
                    </div>
                    <div class="flex justify-between py-4 border-b">
                        <div class="text-gray-500 dark:text-gray-300">tax estimate</div>
                        <div class="font-semibold">฿ 999999</div>
                    </div> -->
                    <div
                        class="fixed bottom-0 left-0 w-full bg-white dark:bg-dark_secondary sm:dark:bg-dark_tertiary px-2 pt-1 pb-16 sm:pb-5 md:pb-0 sm:pt-0 md:px-0 md:static border-t md:border-0 dark:border-gray-600"
                    >
                        <div class="flex justify-between pb-2 sm:py-4">
                            <div class="text-xl font-semibold">Order total</div>
                            <div class="text-xl font-semibold md:text-2xl text-primary">{{ orderTotalPrice }}</div>
                        </div>
                        <button
                            class="w-full px-2 py-2 sm:p-3 mb-1 sm:mb-0 text-center font-semibold text-xl text-white rounded-md bg-primary "
                            @click="$router.push('checkout')"
                            :disabled="$store.getters.userInfo === null"
                            :class="[$store.getters.userInfo === null ? 'cursor-not-allowed hover:bg-primary' : 'hover:bg-primaryfocus']"
                        >
                            Checkout
                        </button>
                        <p v-if="$store.getters.userInfo === null" class="text-red-500 text-center font-semibold mt-4">please login to continue</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import CartItem from "../../components/CartItem.vue";
export default {
    components: {
        CartItem,
    },
    data() {
        return {};
    },
    methods: {
        scrollToTop() {
            window.scrollTo(0, 0);
        },
    },
    computed: {
        orderTotalPrice() {
            return new Intl.NumberFormat("th-TH", {
                style: "currency",
                currency: "THB",
                minimumFractionDigits: 0,
                maximumFractionDigits: 2,
            }).format(this.$store.getters.orderTotalPrice);
        },
    },
    mounted() {
        this.scrollToTop();
    },
    async created() {},
};
</script>

<style scpoed></style>
