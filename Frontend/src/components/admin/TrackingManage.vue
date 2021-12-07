<template>
    <div class="text-center">
        <span v-if="order.shippings.trackingNumber" class="px-6 py-3">{{ order.shippings.trackingNumber }}</span>
        <div v-else class="flex flex-row">
            <input v-model="trackingNumber" type="text" class="bg-gray-50 shadow-sm relative" />
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
            apiUpdateShipping: `${process.env.VUE_APP_API}/shipping/update`,
            shipping: {},
        };
    },
    methods: {
        ativeTrackingForm() {
            this.activeAddButtonTracking = false;
            this.activeAddTracking = true;
        },
        async addTracking() {
            this.shipping.trackingNumber = this.trackingNumber;
            this.shipping.status = "to be receive";
            axios
                .put(this.apiUpdateShipping, this.shipping, {
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
        this.shipping = Object.assign(this.order.shippings);
    },
};
</script>

<style></style>
