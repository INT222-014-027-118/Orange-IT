<template>
    <button class="text-center py-2 w-full shadow-sm" :class="[active?'bg-yellow-400 text-white':'']" @click="chooseMenu">
        <span class="text-lg font-semibold">{{ label }}</span>
    </button>
</template>
<script>
export default {
  data() {
    return {
      
    }
  },
    props: {
        label: String,
        link:String,
        active: Boolean,
    },
    methods: {
      chooseMenu(){
        this.$router.push(`/admin/${this.link}`)
        this.$emit('chooseMenu')
      }
    },
};
</script>

<style></style>
