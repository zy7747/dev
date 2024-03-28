<!--  -->
<template>
  <iframe
    :src="baseUrl + '/doc.html#/default'"
    frameborder="0"
    width="100%"
    height="800px"
  />
</template>

<script lang="ts" setup>
const baseUrl = import.meta.env.VITE_APP_BASE_API;
</script>

<style lang="scss" scoped></style>
