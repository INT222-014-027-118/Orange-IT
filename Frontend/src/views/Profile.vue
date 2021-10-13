<template>
    <div class="flex p-1 py-3 mx-auto max-w-7xl ">
        <div class="fixed md:hidden" @click="showFull = !showFull" v-show="!showFull">back</div>
        <div :class="[showFull ? 'w-full md:w-3/12' : 'hidden md:block']" class="mx-1 w-full  md:w-3/12 top-20 px-3 py-3 bg-white rounded-md shadow-md h-full">
            <div class="">
                <div class="flex items-center ">
                    <div class="self-start py-1">
                        <!-- <img
                        class="object-cover w-20 h-20 p-1 border-2 rounded-full border-primary md:w-24 md:h-24"
                        src="https://images.unsplash.com/photo-1502791451862-7bd8c1df43a7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=700&q=80"
                        alt="profile"
                    /> -->
                        <span class="w-14 h-14 text-5xl text-center border-2 rounded-full material-icons border-primary"> person </span>
                    </div>
                    <div class="w-full ml-2 overflow-hidden overflow-ellipsis">
                        <div class="md:flex md:flex-wrap md:items-center">
                            <h2 class="inline-block mb-2 font-light lg:text-xl sm:mr-5 sm:mb-0 truncate">
                                mrtravlerrr_dasdqweewqe
                            </h2>
                            <!-- <a href="#" class="block px-2 py-0.5 text-sm font-semibold text-center text-white bg-primary rounded md:inline-block">Edit</a> -->
                            <router-link
                                :to="{
                                    name: 'manageProfile',
                                    params: { manage: 'account' },
                                }"
                                @click="showFull = !showFull"
                                class="block px-2 py-0.5 text-sm font-semibold text-center text-white bg-primary rounded md:inline-block"
                                >Edit</router-link
                            >
                        </div>
                    </div>
                </div>
                <div class="px-1 py-3 border-b">
                    <p class="text-base md:text-xl uppercase font-semibold">point : 99999</p>
                </div>
            </div>

            <div class="">
                <div class="py-3 px-1 space-y-3">
                    <router-link
                        :to="{
                            name: 'manageProfile',
                            params: { manage: 'account' },
                        }"
                        @click="showFull = !showFull"
                        class="hover:text-primary block font-semibold capitalize"
                        :class="[this.$route.name === 'manageProfile' ? 'md:text-primary hover:text-primaryfocus' : '']"
                    >
                        my account
                    </router-link>
                    <div class="">
                        <router-link
                            :to="{
                                name: 'Address',
                                params: { address: 'address' },
                            }"
                            @click="showFull = !showFull"
                            class="hover:text-primary block font-semibold capitalize"
                            :class="[this.$route.name === 'Address' ? 'md:text-primary hover:text-primaryfocus' : '']"
                        >
                            address
                        </router-link>
                    </div>
                    <div class="">
                        <router-link
                            :to="{
                                name: 'purchase',
                                params: { purchaseDetail: 'purchase' },
                            }"
                            @click="showFull = !showFull"
                            class="hover:text-primary block font-semibold capitalize"
                            :class="[this.$route.name === 'purchase' ? 'md:text-primary' : '']"
                        >
                            purchase
                        </router-link>
                    </div>
                </div>
            </div>
        </div>

        <router-view class="mx-1 top-20 px-3 py-3 bg-white rounded-md shadow-md h-full" :class="[!showFull ? 'block  w-full md:w-9/12' : 'hidden md:block md:w-9/12']" />
    </div>
</template>

<script>
export default {
    data() {
        return {
            showFull: true,
            showMore: true,
        };
    },
};
</script>

<style></style>
