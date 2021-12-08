<template>
    <div class="pt-1">
        <div class="mx-auto max-w-7xl">
            <div class="p-1 md:mt-0 md:p-2 lg:mx-auto">
                <h1 class="py-3 px-3 text-2xl md:text-3xl font-semibold flex items-center"><span class="material-icons mr-2 text-xl py-2 px-3 bg-primary text-white rounded-full"> rate_review </span>review</h1>
                <div class="capitalize bg-white dark:bg-dark_secondary shadow-md rounded-md p-1 md:p-3">
                    <div class="px-0 sm:px-2">
                        <div class="overflow-hidden">
                            <div class="flex flex-shrink-0 items-center">
                                <div class="self-start border border-gray-200 rounded-md w-14 h-14 sm:w-20 sm:h-20 md:w-28 md:h-28 flex flex-shrink-0 overflow-hidden">
                                    <img :src="`${this.api}/image/get/${this.image}`" class="object-cover object-center w-full h-full" alt="Product image" />
                                </div>
                                <div class="ml-3 w-full self-start">
                                    <p class="font-bold">{{ product.productName }}</p>
                                    <p class="">brand: {{ product.brandName }}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="px-0 py-2 sm:px-2 flex select-none max-w-lg mx-auto">
                        <div class="px-1 font-semibold tracking-wide">star:</div>
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

                    <div class="px-0 py-2 sm:px-2 max-w-lg mx-auto mt-3 mb-6">
                        <div class="px-1 font-semibold tracking-wide">rating:</div>
                        <div class="px-3 pb-5 pt-2 sm:px-4 select-none space-y-3">
                            <div class="">
                                <div class="flex flex-row justify-between ">
                                    <label for="volume text-primary tracking-tighter px-1">Design</label>
                                    <div class="pl-3">
                                        <span class="text-sm">{{ Design.score }}</span>
                                    </div>
                                </div>
                                <div class="flex items-center mt-1">
                                    <input v-model="Design.score" class="focus:outline-none styled-slider slider-progress " type="range" id="volume" name="volume" min="0" max="10" />
                                </div>
                            </div>
                            <div class="">
                                <div class="flex flex-row justify-between ">
                                    <label for="volume text-primary tracking-tighter px-1">Material</label>
                                    <div class="pl-3">
                                        <span class="text-sm">{{ Material.score }}</span>
                                    </div>
                                </div>
                                <div class="flex items-center mt-1">
                                    <input v-model="Material.score" class="focus:outline-none styled-slider slider-progress " type="range" id="volume" name="volume" min="0" max="10" />
                                </div>
                            </div>
                            <div class="">
                                <div class="flex flex-row justify-between ">
                                    <label for="volume text-primary tracking-tighter px-1">Good value</label>
                                    <div class="pl-3">
                                        <span class="text-sm">{{ GoodValue.score }}</span>
                                    </div>
                                </div>
                                <div class="flex items-center mt-1">
                                    <input v-model="GoodValue.score" class="focus:outline-none styled-slider slider-progress " type="range" id="volume" name="volume" min="0" max="10" />
                                </div>
                            </div>
                            <div class="">
                                <div class="flex flex-row justify-between ">
                                    <label for="volume text-primary tracking-tighter px-1">Ease to use</label>
                                    <div class="pl-3">
                                        <span class="text-sm">{{ EaseToUse.score }}</span>
                                    </div>
                                </div>
                                <div class="flex items-center mt-1">
                                    <input v-model="EaseToUse.score" class="focus:outline-none styled-slider slider-progress " type="range" id="volume" name="volume" min="0" max="10" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="px-0 sm:px-2 relative max-w-lg mx-auto">
                        <textarea
                            class="vertical px-3 py-5 rounded-md h-52 ring-1 w-full ring-gray-300 min-h-0 focus:ring focus:ring-primary focus:outline-none bg-neutral dark:bg-dark_primary"
                            name=""
                            id=""
                            cols="30"
                            rows="10"
                            maxlength="100"
                            placeholder="write your review"
                            v-model="reviews.comment"
                        ></textarea>
                        <div class="absolute top-0 right-7">
                            <span class="text-xs"> {{ reviews.comment.length }}/100</span>
                        </div>
                    </div>
                    <div class="flex justify-end text-white dark:border-gray-500 my-5">
                        <button type="reset" class="px-3 py-2 mx-2 capitalize cancel-btn" @click="$router.push({ name: 'purchase', params: { purchaseDetail: 'purchase' } })">
                            cancel
                        </button>
                        <button
                            :disabled="reviews.star == 0"
                            @click="submitReview"
                            type="button"
                            class="px-6 py-2 mx-2 capitalize rounded-md whitespace-nowrap"
                            :class="[reviews.star == 0 ? 'cursor-not-allowed bg-primary bg-opacity-50' : 'btn']"
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
import axios from "axios";
export default {
    data() {
        return {
            api: process.env.VUE_APP_API,
            product: {},
            image: "",
            hoverStarCcore: 0,
            productId: 0,
            reviews: {
                id: 1,
                star: 0,
                comment: "",
                reviewDate: "",
                userId: 0,
                ratingOfProductForAdds: [],
            },

            Design: { id: 1, score: "0", ratings_id: 1, productId: 0, reviewId: 1 },
            Material: { id: 1, score: "0", ratings_id: 2, productId: 0, reviewId: 1 },
            GoodValue: { id: 1, score: "0", ratings_id: 3, productId: 0, reviewId: 1 },
            EaseToUse: { id: 1, score: "0", ratings_id: 4, productId: 0, reviewId: 1 },
        };
    },
    props: {
        product_id: String,
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
            this.reviews.ratingOfProductForAdds.push(this.Design);
            this.reviews.ratingOfProductForAdds.push(this.Material);
            this.reviews.ratingOfProductForAdds.push(this.GoodValue);
            this.reviews.ratingOfProductForAdds.push(this.EaseToUse);
            console.log(this.reviews);
        },
    },
    mounted() {
        for (let e of document.querySelectorAll('input[type="range"].slider-progress')) {
            e.style.setProperty("--value", e.value);
            e.style.setProperty("--min", e.min == "" ? "0" : e.min);
            e.style.setProperty("--max", e.max == "" ? "100" : e.max);
            e.addEventListener("input", () => e.style.setProperty("--value", e.value));
        }
    },
    async created() {
        this.reviews.userId = this.$store.getters.userInfo.id;
        this.product = await axios.get(`${this.api}/product/${this.product_id}`).then((response) => {
            this.image = response.data.images[0].source;
            return response.data;
        });
        this.$store.dispatch("loadRatingTitle");
        this.productId = parseInt(this.product_id);
        this.Design.productId = this.productId;
        this.Material.productId = this.productId;
        this.GoodValue.productId = this.productId;
        this.EaseToUse.productId = this.productId;
    },
};
</script>

