<template>
    <div class="w-full flex flex-col sm:flex-row px-2 py-3 lg:px-36">
        <div class="bg-primary rounded-full sm:p-10 md:p-14 lg:p-16 h-24 w-24 sm:mx-10 text-white flex flex-col items-center justify-center self-center">
            <p class="text-2xl sm:text-5xl font-bold">{{ sumScore.toFixed(2) }}</p>
            <p class="">คะแนน</p>
        </div>
        <div class="w-full">
            <div class="py-3 px-2 lg:px-5" v-for="rate in result" :key="rate">
                <div class="flex flex-row justify-between ">
                    <div class=" text-primary tracking-tighter">
                        <span>{{ rate.name }}</span>
                    </div>
                    <div class=" text-gray-700 dark:text-gray-400 pl-3">
                        <span class="text-sm">{{ rate.result.toFixed(2) }}</span>
                    </div>
                </div>
                <div class="flex items-center mt-1">
                    <div class="w-full">
                        <div class="bg-gray-300 w-full rounded-lg h-2">
                            <div class="bg-secondary rounded-lg h-2" :style="{ width: `${(rate.result / 10) * 100}%` }"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        ratings: Array,
    },
    computed: {
        result() {
            let base = [
                {
                    name: "Design",
                    id: 1,
                    result:
                        this.ratings.filter((item) => {
                            return item.ratings.id == 1;
                        }).length > 0
                            ? this.ratings
                                  .filter((item) => {
                                      return item.ratings.id == 1;
                                  })
                                  .map((item) => {
                                      return Number(item.score);
                                  })
                                  .reduce((previouItem, currentItem) => {
                                      return Number(previouItem) + Number(currentItem);
                                  }) /
                              this.ratings.filter((item) => {
                                  return item.ratings.id == 1;
                              }).length
                            : 0,
                },
                {
                    name: "Material",
                    id: 2,
                    result:
                        this.ratings.filter((item) => {
                            return item.ratings.id == 2;
                        }).length > 0
                            ? this.ratings
                                  .filter((item) => {
                                      return item.ratings.id == 2;
                                  })
                                  .map((item) => {
                                      return Number(item.score);
                                  })
                                  .reduce((previouItem, currentItem) => {
                                      return Number(previouItem) + Number(currentItem);
                                  }) /
                              this.ratings.filter((item) => {
                                  return item.ratings.id == 2;
                              }).length
                            : 0,
                },
                {
                    name: "Good value",
                    id: 3,
                    result:
                        this.ratings.filter((item) => {
                            return item.ratings.id == 3;
                        }).length > 0
                            ? this.ratings
                                  .filter((item) => {
                                      return item.ratings.id == 3;
                                  })
                                  .map((item) => {
                                      return Number(item.score);
                                  })
                                  .reduce((previouItem, currentItem) => {
                                      return Number(previouItem) + Number(currentItem);
                                  }) /
                              this.ratings.filter((item) => {
                                  return item.ratings.id == 3;
                              }).length
                            : 0,
                },
                {
                    name: "Ease to use",
                    id: 4,
                    result:
                        this.ratings.filter((item) => {
                            return item.ratings.id == 4;
                        }).length > 0
                            ? this.ratings
                                  .filter((item) => {
                                      return item.ratings.id == 4;
                                  })
                                  .map((item) => {
                                      return Number(item.score);
                                  })
                                  .reduce((previouItem, currentItem) => {
                                      return Number(previouItem) + Number(currentItem);
                                  }) /
                              this.ratings.filter((item) => {
                                  return item.ratings.id == 4;
                              }).length
                            : 0,
                },
            ];

            return base;
        },
        sumScore() {
            return (
                this.result
                    .map((item) => {
                        return item.result;
                    })
                    .reduce((previouItem, currentItem) => {
                        return previouItem + currentItem;
                    }) / 4
            );
        },
    },
    created() {},
};
</script>

<style></style>
