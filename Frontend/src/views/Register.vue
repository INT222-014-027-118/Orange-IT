<template>
    <div class="flex items-center" v-if="$store.getters.userInfo === null">
        <div class="container mx-auto">
            <div class="max-w-md mx-auto my-10">
                <div class="text-center">
                    <h1 class="my-3 text-3xl font-semibold text-gray-700 dark:text-gray-200">Register</h1>
                    <p class="text-gray-500 dark:text-gray-400">Regis your account</p>
                </div>
                <div class="m-7">
                    <form @submit.prevent="register">
                        <div class="mb-6">
                            <div class="flex justify-between mb-2">
                                <label for="firstname" class="text-sm text-gray-600 dark:text-gray-400">First name</label>
                            </div>
                            <input
                                ref="firstname"
                                v-model="firstname"
                                type="text"
                                name="firstname"
                                id="firstname"
                                maxlength="50"
                                placeholder="Your first name"
                                class="input-theme"
                                :class="[invalid.firstname ? '' : 'ring-1 ring-red-500']"
                            />
                        </div>
                        <div class="mb-6">
                            <div class="flex justify-between mb-2">
                                <label for="lastname" class="text-sm text-gray-600 dark:text-gray-400">Last name</label>
                            </div>
                            <input
                                ref="lastname"
                                v-model="lastname"
                                type="text"
                                name="lastname"
                                id="lastname"
                                maxlength="50"
                                placeholder="Your last name"
                                class="input-theme"
                                :class="[invalid.lastname ? '' : 'ring-1 ring-red-500']"
                            />
                        </div>
                        <div class="mb-6">
                            <div class="flex justify-between mb-2">
                                <label for="username" class="text-sm text-gray-600 dark:text-gray-400">Username</label>
                            </div>
                            <input
                                ref="username"
                                v-model="username"
                                type="text"
                                name="username"
                                id="username"
                                maxlength="50"
                                placeholder="Your username"
                                class="input-theme"
                                :class="[invalid.username ? '' : 'ring-1 ring-red-500']"
                            />
                        </div>
                        <div class="mb-6">
                            <label for="email" class="block mb-2 text-sm text-gray-600 dark:text-gray-400">Email Address</label>
                            <input
                                ref="email"
                                v-model="email"
                                type="email"
                                name="email"
                                id="email"
                                maxlength="50"
                                placeholder="you@email.com"
                                class="input-theme"
                                :class="[invalid.email ? '' : 'ring-1 ring-red-500']"
                            />
                        </div>
                        <div class="mb-6">
                            <div class="flex justify-between mb-2">
                                <label for="password" class="text-sm text-gray-600 dark:text-gray-400">Password</label>
                            </div>
                            <input
                                ref="password"
                                v-model="password"
                                type="password"
                                name="password"
                                id="password"
                                maxlength="100"
                                placeholder="Your Password"
                                class="input-theme"
                                :class="[invalid.password ? '' : 'ring-1 ring-red-500']"
                            />
                        </div>
                        <div class="mb-6">
                            <div class="flex justify-between mb-2">
                                <label for="password" class="text-sm text-gray-600 dark:text-gray-400">Confirm Password</label>
                                <span v-if="!invalid.checkPass" class="text-sm text-red-500"> Password is not same</span>
                            </div>
                            <input
                                ref="checkPass"
                                v-model="confirmPassword"
                                type="password"
                                name="confirmPassword"
                                id="confirmPassword"
                                maxlength="100"
                                placeholder="Your Password"
                                class="input-theme"
                                :class="[invalid.checkPass ? '' : 'ring-1 ring-red-500']"
                            />
                        </div>

                        <div class="pt-3">
                            <button type="submit" class="w-full px-3 py-4 btn">Register</button>
                        </div>
                    </form>
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
            firstname: "",
            lastname: "",
            username: "",
            email: "",
            password: "",
            confirmPassword: "",

            invalid: {
                firstname: true,
                lastname: true,
                email: true,
                username: true,
                password: true,
                checkPass: true,
            },
        };
    },
    methods: {
        register() {
            this.invalid.checkPass = this.password === this.confirmPassword ? true : false + this.$refs.checkPass.focus();
            this.invalid.password = this.password == "" ? false + this.$refs.password.focus() : true;
            this.invalid.email = this.email == "" ? false + this.$refs.email.focus() : true;
            this.invalid.username = this.username == "" ? false + this.$refs.username.focus() : true;
            this.invalid.lastname = this.lastname == "" ? false + this.$refs.lastname.focus() : true;
            this.invalid.firstname = this.firstname == "" ? false + this.$refs.firstname.focus() : true;

            if (this.invalid.firstname && this.invalid.lastname && this.invalid.username && this.invalid.password && this.invalid.checkPass) {
                let data = {
                    id: 1,
                    username: this.username,
                    userFirstName: this.firstname,
                    userLastName: this.lastname,
                    password: this.password,
                    email: this.email,
                    points: 0,
                };
                axios
                    .post(`${process.env.VUE_APP_API}/registerNewUser`, data)
                    .then((response) => {
                        console.log(response);
                        if (response.status === 200) {
                            alert("Succeed");
                            this.$router.push("/login");
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            }
        },
    },
    created() {},
};
</script>

<style></style>
