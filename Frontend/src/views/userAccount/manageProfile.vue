<template>
    <div>
        <div class="flex justify-between flex-wrap overflow-hidden">
            <h1 class="text-3xl font-semibold p-3">My Profile</h1>
            <button type="button" @click="editBTN = true" v-if="!editBTN" class="px-5 py-2 btn self-center my-2 ml-auto"><span class="material-icons text-base mr-1"> edit </span>Edit</button>
        </div>
        <hr />
        <form @submit.prevent="update" class="px-3 pt-4 pb-6 ">
            <div class="space-y-5">
                <!-- <div class="sm:flex items-end mb-2 h-10">
                    <p class="font-semibold sm:w-2/6 sm:text-right pr-2">User name :</p>
                    <p class="lg:w-4/6 text-xl font-light">{{ $store.getters.userInfo.username }}</p>
                </div> -->

                <div class="sm:flex items-end mb-2">
                    <p class="font-semibold sm:w-2/6 sm:text-right pr-2 mb-2 sm:mb-0">{{ editBTN ? "first name :" : "user name :" }}</p>
                    <!-- <p class="lg:w-4/6 text-xl font-light">{{ $store.getters.userInfo.username }}</p> -->
                    <div class="w-full sm:w-4/6 ">
                        <p class="text-xl font-light break-words" v-show="!editBTN">{{ $store.getters.userInfo.username }}</p>
                        <input v-show="editBTN" v-model="firstname" ref="firstname" type="name" name="name" id="name" placeholder="Your first name" maxlength="50" class="sm:w-4/6 input-theme" />
                    </div>
                </div>

                <div class="sm:flex items-end mb-2">
                    <p class="font-semibold sm:w-2/6 sm:text-right pr-2 mb-2 sm:mb-0">{{ editBTN ? "last name :" : "full name :" }}</p>
                    <div class="w-full sm:w-4/6 ">
                        <p class="text-xl font-light break-words" v-show="!editBTN">{{ $store.getters.userInfo.userFirstName }} {{ $store.getters.userInfo.userLastName }}</p>
                        <input v-show="editBTN" v-model="lastname" ref="lastname" type="name" name="name" id="name" placeholder="Your last name" maxlength="50" class="sm:w-4/6 input-theme" />
                    </div>
                </div>
                <div class="sm:flex items-end mb-2">
                    <p class="font-semibold sm:w-2/6 sm:text-right pr-2 mb-2 sm:mb-0">email address :</p>
                    <div class="w-full sm:w-4/6 ">
                        <p class="text-xl font-light break-words" v-show="!editBTN">{{ $store.getters.userInfo.email }}</p>
                        <input v-show="editBTN" v-model="email" ref="email" type="email" name="email" id="email" placeholder="your@email.com" maxlength="50" class="sm:w-4/6 input-theme" />
                    </div>
                </div>

                <!-- <div class="sm:flex items-end mb-2">
                    <p class="font-semibold sm:w-2/6 sm:text-right pr-2">password :</p>
                    <div class="flex w-full sm:w-4/6 justify-between items-center">
                        <p class="sm:w-3/6 text-xl font-light" v-show="!editPassword">*********</p>
                        <input v-show="editPassword" type="password" placeholder="ylg@company.com" class="sm:w-4/6 input-theme" />
                        <div class="w-1/6 mx-4">
                            <button type="button" @click="editPassword = !editPassword" class="rounded-md text-white px-3 py-2 bg-primary">{{ editPassword ? "cancel" : "edit" }}</button>
                        </div>
                    </div>
                </div> -->
            </div>

            <div class="mt-7 flex justify-end" v-if="editBTN">
                <button type="reset" class="px-4 py-2 cancel-btn" @click="editBTN = false">cancel</button>
                <button type="submit" class="px-6 py-2 btn ml-3">Save</button>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            editBTN: false,

            firstname: "",
            lastname: "",
            email: "",

            invalid: {
                firstname: true,
                lastname: true,
                email: true,
            },
        };
    },
    methods: {
        update() {
            this.invalid.email = this.email == "" ? false + this.$refs.email.focus() : true;
            this.invalid.lastname = this.lastname == "" ? false + this.$refs.lastname.focus() : true;
            this.invalid.firstname = this.firstname == "" ? false + this.$refs.firstname.focus() : true;
            if (this.invalid.email && this.invalid.lastname && this.invalid.firstname) {
                console.log(this.firstname + this.lastname + this.email);
            }
        },
    },
};
</script>

<style></style>
