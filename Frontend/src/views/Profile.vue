<template>
    <div class="flex p-1 py-3 mx-auto max-w-7xl ">
        <div class="mx-1 w-full md:w-3/12 top-20 px-3 py-3 bg-white rounded-md shadow-md h-full">
            <div class="">
                <div class="flex items-center ">
                    <div class="self-start py-1">
                        <!-- profile image -->
                        <!-- <img
                        class="object-cover w-20 h-20 p-1 border-2 rounded-full border-primary md:w-24 md:h-24"
                        src="https://images.unsplash.com/photo-1502791451862-7bd8c1df43a7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=700&q=80"
                        alt="profile"
                    /> -->
                        <span class="w-14 h-14 text-5xl text-center border-2 rounded-full material-icons border-primary"> person </span>
                    </div>
                    <!-- profile meta -->
                    <div class="w-full ml-2 overflow-hidden overflow-ellipsis">
                        <div class="md:flex md:flex-wrap md:items-center">
                            <h2 class="inline-block mb-2 font-light lg:text-2xl sm:mr-5 sm:mb-0">
                                mrtravlerrr_dasdqweewqe
                            </h2>
                            <!-- <a href="#" class="block px-2 py-0.5 text-sm font-semibold text-center text-white bg-primary rounded md:inline-block">Edit</a> -->
                            <router-link
                                :to="{
                                    name: 'edit',
                                    params: { editProfile: 'editProfile' },
                                }"
                                class="block px-2 py-0.5 text-sm font-semibold text-center text-white bg-primary rounded md:inline-block"
                                >Edit</router-link
                            >
                        </div>
                    </div>
                </div>
                <div class="px-1 py-2">
                    <p class="text-base md:text-xl uppercase font-semibold">point : 99999</p>
                </div>
            </div>

            <div class="border-b-2">
                <div class="py-3 px-1 text-black">
                    <span class="font-semibold">title setting</span>
                </div>
                <div class="mb-3 space-y-2 rounded-md">
                    <a class="rounded-md hover:bg-gray-100 cursor-pointer px-3 block" value="text"> sub title </a>
                    <a class="rounded-md hover:bg-gray-100 cursor-pointer px-3 block" value="text"> sub title </a>
                    <a class="rounded-md hover:bg-gray-100 cursor-pointer px-3 block" value="text"> sub title </a>
                    <a class="rounded-md hover:bg-gray-100 cursor-pointer px-3 block" value="text"> sub title </a>
                </div>
            </div>

            <div class="border-b-2">
                <div class="py-3 px-1 text-black">
                    <span class="font-semibold">title setting</span>
                </div>
                <div class="mb-3 space-y-2 rounded-md">
                    <a class="rounded-md hover:bg-gray-100 cursor-pointer px-3 block" value="text"> sub title </a>
                    <a class="rounded-md hover:bg-gray-100 cursor-pointer px-3 block" value="text"> sub title </a>
                </div>
            </div>
        </div>

        <router-view class="mx-1 w-full md:w-9/12 top-20 px-3 py-3 bg-white rounded-md shadow-md h-full" />
    </div>
</template>

<script>
export default {};
</script>

<style></style>
