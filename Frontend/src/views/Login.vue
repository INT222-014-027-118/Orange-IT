<template>
    <div class="flex items-cente" v-if="$store.getters.userInfo === null">
        <div class="container mx-auto">
            <div class="max-w-md mx-auto my-10">
                <div class="text-center">
                    <h1 class="my-3 text-3xl font-semibold text-gray-700 dark:text-gray-200">Sign in</h1>
                    <p class="text-gray-500 dark:text-gray-400">Sign in to access your account</p>
                </div>
                <div class="m-7">
                    <form @submit.prevent="login">
                        <div class="mb-6">
                            <label for="username" class="block mb-2 text-sm text-gray-600 dark:text-gray-400">Username</label>
                            <input v-model="username" type="text" name="username" id="username" placeholder="Username" class="input-theme" />
                        </div>
                        <div class="mb-6">
                            <label for="password" class="block mb-2 text-sm text-gray-600 dark:text-gray-400">Password</label>
                            <input v-model="password" type="password" name="password" id="password" placeholder="Your Password" class="input-theme" />
                        </div>
                        <div class="mb-6">
                            <button type="submit" class="w-full px-3 py-4 text-white bg-primary rounded-md focus:bg-secondary focus:outline-none">Sign in</button>
                        </div>
                        <p class="text-sm text-center text-gray-400 ">
                            Don&#x27;t have an account yet?
                            <a @click="$router.push('/register')" class="cursor-pointer text-primary focus:outline-none focus:underline">Sign up</a>
                        </p>
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
            username: "",
            password: "",
        };
    },
    methods: {
        login() {
            axios
                .post(`${process.env.VUE_APP_API}/authenticate`, { userName: this.username, userPassword: this.password })
                .then((response) => {
                    if (response.status === 200) {
                        let userinfo = response.data;
                        localStorage.setItem("token", userinfo.jwtToken);
                        localStorage.setItem("userId", userinfo.user.id);
                        this.$store.commit("setUserInfo", userinfo.user);
                        let isAdmin = userinfo.user.role[0].name === "Admin" ? true : false;
                        this.$store.commit("setIsAdmin", isAdmin);
                        this.$store.dispatch("loadCartData");
                        this.$router.push("/");
                        return isAdmin;
                    }
                })
                .then((isAdmin) => {
                    if (isAdmin) {
                        this.$router.push("/admin");
                    }
                })
                .catch((error) => {
                    alert("Fail!");
                    console.log(error);
                });
        },
    },
};
</script>

<style></style>
