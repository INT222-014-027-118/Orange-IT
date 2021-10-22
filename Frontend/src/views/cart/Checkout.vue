<template>
    <div class="pt-1">
        <div class="mx-auto max-w-7xl">
            <div class="relative p-1 md:mt-0 md:p-2 lg:mx-auto">
                <div class="felx flex-col capitalize bg-white dark:bg-dark_secondary rounded-md p-1 md:p-3 mb-3 relative">
                    <div class="mb-4 text-xl font-semibold flex items-center" :class="[changeAddress ? 'mb-10 md:mb-4' : '']"><span class="material-icons mr-3 "> place </span>delivery address</div>
                    <button
                        type="button"
                        @click="
                            $router.push({ name: 'Address', params: { address: 'address' } });
                            this.$store.commit('setShowAccountPage');
                        "
                        v-show="changeAddress"
                        class="bg-primary rounded-md text-white px-3 py-2 leading-none tracking-tighter inline-block absolute top-10 md:top-3 right-1 md:right-5"
                    >
                        address manage
                    </button>
                    <div class="md:flex p-2" v-show="!changeAddress">
                        <div class="md:w-2/6 font-bold">
                            <p class="pt-1 md:pt-0">{{ selectedAddress.firstname }} {{ selectedAddress.lastanme }} ( {{ selectedAddress.phone }})</p>
                        </div>
                        <div class="md:w-3/6">
                            <p class="pt-1 md:pt-0">
                                {{ selectedAddress.address == "" ? "" : ` ${selectedAddress.address}, ` }}{{ selectedAddress.sub_district == "" ? "" : `${selectedAddress.sub_district}, ` }}
                                {{ selectedAddress.district == "" ? "" : `${selectedAddress.district}, ` }}{{ selectedAddress.province == "" ? "" : `${selectedAddress.province}, ` }}
                                {{ selectedAddress.postal_code == "" ? "" : `${selectedAddress.postal_code}` }}
                            </p>
                        </div>

                        <div class="md:w-1/6 py-3 md:py-0 flex md:justify-center">
                            <button type="button" class="md:text-center cursor-pointer px-4 py-2 md:px-3 md:py-0 bg-primary rounded-md text-white" @click="changeAddress = !changeAddress" v-show="!changeAddress">change</button>
                        </div>
                    </div>
                    <div v-show="changeAddress" class="space-y-2 py-2 md:p-2">
                        <div
                            class="md:flex items-center border-2 p-2 relative cursor-pointer pl-5"
                            v-for="address in addresses"
                            :key="address"
                            @click="selectedAddress = address"
                            :class="[selectedAddress.id == address.id ? 'border-primary' : '']"
                        >
                            <div class="md:w-2/6 font-bold">{{ address.firstname }} {{ address.lastanme }} ( {{ address.phone }})</div>
                            <div class="absolute left-0 top-0 h-full text-white bg-primary flex items-center">
                                <span class="material-icons py-2 text-base font-bold  " :class="[selectedAddress.id === address.id? '' : 'hidden']"> check </span>
                            </div>
                            <div class="md:w-4/6">
                                <p>
                                    {{ address.address == "" ? "" : ` ${address.address}, ` }}{{ address.sub_district == "" ? "" : `${address.sub_district}, ` }}
                                    {{ address.district == "" ? "" : `${address.district}, ` }}{{ address.province == "" ? "" : `${address.province}, ` }}
                                    {{ address.postal_code == "" ? "" : `${address.postal_code}` }}
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="py-3 md:py-3 md:px-2" v-show="changeAddress">
                        <button type="button" class="bg-primary rounded-md text-white px-3 py-2 mr-3 w-20" @click="changeAddress = false">OK</button>
                    </div>
                </div>

                <div class="felx flex-col capitalize bg-white dark:bg-dark_secondary rounded-md p-1 md:p-3 mb-3">
                    <div class="flex justify-between mb-4 ">
                        <div class="text-xl font-semibold flex items-center">ordered</div>
                        <div class="w-2/4 hidden md:inline-flex justify-between pr-2">
                            <span class="w-1/2 text-center text-gray-400">color</span>
                            <span class="w-1/2 text-center text-gray-400">unit price</span>
                            <span class="w-1/2 text-center text-gray-400">quantity</span>
                            <span class="w-1/2 text-center text-gray-400">price</span>
                        </div>
                    </div>
                    <div class="p-2 space-y-2">
                        <div class="md:flex overflow-hidden">
                            <div class="md:w-2/4 font-bold flex flex-shrink-0 items-center">
                                <div class="border border-gray-200 rounded-md w-14 h-14 sm:w-14 sm:h-14 flex flex-shrink-0 overflow-hidden">
                                    <img src="http://20.205.201.136/orange-it/image/get/BlackSharkV2Pro1.png" class="object-cover object-center w-full h-full" alt="Product image" />
                                </div>
                                <span class="ml-2">{addresssssssssssss ssssssssssssss ssssssssdsf sdf sdssssssss ssssssssssssssssssssss}</span>
                            </div>
                            <div class="w-full md:w-2/4 overflow-hidden flex flex-wrap md:flex-nowrap justify-between md:justify-center items-center py-3">
                                <p class="w-full md:w-full md:text-center"><span class="md:hidden">color :</span> {color}</p>
                                <p class="md:w-full text-center">฿99</p>
                                <p class="md:w-full text-right md:text-center">x 9</p>
                                <p class="w-full md:w-full text-right md:text-center">฿9999</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="capitalize bg-white dark:bg-dark_secondary rounded-md p-1 md:p-3 mb-3">
                    <div class="mb-4 text-xl font-semibold flex items-center"><span class="material-icons mr-3"> local_shipping </span>Shipping</div>
                    <div class="flex flex-wrap justify-between md:flex-none p-2">
                        <div class="md:flex justify-between w-3/4">
                            <div class="w-full md:w-1/4">
                                Standard Delivery
                            </div>
                            <div class="w-full md:w-2/4">
                                <p>free shipping</p>
                            </div>
                        </div>
                        <div class="md:w-1/4 flex justify-end">
                            <p class="w-full text-center">฿ 0</p>
                        </div>
                    </div>
                </div>

                <div class="felx flex-col capitalize bg-white dark:bg-dark_secondary rounded-md p-1 md:p-3">
                    <h1 class="mb-4 text-xl font-semibold flex items-center"><span class="material-icons mr-3"> payments </span>payment method</h1>
                    <div class="flex flex-wrap">
                        <!-- <input id="1" type="radio" class="px-3 py-2 mx-3" v-model="paymentMethod" value="เก็บเงินปลาย" />
                        <label for="1"> เก็บเงินปลาย</label>
                        <input id="2" type="radio" class="px-3 py-2 mx-3" v-model="paymentMethod" value="โอน/ชำระผ่านบัญชีธนาคาร" />
                        <label for="2"> โอน/ชำระผ่านบัญชีธนาคาร</label>
                        <input id="3" type="radio" class="px-3 py-2 mx-3" v-model="paymentMethod" value="ทางบัตรเครดิต/บัตรเดบิต" />
                        <label for="3"> ทางบัตรเครดิต/บัตรเดบิต</label> -->
                        <div
                            class="border-2 px-6 py-2 flex items-center mb-3 mx-2 relative rounded-md cursor-pointer"
                            v-for="pay in paymentMethod"
                            :key="pay"
                            @click="selectPaymentMethod === pay ? (selectPaymentMethod = pay) : (selectPaymentMethod = selectPaymentMethod)"
                            :class="[selectPaymentMethod === pay ? 'border-primary' : 'cursor-not-allowed text-gray-500']"
                        >
                            <span class="material-icons absolute left-0 h-full py-2 text-base font-bold text-white bg-primary " :class="[selectPaymentMethod === pay ? '' : 'hidden']"> check </span>
                            <span class="">{{ pay }}</span>
                        </div>
                    </div>

                    <hr class="my-3" />
                    <div class="">
                        <h1 class="text-xl font-semibold mb-4">Checkout</h1>
                        <div class="flex justify-end">
                            <div class="flex justify-between items-end md:w-1/3 text-base w-full">
                                <p>all payments:</p>
                                <p class="text-3xl font-semibold">฿ 9999999</p>
                            </div>
                        </div>
                        <div class="flex justify-end">
                            <button class="bg-primary px-5 py-3 w-full md:w-1/3 text-white rounded-md mt-4">checkout</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            changeAddress: false,
            addresses: [
                {id:1, firstname: "Apisit", lastanme: "Kaewnongsaeng", phone: "087XX", province: "สมุทรปราการ", district: "บางบ่อ", sub_district: "บางบ่อ", postal_code: "10560", address: "66/61 วโรชา หมู่ 2" },
                {id:2, firstname: "Jakkapong", lastanme: "Praditthanachot", phone: "087XXXXXX", province: "บางนา", district: "บางบ่อ", sub_district: "บางนา", postal_code: "10560", address: "66/61 วโรชา หมู่ 2" },
                {id:3, firstname: "Traitawat", lastanme: "Look", phone: "087XXXXXX", province: "บางนา", district: "บางบ่อ", sub_district: "บางนา", postal_code: "10560", address: "66/61 วโรชา หมู่ 2" },
            ],
            selectedAddress: {
                id:1,
                firstname: "Apisit",
                lastanme: "Kaewnongsaeng",
                phone: "087XX",
                province: "สมุทรปราการ",
                district: "บางบ่อ",
                sub_district: "บางบ่อ",
                postal_code: "10560",
                address: "66/61 วโรชา หมู่ 2",
            },
            paymentMethod: ["เก็บเงินปลาย", "โอน/ชำระผ่านบัญชีธนาคาร", "ทางบัตรเครดิต/บัตรเดบิต"],
            selectPaymentMethod: "เก็บเงินปลาย",
        };
    },
};
</script>

<style></style>
