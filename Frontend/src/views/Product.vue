<template>
    <div class="">
        <div class="mx-auto max-w-7xl sm:mt-5 dark:bg-gray-700">
            <div class="md:mt-0 md:px-5 lg:mx-auto grid gap-3 grid-cols-3 ">
                <div class="py-0 lg:py-5 mx-0 col-span-3 lg:col-span-2">
                    <Carousel :images="images" :reImage="reImage" @endload="endload" />
                </div>

                <div class="px-1 py-5 sm:px-16 md:px-20 lg:p-5 col-span-3 lg:col-span-1 my-5 bg-white rounded-md shadow-md">
                    <p class="px-2 text-primary">
                        {{ product.brandName }}
                    </p>
                    <p class="px-2 py-2 border-b border-black text-2xl font-semibold dark:border-gray-100 mb-2">{{ productName }}</p>
                    <div class="px-2 sm:px-3 space-y-3 flex flex-col">
                        <p class="text-2xl text-red-500 font-bold">฿ {{ product.price }}</p>
                        <p class="text-sm font-light">Product ID: {{ product.id }}</p>
                        <div class="inline-flex">
                            <div class="bg-primary text-white px-4 py-1 text-xs rounded-sm">Discount 99%</div>
                        </div>
                        <div class="w-full">
                            <p class="text-sm">color :</p>
                            <div class="w-full flex flex-wrap py-1">
                                <label :for="color.id" v-for="color in product.colors" :key="color" class="flex flex-col items-center">
                                    <input
                                        :id="color.id"
                                        type="radio"
                                        name="color"
                                        class="w-8 h-8 my-2 mx-2 border rounded-full form-input ring-transparent ring-4 ring-offset-2 focus:border-gray-500 focus:ring-4 focus:ring-offset-2 active:ring-secondary checked:ring-primary"
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

                        <div class="text-xl font-bold flex items-center" :class="[stockCheck.class]">
                            <span class="material-icons-outlined mr-1"> {{ stockCheck.icon }} </span> {{ stockCheck.text }}
                        </div>
                        <div class="fixed bottom-0 left-0 w-full px-5 py-2 bg-white pb-14 md:pb-0 md:px-0 md:static">
                            <button class="w-full p-3 text-center text-white rounded-md bg-primary hover:bg-secondary z-40" @click="addCartItem">Add to Cart</button>
                        </div>
                    </div>
                </div>

                <div class="my-5 w-full col-span-3">
                    <p class="font-semibold text-xl p-2 sm:px-16 md:px-20 lg:px-5 bg-white shadow-md rounded-md capitalize">
                        description: <span class="capitalize text-sm">{{ productName }}</span>
                    </p>
                    <p class="px-5 md:px-8 py-5 leading-relaxed" :class="[product.description == '' ? 'text-black text-opacity-50' : '']">
                        {{ product.description == "" ? "No description" : product.description }}
                    </p>
                </div>

                <div class="mb-5 w-full col-span-3">
                    <p class="font-semibold text-xl p-2 sm:px-16 md:px-20 lg:px-5 bg-white shadow-md rounded-md capitalize">
                        attribute: <span class="capitalize text-sm">{{ productName }}</span>
                    </p>
                    <div class="overflow-hidden rounded p-2 sm:px-16 md:px-20 lg:px-5">
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

                <div class="mb-5 w-full col-span-3">
                    <p class="font-semibold text-xl p-2 sm:px-16 md:px-20 lg:px-5 bg-white shadow-md rounded-md capitalize">
                        rating: <span class="capitalize text-sm">{{ productName }}</span>
                    </p>
                    <Raring />
                </div>
                <p class="col-span-3 font-semibold text-xl p-2 sm:px-16 md:px-20 lg:px-5 bg-white shadow-md rounded-md">Customer reviews</p>
                <Review class="col-span-3 lg:col-span-1 p-1 sm:px-16 md:px-20 lg:px-5 mb-5" />
                <div class="col-span-3 lg:col-span-2 sm:px-16 md:px-20 lg:px-5 my-6">
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
        Review,
        Comments,
        Raring,
    },
    props: {
        productName: String,
        productId: String,
    },
    data() {
        return {
            showItem: true,
            reImage: false,
            images: [],
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
        endload() {
            this.loading = false;
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
                                name: this.productName,
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
    computed: {
        stockCheck() {
            let stock = {};
            if (this.product.quantityStock == 0) {
                stock = { class: "text-red-500", icon: "cancel", text: "out of stock" };
                return stock;
            } else if (this.product.quantityStock < 10) {
                stock = { class: "text-yellow-500", icon: "error_outline", text: `low stock (${this.product.quantityStock} piece)` };
                return stock;
            } else {
                stock = { class: "text-green-600", icon: "check_circle", text: "in stock" };
                return stock;
            }
        },
    },
    mounted() {
        this.scrollToTop();
    },
    async created() {
        this.loading = true;
        this.product = await axios.get(`${this.api}/${this.productId}`).then((res) => {
            return res.data;
        });
        this.images = await this.product.images.map((img) => {
            return `http://52.187.10.17/orange-it/image/get/${img.source}`;
        });
    },
};
</script>
<style scoped></style>