<style>
textarea.vertical {
    resize: vertical;
    min-height: 10rem;
}

/*generated with Input range slider CSS style generator (version 20210711)
https://toughengineer.github.io/demo/slider-styler*/
input[type="range"].styled-slider {
    -webkit-appearance: none;
    width: 100%;
}

/*progress support*/
input[type="range"].styled-slider.slider-progress {
    --range: calc(var(--max) - var(--min));
    --ratio: calc((var(--value) - var(--min)) / var(--range));
    --sx: calc(0.5 * 15px + var(--ratio) * (100% - 15px));
}

input[type="range"].styled-slider:focus {
    outline: none;
}

/*webkit*/
input[type="range"].styled-slider::-webkit-slider-thumb {
    width: 15px;
    height: 15px;
    border-radius: 1em;
    background: #f35b04;
    border: none;
    box-shadow: 0 0 2px black;
    margin-top: calc(8px * 0.5 - 15px * 0.5);
    -webkit-appearance: none;
    cursor: grab;
}

input[type="range"].styled-slider::-webkit-slider-runnable-track {
    height: 8px;
    border-radius: 0.6em;
    background: #efefef;
    border: none;
    box-shadow: none;
}
input[type="range"].styled-slider::-webkit-slider-thumb:hover {
    background: #f35b04;
}

input[type="range"].styled-slider::-webkit-slider-thumb:active {
    background: #f35b04;
}

input[type="range"].styled-slider.slider-progress::-webkit-slider-runnable-track {
    background: linear-gradient(#f88100, #f88100) 0 / var(--sx) 100% no-repeat, #efefef;
}

/*mozilla*/
input[type="range"].styled-slider::-moz-range-thumb {
    width: 15px;
    height: 15px;
    border-radius: 1em;
    background: #f35b04;
    border: none;
    box-shadow: 0 0 2px black;
}

input[type="range"].styled-slider::-moz-range-track {
    height: 8px;
    border-radius: 0.6em;
    background: #efefef;
    border: none;
    box-shadow: none;
}

input[type="range"].styled-slider::-moz-range-thumb:hover {
    background: #f35b04;
}

input[type="range"].styled-slider::-moz-range-thumb:active {
    background: #f35b04;
}

input[type="range"].styled-slider.slider-progress::-moz-range-track {
    background: linear-gradient(#f88100, #f88100) 0 / var(--sx) 100% no-repeat, #efefef;
}

/*ms*/
input[type="range"].styled-slider::-ms-fill-upper {
    background: transparent;
    border-color: transparent;
}

input[type="range"].styled-slider::-ms-fill-lower {
    background: transparent;
    border-color: transparent;
}

input[type="range"].styled-slider::-ms-thumb {
    width: 15px;
    height: 15px;
    border-radius: 1em;
    background: #f35b04;
    border: none;
    box-shadow: 0 0 2px black;
    margin-top: 0;
    box-sizing: border-box;
}

input[type="range"].styled-slider::-ms-track {
    height: 8px;
    border-radius: 0.6em;
    background: #efefef;
    border: none;
    box-shadow: none;
    box-sizing: border-box;
}

input[type="range"].styled-slider::-ms-thumb:hover {
    background: #f35b04;
}

input[type="range"].styled-slider::-ms-thumb:active {
    background: #f35b04;
}

input[type="range"].styled-slider.slider-progress::-ms-fill-lower {
    height: 8px;
    border-radius: 0.6em 0 0 0.6em;
    margin: -undefined 0 -undefined -undefined;
    background: #f88100;
    border: none;
    border-right-width: 0;
}
</style>
