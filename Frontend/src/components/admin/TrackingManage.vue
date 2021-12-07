<template>
    <div class="text-center">
        <span v-if="order.shippings.trackingNumber" class="px-6 py-3">{{ order.shippings.trackingNumber }}</span>
        <div v-else class="flex flex-row">
            <input v-model="trackingNumber" type="text" class="bg-gray-50 shadow-sm relative">
            <button @click="addTracking" class="bg-green-400 rounded px-2 text-sm text-white font-semibold">Add</button>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    props: {
        order: Object,
    },
    data() {
        return {
            trackingNumber: "",
            apiUpdateOrder: `${process.env.VUE_APP_API}/order/update`,
            orderToAddTrackingNumber: {},
        };
    },
    methods: {
        ativeTrackingForm() {
            this.activeAddButtonTracking = false;
            this.activeAddTracking = true;
        },
        addTracking() {
            this.orderToAddTrackingNumber.shippings.trackingNumber = this.trackingNumber;
            this.orderToAddTrackingNumber.shippings.status = "to be receive";
            axios
                .put(this.apiUpdateOrder, this.orderToAddTrackingNumber, {
                    headers: {
                        Authorization: this.$store.getters.token,
                    },
                })
                .then((res) => {
                    console.log(res);
                });
        },
    },
    created() {
        this.orderToAddTrackingNumber = Object.assign(this.order);
    },
};
</script>

<style></style>
