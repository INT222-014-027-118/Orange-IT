<template>
    <div class="" v-if="!this.$store.getters.cart.length == 0">
        <div class="mx-auto max-w-7xl">
            <div class="relative md:mt-0 md:p-2 lg:mx-auto space-y-5">
                <div class="felx flex-col capitalize bg-white dark:bg-dark_secondary shadow-md rounded-md p-3 md:p-3 relative">
                    <div class="mb-4 text-2xl md:text-3xl font-light flex items-center" :class="[changeAddress || $store.getters.addresses.length == 0 ? 'mb-12 md:mb-4' : '']">
                        <span class="material-icons mr-3 text-xl py-2 px-3 bg-primary text-white rounded-full"> place </span>
                        <h1 class="whitespace-nowrap">delivery address</h1>
                    </div>
                    <hr class="my-3 dark:border-gray-500" />
                    <button
                        type="button"
                        @click="
                            $router.push({ name: 'Address', params: { address: 'address' } });
                            this.$store.commit('setShowAccountPage');
                        "
                        v-show="changeAddress || $store.getters.addresses.length == 0"
                        class="btn px-3 py-2 leading-none absolute top-14 md:top-5 right-1 md:right-5"
                    >
                        address manage
                    </button>
                    <div class="md:flex p-2" v-show="!changeAddress" v-if="$store.getters.addresses.length !== 0">
                        <div class="md:w-2/6 font-bold">
                            <p class="pt-1 md:pt-0">{{ selectedAddress.firstname }} {{ selectedAddress.lastname }} ( {{ selectedAddress.phone }})</p>
                        </div>
                        <div class="md:w-3/6">
                            <p class="pt-1 md:pt-0">
                                {{ selectedAddress.address == "" ? "" : ` ${selectedAddress.address}, ` }}{{ selectedAddress.subDistrict == "" ? "" : `${selectedAddress.subDistrict}, ` }}
                                {{ selectedAddress.district == "" ? "" : `${selectedAddress.district}, ` }}{{ selectedAddress.province == "" ? "" : `${selectedAddress.province}, ` }}
                                {{ selectedAddress.postCode == "" ? "" : `${selectedAddress.postCode}` }}
                            </p>
                        </div>

                        <div class="md:w-1/6 py-3 md:py-0 flex md:justify-center">
                            <button type="button" class="md:text-center cursor-pointer px-4 py-2 md:px-3 md:py-0 btn" @click="changeAddress = !changeAddress" v-show="!changeAddress">
                                change
                            </button>
                        </div>
                    </div>
                    <div v-else class="p-2">no address</div>

                    <div v-show="changeAddress" class="space-y-2 py-2 md:p-2 border rounded-md ">
                        <div
                            class="md:flex items-center ring-2 p-2 relative cursor-pointer pl-5 rounded-sm"
                            v-for="address in this.$store.getters.addresses"
                            :key="address.id"
                            @click="selectedAddress = address"
                            :class="[selectedAddress.id == address.id ? 'ring-primary' : 'ring-transparent']"
                        >
                            <div class="md:w-2/6 font-bold">{{ address.firstname }} {{ address.lastname }} ( {{ address.phone }})</div>
                            <div class="absolute left-0 top-0 h-full text-white bg-primary flex items-center">
                                <span class="material-icons py-2 text-base font-bold  " :class="[selectedAddress.id === address.id ? '' : 'hidden']"> check </span>
                            </div>
                            <div class="md:w-4/6">
                                <p>
                                    {{ address.address == "" ? "" : ` ${address.address}, ` }}{{ address.subDistrict == "" ? "" : `${address.subDistrict}, ` }}
                                    {{ address.district == "" ? "" : `${address.district}, ` }}{{ address.province == "" ? "" : `${address.province}, ` }}
                                    {{ address.postCode == "" ? "" : `${address.postCode}` }}
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="py-3 md:py-3 md:px-2" v-show="changeAddress">
                        <button type="button" class="btn px-3 py-2 mr-3 w-20" @click="changeAddress = false">OK</button>
                    </div>
                </div>

                <div class="felx flex-col capitalize bg-white dark:bg-dark_secondary shadow-md rounded-md p-3 md:p-3">
                    <div class="flex justify-between mb-2 md:mb-4">
                        <div class="text-2xl md:text-3xl font-light flex items-center">
                            <span class="material-icons mr-3 text-xl py-2 px-3 bg-primary text-white rounded-full"> receipt_long </span>
                            <h1 class="whitespace-nowrap">ordered</h1>
                        </div>
                        <div class="w-2/4 hidden md:inline-flex justify-between pr-2">
                            <span class="w-1/2 text-center text-gray-400">color</span>
                            <span class="w-1/2 text-center text-gray-400">unit price</span>
                            <span class="w-1/2 text-center text-gray-400">quantity</span>
                            <span class="w-1/2 text-center text-gray-400">price</span>
                        </div>
                    </div>
                    <hr class="my-3 dark:border-gray-500" />
                    <div class="px-0 sm:px-2 space-y-2" v-for="product in this.$store.getters.cart" :key="product.id">
                        <div class="md:flex overflow-hidden mb-3">
                            <div class="md:w-2/4 font-semibold flex flex-shrink-0 items-center">
                                <div class="border border-gray-200 rounded-md w-14 h-14 sm:w-14 sm:h-14 flex flex-shrink-0 overflow-hidden">
                                    <img :src="`${this.api}/image/get/${product.productCart.images[0].source}`" class="object-cover object-center w-full h-full" alt="Product image" />
                                </div>
                                <span class="ml-2">{{ product.productCart.productName }}</span>
                            </div>
                            <div class="w-full md:w-2/4 overflow-hidden flex flex-wrap md:flex-nowrap justify-between md:justify-center items-center py-3">
                                <p class="w-full md:w-full md:text-center"><span class="md:hidden">color :</span> {{ product.colors.label }}</p>
                                <p class="md:w-full text-center">{{ passingPrice(product.productCart.price) }}</p>
                                <p class="md:w-full text-right md:text-center">x {{ product.quantity }}</p>
                                <p class="w-full md:w-full text-right md:text-center">{{ passingPrice(product.productCart.price * product.quantity) }}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="capitalize bg-white dark:bg-dark_secondary shadow-md rounded-md p-3 md:p-3">
                    <div class="mb-2 md:mb-4 text-2xl md:text-3xl font-light flex items-center">
                        <span class="material-icons mr-3 text-xl py-2 px-3 bg-primary text-white rounded-full"> local_shipping </span>
                        <h1 class="whitespace-nowrap">Shipping</h1>
                    </div>
                    <hr class="my-3 dark:border-gray-500" />
                    <div class="flex flex-wrap justify-between md:flex-none px-0 py-1 sm:px-2 sm:py-2">
                        <div class="md:flex justify-between w-3/4">
                            <div class="w-full md:w-1/4">
                                Standard Delivery
                            </div>
                            <div class="w-full md:w-2/4">
                                <p>free shipping</p>
                            </div>
                        </div>
                        <div class="md:w-1/4 flex justify-end">
                            <p class="w-full text-center">฿0</p>
                        </div>
                    </div>
                </div>

                <div class="felx flex-col capitalize bg-white dark:bg-dark_secondary shadow-md rounded-md p-3 md:p-3">
                    <h1 class="mb-4 text-2xl md:text-3xl font-light flex items-center">
                        <span class="material-icons mr-3 text-xl py-2 px-3 bg-primary text-white rounded-full"> payments </span>
                        <h1 class="whitespace-nowrap">payment method</h1>
                    </h1>
                    <div class="flex flex-wrap">
                        <!-- <input id="1" type="radio" class="px-3 py-2 mx-3" v-model="paymentMethod" value="เก็บเงินปลาย" />
                        <label for="1"> เก็บเงินปลาย</label>
                        <input id="2" type="radio" class="px-3 py-2 mx-3" v-model="paymentMethod" value="โอน/ชำระผ่านบัญชีธนาคาร" />
                        <label for="2"> โอน/ชำระผ่านบัญชีธนาคาร</label>
                        <input id="3" type="radio" class="px-3 py-2 mx-3" v-model="paymentMethod" value="ทางบัตรเครดิต/บัตรเดบิต" />
                        <label for="3"> ทางบัตรเครดิต/บัตรเดบิต</label> -->
                        <div
                            class="ring-2 px-6 py-2 flex items-center mx-2 relative rounded-sm cursor-pointer my-2"
                            v-for="pay in paymentMethod"
                            :key="pay"
                            @click="selectPaymentMethod === pay ? (selectPaymentMethod = pay) : (selectPaymentMethod = selectPaymentMethod)"
                            :class="[selectPaymentMethod === pay ? 'ring-primary' : 'cursor-not-allowed text-gray-500 ring-gray-200 dark:ring-gray-500']"
                        >
                            <span class="material-icons absolute left-0 h-full py-2 text-base font-bold text-white bg-primary " :class="[selectPaymentMethod === pay ? '' : 'hidden']"> check </span>
                            <span class="">{{ pay }}</span>
                        </div>
                    </div>

                    <hr class="my-3 dark:border-gray-500" />
                    <div class="">
                        <h1 class="text-xl font-semibold mb-4">Checkout</h1>
                        <div class="flex justify-end">
                            <div class="flex justify-between items-end md:w-1/3 text-base w-full p-1">
                                <p>all payments:</p>
                                <p class="text-3xl font-semibold">{{ passingPrice(this.$store.getters.orderTotalPrice) }}</p>
                            </div>
                        </div>
                        <div class="flex justify-end">
                            <button class="btn px-5 py-3 w-full md:w-1/3 mt-4 text-xl" @click="checkOut">submit order</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
