<template>
    <div>
        <div class="flex justify-between flex-wrap">
            <h1 class="text-3xl font-semibold p-3 capitalize">address</h1>
            <button type="button" class="px-5 py-2 btn self-center my-2 ml-auto" @click="showForm = true" v-if="!showForm"><span class="material-icons text-base mr-1"> add </span>Add</button>
        </div>
        <hr />
        <div class="px-0 md:px-3 py-4">
            <div v-show="!showForm" v-for="(address, index) in this.$store.getters.addresses" :key="address" class="border dark:border-gray-500 px-3 pt-3 pb-10 md:pb-3 mt-4 relative">
                <div class="absolute bottom-0 md:top-0 right-5 cursor-pointer text-red-500 p-2 font-semibold" @click="removeAddress(index)">remove</div>
                <p class="capitalize">name : {{ address.firstname }} {{ address.lastname }}</p>
                <p class="capitalize">phone : {{ address.phone }}</p>
                <p class="capitalize break-words">
                    address : {{ address.address == "" ? "" : ` ${address.address}, ` }}{{ address.subDistrict == "" ? "" : `${address.subDistrict}, ` }}
                    {{ address.district == "" ? "" : `${address.district}, ` }}{{ address.province == "" ? "" : `${address.province}, ` }}
                    {{ address.postCode == "" ? "" : `${address.postCode}` }}
                </p>
            </div>
        </div>
        <div v-if="showForm">
            <form @submit.prevent="addAddress" class="px-3 py-4 ">
                <div class="space-y-5">
                    <div class="sm:flex items-center mb-2">
                        <label for="fname" class="font-semibold block sm:w-2/6 sm:text-right pr-2">first name :</label>
                        <input v-model="formAddress.firstname" ref="firstname" type="text" id="fname" maxlength="50" name="fname" class="w-full sm:w-3/6 border input-theme" />
                    </div>
                    <div class="sm:flex items-center mb-2">
                        <label for="lname" class="font-semibold block sm:w-2/6 sm:text-right pr-2">last name :</label>
                        <input v-model="formAddress.lastname" ref="lastname" type="text" id="lname" maxlength="50" name="lname" class="w-full sm:w-3/6 border input-theme" />
                    </div>
                    <div class="sm:flex items-center mb-2">
                        <label for="phone" class="font-semibold block sm:w-2/6 sm:text-right pr-2">phone :</label>
                        <input v-model="formAddress.phone" ref="phone" type="tel" id="phone" minlength="10" maxlength="10" pattern="[0]{1}[0-9]{9}" name="phone" class="w-full sm:w-3/6 border input-theme" />
                    </div>
                    <div class="sm:flex items-center mb-2">
                        <label for="province" class="font-semibold block sm:w-2/6 sm:text-right pr-2">province :</label>
                        <input v-model="formAddress.province" ref="province" type="text" id="province" maxlength="50" name="address-level1" class="w-full sm:w-3/6 border input-theme" />
                    </div>
                    <div class="sm:flex items-center mb-2">
                        <label for="city" class="font-semibold block sm:w-2/6 sm:text-right pr-2">district / city :</label>
                        <input v-model="formAddress.district" ref="district" type="text" id="city" maxlength="50" name="address-level2" class="w-full sm:w-3/6 border input-theme" />
                    </div>
                    <div class="sm:flex items-center mb-2">
                        <label for="suburb" class="font-semibold block sm:w-2/6 sm:text-right pr-2">Sub-district :</label>
                        <input v-model="formAddress.subDistrict" ref="subDistrict" type="text" id="suburb" maxlength="50" name="suburb" class="w-full sm:w-3/6 border input-theme" />
                    </div>
                    <div class="sm:flex items-center mb-2">
                        <label for="postal" class="font-semibold block sm:w-2/6 sm:text-right pr-2">postal :</label>
                        <input v-model="formAddress.postCode" ref="postCode" type="text" id="postal" minlength="5" maxlength="5" name="postal-code" class="w-full sm:w-3/6 border input-theme" />
                    </div>
                    <div class="sm:flex items-center mb-2">
                        <label for="address" class="font-semibold block sm:w-2/6 sm:text-right pr-2">address :</label>
                        <textarea v-model="formAddress.address" ref="address" type="text" id="address" maxlength="150" name="address-line1" class="w-full sm:w-3/6 border input-theme" />
                    </div>
                </div>

                <div class="mt-7 flex justify-end">
                    <button
                        type="reset"
                        class="px-4 py-2 cancel-btn"
                        @click="
                            showForm = false;
                            resetForm();
                        "
                    >
                        cancel
                    </button>
                    <button type="submit" class="px-6 py-2 btn ml-3">Save</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            showForm: false,
            formAddress: {
                id: 1,
                firstname: "",
                lastname: "",
                phone: "",
                province: "",
                district: "",
                subDistrict: "",
                postCode: "",
                address: "",
                userId: localStorage.getItem("userId"),
            },
            invalid: {
                firstname: true,
                lastname: true,
                phone: true,
                province: true,
                district: true,
                subDistrict: true,
                postCode: true,
                address: true,
            },
        };
    },
    methods: {
        addAddress() {
            this.invalid.address = this.formAddress.address == "" ? false + this.$refs.address.focus() : true;
            this.invalid.postCode = this.formAddress.postCode == "" ? false + this.$refs.postCode.focus() : true;
            this.invalid.subDistrict = this.formAddress.subDistrict == "" ? false + this.$refs.subDistrict.focus() : true;
            this.invalid.province = this.formAddress.province == "" ? false + this.$refs.province.focus() : true;
            this.invalid.district = this.formAddress.district == "" ? false + this.$refs.district.focus() : true;
            this.invalid.phone = this.formAddress.phone == "" ? false + this.$refs.phone.focus() : true;
            this.invalid.lastname = this.formAddress.lastname == "" ? false + this.$refs.lastname.focus() : true;
            this.invalid.firstname = this.formAddress.firstname == "" ? false + this.$refs.firstname.focus() : true;

            if (
                this.invalid.firstname &&
                this.invalid.lastname &&
                this.invalid.phone &&
                this.invalid.province &&
                this.invalid.district &&
                this.invalid.subDistrict &&
                this.invalid.postCode &&
                this.invalid.address
            ) {
                let newAddress = {};
                Object.assign(newAddress, this.formAddress);
                this.$store.commit("addAddress", newAddress);
                this.showForm = false;
                this.resetForm();
            }
        },
        removeAddress(index) {
            this.$store.commit("removeAddress", index);
        },
        resetForm() {
            this.formAddress.firstname = "";
            this.formAddress.lastname = "";
            this.formAddress.phone = "";
            this.formAddress.province = "";
            this.formAddress.district = "";
            this.formAddress.subDistrict = "";
            this.formAddress.postCode = "";
            this.formAddress.address = "";
        },
    },
    created() {
        this.$store.dispatch("loadUserAddresses");
    },
};
</script>

<style scpoed></style>
