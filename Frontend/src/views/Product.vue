<template>
    <div class="">
        <div class="mx-auto max-w-7xl">
            <div class="lg:mx-auto grid gap-3 lg:py-3 grid-cols-3 ">
                <div class="col-span-3 lg:col-span-2">
                    <Carousel :images="images" :reImage="reImage" @endload="endload" />
                </div>

                <div class="px-1 py-5 sm:px-16 md:px-20 lg:p-5 col-span-3 lg:col-span-1 bg-white dark:bg-dark_tertiary rounded-md shadow-md">
                    <div class="px-2 sm:px-3 md:px-0 space-y-3 flex flex-wrap content-between h-full">
                        <div class="space-y-3 flex flex-col w-full">
                            <div>
                                <p class="px-2 text-primary">
                                    {{ product.brandName }}
                                </p>
                                <p class="px-2 py-2 border-b border-black text-xl font-semibold dark:border-gray-100">{{ product.productName }}</p>
                            </div>
                            <p class="px-2 text-2xl text-red-500 font-bold">{{ productPrice }}</p>
                            <p class="px-2 text-sm font-light">Product ID: {{ product.id }}</p>
                            <div class="inline-flex" v-if="false">
                                <div class="bg-primary text-white px-4 py-1 text-xs rounded-sm">Discount 99%</div>
                            </div>
                        </div>
                        <div class="space-y-3 flex flex-col w-full">
                            <div class="w-full px-2">
                                <p class="text-sm">color:</p>
                                <div class="w-full flex flex-wrap py-1">
                                    <label :for="color.id" v-for="color in product.colors" :key="color" class="flex flex-col items-center cursor-pointer">
                                        <input
                                            :id="color.id"
                                            type="radio"
                                            name="color"
                                            class="w-8 h-8 my-2 mx-2 border rounded-full cursor-pointer form-input ring-transparent ring-2 ring-offset-1 ring-offset-white dark:ring-offset-dark_tertiary focus:border-gray-500 focus:ring-2 focus:ring-offset-1 active:ring-secondary checked:ring-primary"
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
                            <div
                                class="fixed flex bottom-0 z-10 left-0 w-full px-2 pt-1 pb-16 bg-white dark:bg-dark_secondary sm:dark:bg-dark_tertiary sm:pb-7 md:pb-0 md:pt-2 md:px-0 sm:static border-t sm:border-0 dark:border-gray-600"
                            >
                                <button
                                    @click="addToCompare"
                                    :disabled="this.$store.getters.countCompareProducts >= 2 || this.$store.getters.compareProductsWithId == product.id"
                                    :class="[
                                        this.$store.getters.countCompareProducts == 2 || this.$store.getters.compareProductsWithId == product.id
                                            ? 'cursor-not-allowed bg-secondary bg-opacity-50'
                                            : 'bg-secondary hover:bg-yellow-500',
                                    ]"
                                    class="text-white px-2 py-1 sm:px-2 sm:py-3 mr-2 mb-1 sm:mb-0 rounded-md flex flex-col sm:flex-row items-center justify-center whitespace-nowrap tracking-tighter transition-colors"
                                >
                                    <span class="material-icons mr-1"> compare_arrows </span>
                                    <span class="text-xs sm:text-base"> Compare ({{ this.$store.getters.countCompareProducts }})</span>
                                </button>
                                <button
                                    class="w-full px-1 py-1 mb-1 sm:mb-0 flex flex-col sm:flex-row items-center justify-center whitespace-nowrap z-40 btn"
                                    @click="addCartItem"
                                    :disabled="product.quantityStock <= 0"
                                    :class="[product.quantityStock <= 0 ? 'cursor-not-allowed' : '']"
                                >
                                    <span class="material-icons mr-1"> add_shopping_cart </span>
                                    <span class="text-xs sm:text-base">Add to Cart</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="my-5 w-full col-span-3">
                    <p class="font-semibold text-xl p-2 sm:px-16 md:px-20 lg:px-5 bg-white dark:bg-dark_tertiary shadow-md rounded-md capitalize">
                        description:
                    </p>
                    <p class="px-5 md:px-8 py-5 leading-relaxed text-left whitespace-pre-wrap" :class="[product.description == '' ? 'text-opacity-50' : '']">
                        {{ product.description == "" ? "No description" : product.description }}
                    </p>
                </div>

                <div class="mb-5 w-full col-span-3">
                    <p class="font-semibold text-xl p-2 sm:px-16 md:px-20 lg:px-5 bg-white dark:bg-dark_tertiary shadow-md rounded-md capitalize">
                        attribute:
                    </p>
                    <div class="overflow-hidden m-2 sm:mx-16 md:mx-20 lg:mx-48">
                        <table class="min-w-full bg-white">
                            <tbody class="text-black dark:text-gray-100 ">
                                <tr
                                    :class="index % 2 == 0 ? 'bg-gray-100 dark:bg-dark_primary dark:bg-opacity-90' : 'bg-gray-50 dark:bg-dark_secondary dark:bg-opacity-90'"
                                    v-for="(Attribute, index) in Attribute"
                                    :key="index"
                                >
                                    <td class="w-1/2 text-left py-3 px-4 font-bold tracking-wide">{{ Attribute.attribute }} :</td>
                                    <td class="w-1/2 text-left py-3 px-4">{{ Attribute.value }}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="mb-5 w-full col-span-3">
                    <p class="font-semibold text-xl p-2 sm:px-16 md:px-20 lg:px-5 bg-white dark:bg-dark_tertiary shadow-md rounded-md capitalize">
                        rating:
                    </p>
                    <Rating :ratings="ratings" />
                </div>
                <div class="col-span-3  p-2 sm:px-16 md:px-20 lg:px-5 bg-white dark:bg-dark_tertiary shadow-md rounded-md flex flex-nowrap justify-between">
                    <div>
                        <p class="font-semibold text-xl whitespace-nowrap">Customer reviews</p>
                        <p class="font-medium text-primary whitespace-nowrap">{{ reviews.length }} reviews</p>
                    </div>
                    <div class="mx-4 flex items-center">
                        <div class="flex items-center">
                            <span
                                class="material-icons mb-1"
                                v-for="rating in [0, 1, 2, 3, 4]"
                                :key="rating"
                                :class="[avgRateStar > rating ? 'text-yellow-400' : 'text-gray-200', 'h-5 w-5 flex-shrink-0']"
                                aria-hidden="true"
                            >
                                star
                            </span>
                            <span class="ml-3"> {{ avgRateStar.toFixed(2) }}</span>
                        </div>
                        <p class="sr-only">{{ reviews.average }} out of 5 stars</p>
                    </div>
                </div>
                <div class="col-span-3 p-1 sm:px-16 md:px-20 lg:mt-6 mb-16 lg:px-36">
                    <Comments v-for="review in reviews" :key="review" :review="review" />
                </div>
            </div>
        </div>
        <Loading v-show="loading" />
    </div>
