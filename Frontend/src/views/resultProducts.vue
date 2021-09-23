<template>
    <div class="container flex p-1 py-11 lg:py-5 mx-auto lg:max-w-7xl ">
        <div class="fixed left-0 z-30 mx-2 lg:w-3/12 top-12 sm:top-20 lg:static lg:bg-transparent">
            <div class="lg:hidden">
                <button class="material-icons p-2 bg-white rounded-md shadow-md select-none cursor-pointer " @click="showFilter = !showFilter">
                    filter_list
                </button>
            </div>
            <div class="px-3 pb-3 bg-white rounded-md shadow-md select-none w-52 lg:w-full sh" :class="[{'hidden' : showFilter}, 'lg:block']">
                <div class="border-b-2">
                    <div class="flex items-center justify-between py-3 text-gray-600 cursor-pointer hover:text-black" @click="showCat = !showCat">
                        <span class="font-semibold">Category</span> <span class="text-base font-bold">+</span>
                    </div>
                    <div class="p-1 mb-3 space-y-2 rounded-md bg-gray-50" v-show="showCat">
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="headphone" /> headphone</label
                        >
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="speaker" /> speaker</label
                        >
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="keyboard" /> keyboard</label
                        >
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="mouse" /> mouse</label
                        >
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="computers" /> computers</label
                        >
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="gaming gear" /> gaming gear</label
                        >
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="smart gadget" /> smart gadget</label
                        >
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="moblie accessories" /> moblie accessories</label
                        >
                    </div>
                </div>
                <div class="border-b-2">
                    <div class="flex items-center justify-between py-3 text-gray-600 cursor-pointer hover:text-black" @click="showtype = !showtype">
                        <span class="font-semibold">Type</span> <span class="text-base font-bold">+</span>
                    </div>
                    <div class="p-1 mb-3 space-y-2 rounded-md bg-gray-50" v-show="showtype">
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="" />
                        </label>
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="" />
                        </label>
                        <label id="" class="flex items-center px-2 py-1 rounded-md hover:bg-gray-200"
                            ><input type="checkbox" class="mr-4 rounded-md form-checkbox" v-model="categorysName" value="" />
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="container lg:max-w-7xl sm:px-5 md:px-0.5 mx-auto grid gap-1 md:gap-2 lg:gap-3 grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 col-span-4">
            <router-link
                :to="{
                    name: 'Product',
                    params: { product_name: product.productName, productId: product.id, price: product.price, brand: product.brandName },
                }"
                v-for="product in $store.getters.products"
                :key="product.id"
            >
                <BaseProduct :product="product" />
            </router-link>
            <router-link
                :to="{
                    name: 'Product',
                    params: { product_name: product.productName, productId: product.id, price: product.price, brand: product.brandName },
                }"
                v-for="product in $store.getters.products"
                :key="product.id"
            >
                <BaseProduct :product="product" />
            </router-link>
            <router-link
                :to="{
                    name: 'Product',
                    params: { product_name: product.productName, productId: product.id, price: product.price, brand: product.brandName },
                }"
                v-for="product in $store.getters.products"
                :key="product.id"
            >
                <BaseProduct :product="product" />
            </router-link>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            showFilter: true,
            showCat: false,
            showtype: false,
            categorysName: [],
        };
    },
    props: {
        categoryName: String,
    },
    created() {
        this.categorysName.push(this.categoryName);
    },
};
</script>

<style></style>