export default {
    data() {
        return {
            changeAddress: false,
            selectedAddress: {},
            paymentMethod: ["เก็บเงินปลาย", "โอน/ชำระผ่านบัญชีธนาคาร", "ทางบัตรเครดิต/บัตรเดบิต"],
            selectPaymentMethod: "เก็บเงินปลาย",
            api: process.env.VUE_APP_API,
        };
    },
    methods: {
        scrollToTop() {
            window.scrollTo(0, 0);
        },
        passingPrice(price) {
            return new Intl.NumberFormat("th-TH", {
                style: "currency",
                currency: "THB",
                minimumFractionDigits: 0,
                maximumFractionDigits: 2,
            }).format(price);
        },
        checkOut() {
            let shipping = {
                id: 1,
                status: "to be delivered",
                companyShipping: "Standard Delivery",
                trackingNumber: "",
                deliveryDetailId: this.selectedAddress.id,
            };
            axios
                .post(`${process.env.VUE_APP_API}/shipping/add`, shipping, {
                    headers: {
                        Authorization: this.$store.getters.token,
                    },
                })
                .then((response) => {
                    console.log(response);
                    if (response.status === 200) {
                        let order = { id: 1, status: "in progress", orderDate: "", shippingId: response.data.id, paymentsId: 1 };
                        axios.post(`${process.env.VUE_APP_API}/order/add`, order, {
                            headers: {
                                Authorization: this.$store.getters.token,
                            },
                        });
                    }
                });
        },
    },
    mounted() {
        this.scrollToTop();
    },
    async created() {
        this.selectedAddress = await this.$store.getters.defaultAddress;
    },
    beforeCreate() {
        if (this.$store.getters.userInfo == null) {
            this.$router.push("/cart");
        }
    },
};
</script>

<style></style>
