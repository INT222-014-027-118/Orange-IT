.<template>
    <div class="flex w-full p-2 mb-2 overflow-auto bg-white dark:bg-dark_tertiary rounded-md shadow-sm">
        <div class="flex-shrink-0 w-20 h-20 overflow-hidden border border-gray-200 rounded-md sm:w-32 sm:h-32 lg:w-44 lg:h-44">
            <img :src="image" class="object-cover object-center w-full h-full" alt="Product image" />
        </div>
        <div class="flex justify-between w-full ml-3 sm:p-3">
            <div class="flex flex-col w-8/12 sm:flex sm:justify-between sm:flex-row">
                <div class="flex flex-col justify-between w-11/12">
                    <div>
                        <p class="text-sm sm:text-base">{{ product.productCart.productName }}</p>
                        <p class="text-sm text-gray-500 dark:text-gray-300">{{ product.colors.label }}</p>
                    </div>
                    <div>
                        <div class="flex items-center py-3 text-sm font-medium text-green-600"><span class="material-icons"> check_circle_outline </span> In stork</div>
                    </div>
                </div>

                <div class="">
                    <form @submit.prevent class="w-1/2 sm:w-full">
                        <div class="flex items-center justify-center bg-gray-200  dark:bg-gray-600 dark:bg-opacity-70 rounded-md">
                            <select name="quantity" id="quantity" v-model="quantity" class="self-start p-1 bg-gray-200 rounded-md cursor-pointer focus:outline-none" @change="editQuantity">
                                <option class="overflow-auto" v-for="choice in choices" :key="choice" :value="choice" :id="`choice-${choice}`">{{ choice }}</option>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
            <div class="flex flex-col justify-between w-4/12 font-semibold text-right">
                <span class="text-xl md:text-2xl text-primary">฿ {{ product.productCart.price }}</span>
                <div class="inline-flex justify-end"><button class="px-1 rounded-md hover:bg-gray-200" @click="remove">remove</button></div>
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
