<template>
    <div class="pt-1">
        <div class="mx-auto max-w-7xl">
            <div class="p-1 md:mt-0 md:p-2 lg:mx-auto">
                <h1 class="py-3 px-3 text-2xl md:text-3xl font-semibold flex items-center"><span class="material-icons mr-2 text-2xl md:text-3xl"> rate_review </span>review</h1>
                <div class="capitalize bg-white dark:bg-dark_secondary shadow-md rounded-md p-1 md:p-3">
                    <div class="px-0 sm:px-2">
                        <div class="overflow-hidden">
                            <div class="flex flex-shrink-0 items-center">
                                <div class="self-start border border-gray-200 rounded-md w-14 h-14 sm:w-20 sm:h-20 md:w-28 md:h-28 flex flex-shrink-0 overflow-hidden">
                                    <img :src="`${this.api}/image/get/DeathAdder-V2-1.png`" class="object-cover object-center w-full h-full" alt="Product image" />
                                </div>
                                <div class="ml-3 w-full self-start">
                                    <p class="font-bold">RAZER DEATHADDER V2</p>
                                    <p class="">color: Black</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="px-0 py-4 sm:px-2 flex">
                        <div
                            class="cursor-pointer h-7 w-7 sm:h-9 sm:w-9"
                            v-for="star in [0, 1, 2, 3, 4]"
                            :key="star"
                            :class="[(reviews.star == 0 ? hoverStarCcore : reviews.star) > star ? 'text-yellow-400' : 'text-gray-200', 'h-5 w-5 flex-shrink-0']"
                            aria-hidden="true"
                            @mouseenter="hoverStar(star + 1)"
                            @mouseleave="hoverStar(0)"
                            @click="slectStar(star + 1)"
                        >
                            <span class="material-icons text-3xl sm:text-4xl">
                                star
                            </span>
                        </div>
                    </div>
                    <div class="px-0 sm:px-2">
                        <textarea
                            class="p-3 rounded-md ring-1 w-full ring-gray-300 min-h-0 focus:ring focus:ring-primary focus:outline-none dark:bg-dark_primary"
                            name=""
                            id=""
                            cols="30"
                            rows="10"
                            placeholder="write your review"
                            v-model="reviews.comment"
                        ></textarea>
                    </div>
                    <div class="flex justify-end text-white dark:border-gray-500 my-5">
                        <button type="button" class="bg-secondary px-3 py-2 mx-2 capitalize rounded-md whitespace-nowrap" @click="$router.push({ name: 'purchase', params: { purchaseDetail: 'purchase' }})">
                            cancel
                        </button>
                        <button
                            :disabled="reviews.star == 0"
                            @click="submitReview"
                            type="button"
                            class="px-6 py-2 mx-2 capitalize rounded-md whitespace-nowrap"
                            :class="[reviews.star == 0 ? 'cursor-not-allowed bg-primary bg-opacity-50' : 'bg-primary']"
                        >
                            submit
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            api: process.env.VUE_APP_API,
            product: {},
            reviews: {
                id: null,
                star: 0,
                comment: "",
                review_date: "",
                users_id: 0,
            },
            hoverStarCcore: 0,
        };
    },
    methods: {
        scrollToTop() {
            window.scrollTo(0, 0);
        },
        passingPrice(price) {
            return new Intl.NumberFormat("th-TH", {
                style: "currency",
                currency: "THB",
                minimumFractionDigits: 0,
                maximumFractionDigits: 2,
            }).format(price);
        },
        hoverStar(score) {
            this.hoverStarCcore = score;
        },
        slectStar(score) {
            this.reviews.star = score;
        },
        submitReview() {
            const time = new Date();
            this.reviews.review_date = time.toLocaleString("th-TH");
            console.log(this.reviews);
        },
    },
    mounted() {},
    created() {
        this.reviews.users_id = this.$store.getters.userInfo.id;
    },
};
</script>

<style></style>
