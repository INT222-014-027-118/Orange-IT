<template>
    <div class="m-10 max-w-7xl bg-white">
        <span class="text-3xl font-semibold font-sans" >Products List</span>
        <product-item v-for="product in $store.getters.products" :key="product.id" :product="product"/>
    </div>

</template>

<script>
import ProductItem from "../../components/admin/productItem.vue";

export default {
    components: {
        ProductItem,
    },
};
</script>

<style></style>