</template>

<script>
import axios from "axios";
import Comments from "../components/product/Comment.vue";
import Rating from "../components/product/Rating.vue";
export default {
    name: "Item",
    components: {
        Comments,
        Rating,
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
            Attribute: [],
            reviews: [],
            ratings: [],
            colorPick: null,
            loading: false,
            api: `${process.env.VUE_APP_API}/product`,
        };
    },
    methods: {
        scrollToTop() {
            window.scrollTo(0, 0);
        },
        endload() {
            this.loading = false;
        },
        addCartItem() {
            if (!this.colorPick) {
                alert("Please choose color.");
                return;
            }
            this.$swal({
                title:
                    "<div class='flex items-center justify-center text-primary'><span class='material-icons pt-1 px-1 text-3xl'> shopping_cart </span><span class='font-bold text-2xl'>Shopping Cart</span></div>",
                text: "The item has been added to your shopping cart",
                showCloseButton: true,
                confirmButtonColor: "#EC6907",
                backdrop: "rgba(31, 41, 55, 0.5)",
                willOpen: () => {
                    // this.$swal.showLoading();
                    if (this.$store.getters.isLogin) {
                        let cartItem = {
                            id: 1,
                            quantity: 1,
                            productId: Number(this.productId),
                            userId: localStorage.getItem("userId"),
                            colorId: this.colorPick.id,
                        };
                        this.$store.commit("addCartItem", cartItem);
                    } else {
                        let cartItem = {
                            quantity: 1,
                            productCart: {
                                id: this.product.id,
                                productName: this.product.productName,
                                price: this.product.price,
                                quantityStock: this.product.quantityStock,
                                discount: null,
                                images: [this.product.images[0]],
                            },
                            colors: this.colorPick,
                            userId: null,
                        };
                        this.$store.commit("addCartItem", cartItem);
                        localStorage.setItem("cart", JSON.stringify(this.$store.getters.cart));
                    }
                },
            });
        },
        addToCompare() {
            this.$store.commit("setCompareProducts", this.product);
        },
    },
    computed: {
        stockCheck() {
            let stock = {};
            if (this.product.quantityStock <= 0) {
                stock = { class: "text-red-500", icon: "cancel", text: "out of stock" };
                return stock;
            } else if (this.product.quantityStock < 10) {
                stock = { class: "text-yellow-500", icon: "error_outline", text: `low stock (${this.product.quantityStock} pieces)` };
                return stock;
            } else {
                stock = { class: "text-green-600", icon: "check_circle", text: "in stock" };
                return stock;
            }
        },
        productPrice() {
            return new Intl.NumberFormat("th-TH", {
                style: "currency",
                currency: "THB",
                minimumFractionDigits: 0,
                maximumFractionDigits: 2,
            }).format(this.product.price);
        },
        avgRateStar() {
            let avgStar =
                this.reviews.length > 0
                    ? this.reviews
                          .map((item) => {
                              return item.star;
                          })
                          .reduce((previouItem, currentItem) => {
                              return Number(previouItem) + Number(currentItem);
                          }) / this.reviews.length
                    : 0;
            return avgStar;
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
        this.Attribute = this.product.attributes.map((att) => {
            return {
                id: att.id,
                attribute: att.attribute,
                value: this.product.productsHasAttributes.find((value) => {
                    return value.attributeId == att.id;
                }).attribute_value,
            };
        });

        this.reviews = await axios.get(`${process.env.VUE_APP_API}/review/getByProductId/${this.productId}`).then((res) => {
            return res.data;
        });

        this.ratings = await axios.get(`${process.env.VUE_APP_API}/rating/listByProductId/${this.productId}`).then((res) => {
            return res.data;
        });
        // console.log(this.ratings);

        this.images = await this.product.images.map((img) => {
            return `${process.env.VUE_APP_API}/image/get/${img.source}`;
        });
        this.colorPick = this.product.colors[0];
    },
};
</script>
<style scoped></style>
