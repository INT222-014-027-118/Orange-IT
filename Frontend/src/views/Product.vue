<template>
    <div class="" v-if="showItem">
        <div class="mx-auto max-w-7xl sm:mt-5 dark:bg-gray-700">
            <div class="md:mt-0 md:px-5 lg:mx-auto grid gap-3 grid-cols-3 ">
                <div class="py-0 lg:py-5 mx-0 col-span-3 lg:col-span-2">
                    <Carousel :images="product.images" />
                </div>

                <div class="px-1 py-5 sm:px-16 md:px-20 lg:p-5 col-span-3 lg:col-span-1 my-5 bg-white rounded-md shadow-md">
                    <p class="px-2 text-primary">Brand : <span class="text-sm font-light"></span></p>
                    <p class="px-2 py-3 border-b border-black text-2xl font-semibold dark:border-gray-100 mb-2">{{ product_name }}</p>
                    <div class="px-2 sm:px-3 space-y-3 lg:space-y-3">
                        <p class="text-2xl text-red-500 font-bold">฿ {{ product.price }}</p>
                        <p class="text-sm font-light">Product ID: {{ product.id }}</p>
                        <div class="bg-primary text-white px-4 py-1 inline-block text-xs rounded-sm">Discount 99%</div>
                        <div class="w-full">
                            <p class="text-sm">color</p>
                            <div class="w-full flex flex-wrap py-1">
                                <label :for="color.id" v-for="color in product.colors" :key="color" class="flex flex-col items-center">
                                    <input
                                        :id="color.id"
                                        type="radio"
                                        name="color"
                                        class="w-8 h-8 my-2 mx-2  rounded-full form-input ring-transparent ring-4 ring-offset-2 focus:ring-4 focus:ring-offset-2 active:ring-secondary checked:ring-primary"
                                        :style="{
                                            backgroundColor: `#${color.hexCode}`,
                                        }"
                                        :value="color"
                                        v-model="colorPick"
                                    />
                                    <span class="text-xs"> {{ color.label }}</span>
                                </label>
                            </div>
                        </div>

                        <button
                            class="bg-primary text-white select-none block py-3 px-4 text-center w-full rounded-md hover:shadow-md hover:bg-primaryfocus cursor-pointer fixed sm:static bottom-12 left-0 z-40"
                            @click="addCartItem()"
                        >
                            Add to Cart
                        </button>
                        <div class="text-xl text-green-600 font-bold flex items-center"><span class="material-icons"> check_circle_outline </span> {{ stockCheck }}</div>
                    </div>
                </div>

                <div class="my-5 w-full col-span-3">
                    <div class="p-1 sm:px-16 md:px-20 lg:px-5">
                        <p class="font-semibold text-xl px-2">Attribute: {{ product_name }}</p>
                        <div class="overflow-hidden rounded p-1">
                            <table class="min-w-full bg-white">
                                <tbody class="text-gray-700">
                                    <tr :class="attribute % 2 == 0 ? 'bg-gray-100' : 'bg-gray-50'" v-for="attribute in [0, 1, 2, 3, 4]" :key="attribute">
                                        <td class="w-1/3 text-left py-3 px-4">title</td>
                                        <td class="w-1/3 text-left py-3 px-4">Smith</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="p-1 mb-5 w-full col-span-3 sm:px-16 md:px-20 lg:px-5">
                    <p class="font-semibold text-xl px-2">Rating: {{ product_name }}</p>
                    <Raring />
                </div>
                <Review class="col-span-3 lg:col-span-1 p-1 sm:px-16 md:px-20 lg:px-5 mb-5" />
                <div class="col-span-3 lg:col-span-2 sm:px-16 md:px-20 lg:px-5 my-6">
                    <!-- <p class="font-semibold text-xl">Customer reviews</p> -->
                    <Comments />
                    <Comments />
                    <Comments />
                </div>
            </div>
        </div>
        <Loading v-show="loading" />
    </div>
</template>

<script>
import axios from "axios";
import Review from "../components/Review.vue";
import Comments from "../components/Comment.vue";
import Raring from "../components/Rating.vue";
export default {
    name: "Item",
    components: {
        // Carousel,
        Review,
        Comments,
        Raring,
    },
    props: {
        product_name: String,
        productId: String,
    },
    data() {
        return {
            showItem: true,
            images:[],
            product: {},
            reviews: {
                totalCount: 123,
                average: 4,
            },
            colorPick: {},
            loading: false,
            api: "http://52.187.10.17/orange-it/product",
        };
    },
    methods: {
        selectColor(index) {
            console.log(index);
        },
        scrollToTop() {
            window.scrollTo(0, 0);
        },
        addCartItem() {
            this.loading = true;
            setTimeout(() => {
                this.loading = false;
                this.$swal({
                    title:
                        "<div class='flex items-center justify-center text-primary'><span class='material-icons pt-1 px-1 text-3xl'> shopping_cart </span><span class='font-bold text-2xl'>Shopping Cart</span></div>",
                    text: "The item has been added to your shopping cart",
                    showCloseButton: true,
                    confirmButtonColor: "#EC6907",
                    backdrop: "rgba(75, 85, 99, 0.4)",
                    willOpen: () => {
                        // this.$swal.showLoading();
                        setTimeout(() => {
                            let itemDumy = {
                                name: this.product_name,
                                price: this.price,
                                quantity: 1,
                            };
                            this.$store.dispatch("addCartItem", itemDumy);
                        }, 500);
                    },
                });
            }, 500);
        },
    },
    mounted() {
        this.scrollToTop();
        // console.log(this.$store.getters.product);
        // setTimeout(() => {
        //     this.$store.dispatch("loadProduct", Number(this.productId));
        //     this.product =  this.$store.getters.product;
        //     console.log(this.product);

        // }, 5000);
    },
    computed: {
        stockCheck() {
            if (this.product.quantityStock == 0) {
                return "out of stock";
            } else if (this.product.quantityStock < 20) {
                return "low stock";
            } else {
                return "in stock";
            }
        },
    },
    async created() {
        this.product = await axios.get(`${this.api}/${this.productId}`).then((res) => res.data);
        for(let i=0; i < this.product.images.length ; i++){
            this.images.push(`http://52.187.10.17/orange-it/image/get/${this.product.images[i].source}`) 
        }
    },
};
</script>
<style scoped>
/* [type="radio"]:checked {
    background-image: url("data:image/svg+xml,%3csvg viewBox='0 0 16 16' fill='red' xmlns='http://www.w3.org/2000/svg'%3e%3ccircle cx='8' cy='8' r='3'/%3e%3c/svg%3e");
} */
</style>
