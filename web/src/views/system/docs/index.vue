<!--  -->
<template>
  <iframe
    :src="baseUrl + '/docs'"
    frameborder="0"
    width="100%"
    height="830px"
  />
</template>

<script lang="ts" setup>
// const baseUrl = import.meta.env.VITE_APP_BASE_API;
const baseUrl = "http://localhost:80";
</script>

<style lang="scss" scoped></style>
