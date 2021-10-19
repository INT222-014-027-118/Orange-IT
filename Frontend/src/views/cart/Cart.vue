<template>
    <div class="mx-auto max-w-7xl sm:mt-5">
        <div class="relative grid grid-cols-3 gap-3 p-1 md:mt-0 md:p-5 lg:mx-auto">
            <div class="flex-col col-span-3 md:col-span-2 felx">
                <h1 class="py-3 text-2xl font-semibold">Shopping Cart</h1>
                <div v-if="!this.$store.getters.cart.length == 0">
                    <div v-for="(product,index) in this.$store.getters.cart" :key="product.id" >
                        <cart-item :product="product" :index="index"/>
                    </div>
                </div>
                <div v-else>no product in cart</div>
            </div>

            <div class="col-span-3 pb-28 md:col-span-1">
                <h1 class="py-3 text-2xl font-semibold">Order summary</h1>
                <div class="w-full px-5 pb-5 bg-white dark:bg-dark_tertiary rounded-md lg:px-6">
                    <!-- <div class="flex justify-between py-4 border-b">
                        <div class="text-gray-500 dark:text-gray-300">Subtotal</div>
                        <div class="font-semibold">฿ 999999</div>
                    </div>
                    <div class="flex justify-between py-4 border-b">
                        <div class="text-gray-500 dark:text-gray-300">Shipping estimate</div>
                        <div class="font-semibold">฿ 999999</div>
                    </div>
                    <div class="flex justify-between py-4 border-b">
                        <div class="text-gray-500 dark:text-gray-300">tax estimate</div>
                        <div class="font-semibold">฿ 999999</div>
                    </div> -->
                    <div class="fixed bottom-0 left-0 w-full bg-white dark:bg-dark_secondary sm:dark:bg-dark_tertiary px-5 pb-20 sm:pb-5 sm:pt-5 md:px-0 md:static border-t md:border-0 dark:border-gray-600">
                        <div class="flex justify-between py-4">
                            <div class="text-xl font-semibold">Order total</div>
                            <div class="text-xl font-semibold md:text-2xl text-primary">฿ {{this.$store.getters.orderTotalPrice}}</div>
                        </div>
                        <button class="w-full p-3 text-center text-white rounded-md bg-primary hover:bg-primaryfocus">Checkout</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import CartItem from '../../components/CartItem.vue'
export default {
    components:{
        CartItem
    },
    data() {
        return {
        };
    },
    created() {
        console.log();
    },
};
</script>

<style scpoed>
/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

/* Firefox */
input[type="number"] {
    -moz-appearance: textfield;
}
</style>
