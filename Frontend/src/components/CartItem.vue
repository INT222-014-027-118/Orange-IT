.<template>
    <div class="flex w-full p-1 md:px-2 md:py-2 mb-2 md:mb-3 overflow-auto bg-white dark:bg-dark_tertiary rounded-md shadow-md">
        <div
            class="flex-shrink-0 w-14 h-14 overflow-hidden border border-gray-200 rounded-md sm:w-24 sm:h-24 md:w-32 md:h-32 lg:w-44 lg:h-44 cursor-pointer"
            @click="
                $router.push({
                    name: 'Product',
                    params: { productName: product.productCart.productName == '' ? 'Product name is not defined' : product.productCart.productName, productId: product.productCart.id },
                })
            "
        >
            <img :src="image" class="object-cover object-center w-full h-full" alt="Product image" />
        </div>
        <div class="flex justify-between w-full ml-1 sm:ml-3 sm:p-2">
            <div class="flex flex-col w-8/12 sm:flex sm:justify-between sm:flex-row">
                <div class="flex flex-col justify-between w-11/12">
                    <div>
                        <p class="text-base tracking-tight">{{ product.productCart.productName }}</p>
                        <p class="text-xs sm:text-base text-gray-600 dark:text-gray-300">color: {{ product.colors.label }}</p>
                    </div>
                    <!-- <div> -->
                    <div class="font-semibold flex items-center mt-3" :class="[stockCheck.class]">
                        <span class="material-icons-outlined mr-1"> {{ stockCheck.icon }} </span> {{ stockCheck.text }}
                    </div>
                    <!-- </div> -->
                </div>
                <div>
                    <form @submit.prevent class="w-1/2 sm:w-full my-2 sm:my-0">
                        <div class="flex items-center justify-center rounded-md">
                            <select
                                name="quantity"
                                id="quantity"
                                v-model="quantity"
                                class="self-start p-1 w-full md:w-14 border dark:border-gray-600 rounded-md cursor-pointer focus:outline-none"
                                @change="editQuantity"
                            >
                                <option class="overflow-auto" v-for="choice in choices" :key="choice" :value="choice" :id="`choice-${choice}`">{{ choice }}</option>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
            <div class="flex flex-col justify-between w-4/12 text-right">
                <div class="flex flex-col">
                    <span class="text-xl md:text-2xl text-primary font-semibold">{{ productPrice(product.productCart.price * product.quantity) }}</span>
                    <span class="text-gray-600 dark:text-gray-300 tracking-tighter line-through" v-if="quantity >= 2">{{ productPrice(product.productCart.price) }}</span>
                </div>
                <div class="inline-flex justify-end">
                    <button class="px-1 py-2 font-semibold text-red-500 sm:px-2 sm:py-0 rounded-md " @click="remove">remove</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        product: Object,
        index: Number,
    },
    data() {
        return {
            image: "",
            quantity: 0,
        };
    },
    methods: {
        remove() {
            this.$store.commit("removeCartItem", this.index);
        },
        editQuantity() {
            let payload = { index: this.index, quantity: Number(this.quantity) };
            this.$store.commit("editQuantity", payload);
        },
        productPrice(price) {
            return new Intl.NumberFormat("th-TH", {
                style: "currency",
                currency: "THB",
                minimumFractionDigits: 0,
                maximumFractionDigits: 2,
            }).format(price);
        },
    },
    computed: {
        stockCheck() {
            let stock = {};
            if (this.product.productCart.quantityStock <= 0) {
                stock = { class: "text-red-500", icon: "cancel", text: "out of stock" };
                return stock;
            } else if (this.product.productCart.quantityStock < 10) {
                stock = { class: "text-yellow-500", icon: "error_outline", text: `low stock (${this.product.productCart.quantityStock} pieces)` };
                return stock;
            } else {
                stock = { class: "text-green-600", icon: "check_circle", text: "in stock" };
                return stock;
            }
        },
        choices() {
            let option = [];
            let maxOption = this.product.productCart.quantityStock > 10 ? 10 : this.product.productCart.quantityStock;
            for (let index = 1; index <= maxOption; index++) {
                option.push(index);
            }
            return option;
        },
    },
    async created() {
        this.image = `${process.env.VUE_APP_API}/image/get/${this.product.productCart.images[0].source}`;
        this.quantity = await this.product.quantity;
        document.getElementById(`choice-${this.product.quantity}`).setAttribute("selected", "");
    },
};
</script>

<style></style>
