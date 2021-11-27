<template>
    <div class="m-2 p-3 shadow">
        <purch-item v-for="product in order.orderItems" :key="product.id" :product="product" />
        <div class="text-right">
            <p>{{ order.shippings.companyShipping }}</p>
            <p v-if="order.shippings.status == 'to be receive'">{{ order.shippings.trackingNumber }}</p>
            <p>Ordered on: {{ order.orderDate }}</p>
        </div>
    </div>
</template>

<script>
import PurchItem from "./purchItem.vue";
export default {
    components: {
        PurchItem,
    },
    props: {
        order: Object,
    },
};
</script>

<style></style>
