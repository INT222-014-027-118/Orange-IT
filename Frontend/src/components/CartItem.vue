.<template>
    <div class="flex w-full px-1 py-2 md:px-2 md:py-2 mb-2 md:mb-3 overflow-auto bg-white dark:bg-dark_tertiary rounded-md shadow-sm">
        <div class="flex-shrink-0 w-14 h-14 overflow-hidden border border-gray-200 rounded-md sm:w-24 sm:h-24 md:w-32 md:h-32 lg:w-44 lg:h-44">
            <img :src="image" class="object-cover object-center w-full h-full" alt="Product image" />
        </div>
        <div class="flex justify-between w-full ml-1 sm:ml-3 sm:p-2">
            <div class="flex flex-col w-8/12 sm:flex sm:justify-between sm:flex-row">
                <div class="flex flex-col justify-between w-11/12">
                    <div>
                        <p class="text-xs sm:text-base">{{ product.productCart.productName }}</p>
                        <p class="text-xs sm:text-base text-gray-600 dark:text-gray-300">color: {{ product.colors.label }}</p>
                    </div>
                    <div>
                        <div class="flex items-center py-3 sm:pt-3 sm:pb-0 text-sm font-medium text-green-600"><span class="material-icons"> check_circle_outline </span> In stork</div>
                    </div>
                </div>

                <div class="">
                    <form @submit.prevent class="w-1/2 sm:w-full">
                        <div class="flex items-center justify-center bg-gray-200  dark:bg-gray-600 dark:bg-opacity-70 rounded-md">
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
                    <span class="text-base md:text-2xl text-primary font-semibold">฿ {{ product.productCart.price * quantity }}</span>
                    <span class="text-xs md:text-base text-gray-600 dark:text-gray-300 tracking-tighter" v-if="quantity >= 2">unit : ฿ {{ product.productCart.price }}</span>
                </div>
                <div class="inline-flex justify-end"><button class="p-1 font-semibold sm:px-2 text-xs sm:text-base rounded-md hover:bg-gray-200 dark:hover:bg-dark_secondary" @click="remove">remove</button></div>
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
            choices: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        };
    },
    methods: {
        remove() {
            this.$store.dispatch("removeCartItem", this.index);
        },
        editQuantity() {
            let payload = { index: this.index, quantity: Number(this.quantity) };
            this.$store.dispatch("editQuantity", payload);
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
