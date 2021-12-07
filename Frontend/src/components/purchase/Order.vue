<template>
    <div class="mb-2 p-3 border rounded-md dark:border-gray-500 overflow-hidden">
        <PurchItem v-for="product in order.orderItems" :key="product.id" :product="product" />
        <div class="text-sm md:text-base text-right font-light whitespace-nowrap">
            <p>{{ order.shippings.companyShipping }}</p>
            <p v-if="order.shippings.status == 'to be receive'">{{ order.shippings.trackingNumber }}</p>
            <p>Ordered on: {{ order.orderDate }}</p>
        </div>
        <div>
            <p class="text-lg sm:text-2xl font-semibold self-end ml-auto pb-0.5 whitespace-nowrap ">
                Total price: <span class="text-primary">{{ totalPrice }}</span>
            </p>
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
            return new Intl.NumberFormat("th-TH", {
                style: "currency",
                currency: "THB",
                minimumFractionDigits: 0,
                maximumFractionDigits: 2,
            }).format(
                this.order.orderItems.length > 0
                    ? this.order.orderItems
                          .map((product) => product.price * product.quantity)
                          .reduce((previousPrice, currentPrice) => {
                              return previousPrice + currentPrice;
                          })
                    : 0
            );
        },
    },
};
</script>
