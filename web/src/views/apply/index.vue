<!--  -->
<template>
  <div class="">
    <Banner />
    <c-button text="ann " @click="show"></c-button>
  </div>
</template>

<script lang="ts" setup>
import Banner from "@/components/Banner/index.vue";

function show() {
  showModal(Banner, {
    dialogConfig: { width: "1500px", actions: [{ operation: "confirm" }] },
    title: "弹框",
  });
}
</script>

<style lang="scss" scoped></style>
