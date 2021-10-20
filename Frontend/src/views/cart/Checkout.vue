<template>
    <div class="pt-1">
        <div class="mx-auto max-w-7xl sm:mt-5">
            <div class="relative p-1 md:mt-0 md:p-2 lg:mx-auto">
                <div class="felx flex-col capitalize bg-white p-3 mb-3">
                    <div class="flex justify-between">
                        <div class="mb-4 text-xl font-semibold flex items-center"><span class="material-icons mr-3"> place </span>delivery address</div>
                        <button type="button" @click="$router.push({ name: 'Address', params: { address: 'address' } })" v-show="changeAddress" class="bg-gray-200 px-2">address Manage</button>
                    </div>
                    <div class="flex p-2" v-show="!changeAddress">
                        <div class="w-2/6 font-bold">
                            {lkjasdoert5hna sdkfjha23423s} {08723423423}
                        </div>
                        <div class="w-3/6">
                            <p>{addresssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss}</p>
                        </div>
                        <div class="w-1/6 flex justify-center">
                            <button type="button" @click="changeAddress = !changeAddress" v-show="!changeAddress">change</button>
                        </div>
                    </div>
                    <div v-show="changeAddress" class="space-y-2 p-2">
                        <label for="A1" class="flex items-center ">
                            <input type="radio" id="A1" name="drone" class="flex p-2 mr-2" />
                            <div class="w-2/6 font-bold">
                                {lkjasdoert5hna sdkfjha23423s} {08723423423}
                            </div>
                            <div class="w-4/6">
                                <p>{addresssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss}</p>
                            </div>
                        </label>
                        <label for="A2" class="flex items-center ">
                            <input type="radio" id="A2" name="drone" class="flex p-2 mr-2" />
                            <div class="w-2/6 font-bold">
                                {lkjasdoert5hna sdkfjha23423s} {08723423423}
                            </div>
                            <div class="w-4/6">
                                <p>{addresssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss}</p>
                            </div>
                        </label>
                    </div>

                    <div class="p-4" v-show="changeAddress">
                        <button type="button" class="bg-gray-200 px-3 py-2 mr-3" @click="changeAddress = false">OK</button>
                        <button type="button" class="bg-gray-200 px-3 py-2" @click="changeAddress = false">cancle</button>
                    </div>
                </div>

                <div class="felx flex-col capitalize bg-white p-3 mb-3">
                    <div class="flex justify-between mb-4 ">
                        <div class="text-xl font-semibold flex items-center">ordered</div>
                        <div class="w-1/4 flex justify-between pr-2">
                            <span class="w-1/2 text-center">quantity</span>
                            <span class="w-1/2 text-center">price</span>
                        </div>
                    </div>
                    <div class="p-2 space-y-2">
                        <div class="flex">
                            <div class="w-2/4 font-bold flex flex-shrink-0 items-center">
                                <div class="border border-gray-200 rounded-md w-10 h-10 sm:w-14 sm:h-14 flex flex-shrink-0">
                                    <img src="http://20.205.201.136/orange-it/image/get/BlackSharkV2Pro1.png" class="object-cover object-center w-full h-full" alt="Product image" />
                                </div>
                                <span class=" ml-2">{addresssssssssssss ssssssssssssss ssssssssdsf sdf sdssssssss ssssssssssssssssssssss}</span>
                            </div>
                            <div class="w-1/4 overflow-hidden flex justify-center items-center">
                                <p class="w-full text-center">{color}</p>
                            </div>
                            <div class="w-1/4 flex justify-center items-center">
                                <p class="w-1/2 text-center">99</p>
                                <p class="w-1/2 text-center">฿9999</p>
                            </div>
                        </div>
                        <div class="flex">
                            <div class="w-2/4 font-bold flex flex-shrink-0 items-center">
                                <div class="border border-gray-200 rounded-md w-10 h-10 sm:w-14 sm:h-14 flex flex-shrink-0">
                                    <img src="http://20.205.201.136/orange-it/image/get/BlackSharkV2Pro1.png" class="object-cover object-center w-full h-full" alt="Product image" />
                                </div>
                                <span class=" ml-2">{addresssssssssssss ssssssssssssss ssssssssdsf sdf sdssssssss ssssssssssssssssssssss}</span>
                            </div>
                            <div class="w-1/4 overflow-hidden flex justify-center items-center">
                                <p class="w-full text-center">{color}</p>
                            </div>
                            <div class="w-1/4 flex justify-center items-center">
                                <p class="w-1/2 text-center">99</p>
                                <p class="w-1/2 text-center">฿9999</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="felx flex-col capitalize bg-white p-3 mb-3">
                    <div class="flex justify-between">
                        <div class="mb-4 text-xl font-semibold flex items-center"><span class="material-icons mr-3"> local_shipping </span>Shipping</div>
                    </div>
                    <div class="flex p-2">
                        <div class="w-1/4">
                            Standard Delivery
                        </div>
                        <div class="w-2/4">
                            <p>free shipping</p>
                        </div>
                        <div class="w-1/4 flex justify-end">
                            <p>฿ 0</p>
                        </div>
                    </div>
                </div>

                <div class="felx flex-col capitalize bg-white p-3 mb-3">
                    <div class="flex justify-between">
                        <h1 class="mb-4 text-xl font-semibold flex items-center"><span class="material-icons mr-3"> payments </span>payment method</h1>
                    </div>
                    <div class="flex p-2">
                        <input id="1" type="radio" class="px-3 py-2 mx-3" v-model="paymentMethod" value="เก็บเงินปลาย" />
                        <label for="1"> เก็บเงินปลาย</label>
                        <input id="2" type="radio" class="px-3 py-2 mx-3" v-model="paymentMethod" value="โอน/ชำระผ่านบัญชีธนาคาร" />
                        <label for="2"> โอน/ชำระผ่านบัญชีธนาคาร</label>
                        <input id="3" type="radio" class="px-3 py-2 mx-3" v-model="paymentMethod" value="ทางบัตรเครดิต/บัตรเดบิต" />
                        <label for="3"> ทางบัตรเครดิต/บัตรเดบิต</label>
                    </div>

                    {{ paymentMethod }}

                    <div class="text-xl font-semibold flex items-center justify-between">
                        <h1>Checkout :</h1>

                        <button class="bg-primary px-3 py-1">checkout</button>
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
                { firstname: "Jakkapong", lastanme: "Praditthanachot", phone: "0877616878", province: "สมุทรปราการ", district: "บางบ่อ", sub_district: "", postal_code: "10560", address: "66/61 วโรชา หมู่ 2" },
            ],
            paymentMethod: "",
        };
    },
};
</script>

<style></style>
