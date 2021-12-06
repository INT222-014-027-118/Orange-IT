<template>
    <div class="mb-2 p-3 border rounded-md dark:border-gray-500">
        <PurchItem v-for="product in order.orderItems" :key="product.id" :product="product" />
        <div class="text-right">
            <p>{{ order.shippings.companyShipping }}</p>
            <p v-if="order.shippings.status == 'to be receive'">{{ order.shippings.trackingNumber }}</p>
            <p>Ordered on: {{ order.orderDate }}</p>
        </div>
        <div>
            <p class="text-lg sm:text-2xl font-semibold self-end ml-auto pb-0.5">Total price: {{ totalPrice }}</p>
        </div>
    </div>
</template>

<script>
import PurchItem from "./PurchItem.vue";
export default {
    components: {
        PurchItem,
    },
    props: {
        order: Object,
    },
    computed: {
        totalPrice() {
            return this.order.orderItems.length > 0
                ? this.order.orderItems
                      .map((product) => product.price * product.quantity)
                      .reduce((previousPrice, currentPrice) => {
                          return previousPrice + currentPrice;
                      })
                : 0;
        },
    },
};
</script>
