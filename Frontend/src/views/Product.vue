<template>
    <div class="">
        <div class="mx-auto max-w-7xl sm:mt-5 dark:bg-gray-700">
            <div class="md:mt-0 md:px-5 lg:mx-auto grid gap-3 grid-cols-3 ">
                <div class="py-0 lg:py-5 mx-0 col-span-3 lg:col-span-2">
                    <Carousel :images="images" :reImage="reImage" @endload="endload" />
                </div>

                <div class="px-1 py-5 sm:px-16 md:px-20 lg:p-5 col-span-3 lg:col-span-1 my-5 bg-white rounded-md shadow-md">
                    <p class="px-2 text-primary">
                        Brand : <span class="text-sm font-light"> {{ product.brandName }}</span>
                    </p>
                    <p class="px-2 py-2 border-b border-black text-2xl font-semibold dark:border-gray-100 mb-2">{{ productName }}</p>
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

                        <div class="fixed bottom-0 left-0 w-full px-5 py-2 bg-white pb-14 md:pb-0 md:px-0 md:static">
                            <button class="w-full p-3 text-center text-white rounded-md bg-primary hover:bg-secondary z-40">Add to Cart</button>
                        </div>
                        <div class="text-xl text-green-600 font-bold flex items-center"><span class="material-icons"> check_circle_outline </span> {{ stockCheck }}</div>
                    </div>
                </div>

                <div class="my-5 w-full col-span-3">
                    <div class="p-1 sm:px-16 md:px-20 lg:px-5">
                        <p class="font-semibold text-xl px-2">Attribute: {{ productName }}</p>
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
                    <p class="font-semibold text-xl px-2">Rating: {{ productName }}</p>
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
            if (this.product.quantityStock == 0) {
                return "out of stock";
            } else if (this.product.quantityStock < 20) {
                return "low stock";
            } else {
                return "in stock";
            }
        },
    },
    mounted() {
        this.scrollToTop();
    },
    async created() {
        // this.loading = true;
        this.product = await axios.get(`${this.api}/${this.productId}`).then((res) => {
            return res.data;
        });
        this.images = await this.product.images.map((img) => {
            return `http://52.187.10.17/orange-it/image/get/${img.source}`;
        });
    },
};
</script>
<style scoped>
/* [type="radio"]:checked {
    background-image: url("data:image/svg+xml,%3csvg viewBox='0 0 16 16' fill='red' xmlns='http://www.w3.org/2000/svg'%3e%3ccircle cx='8' cy='8' r='3'/%3e%3c/svg%3e");
} */
/* 
[type="checkbox"],
[type="radio"] {
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    padding: 0;
    -webkit-print-color-adjust: exact;
    color-adjust: exact;
    display: inline-block;
    vertical-align: middle;
    background-origin: border-box;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    flex-shrink: 0;
    height: 1rem;
    width: 1rem;
    color: #2563eb;
    background-color: #fff;
    border-color: #6b7280;
    border-width: 0px;
}

[type="checkbox"]:focus,
[type="radio"]:focus {
    outline: 2px solid transparent;
    outline-offset: 2px;
    --tw-ring-inset: var(--tw-empty);
    --tw-ring-offset-width: 2px;
    --tw-ring-offset-color: #fff;
    --tw-ring-color: #2563eb;
    --tw-ring-offset-shadow: var(--tw-ring-inset) 0 0 0 var(--tw-ring-offset-width) var(--tw-ring-offset-color);
    --tw-ring-shadow: var(--tw-ring-inset) 0 0 0 calc(2px + var(--tw-ring-offset-width)) var(--tw-ring-color);
    box-shadow: var(--tw-ring-offset-shadow), var(--tw-ring-shadow), var(--tw-shadow, 0 0 #0000);
}

[type="checkbox"]:checked,
[type="radio"]:checked {
    border-color: transparent;
    background-color: currentColor;
    background-size: 100% 100%;
    background-position: center;
    background-repeat: no-repeat;
} */
</style>
