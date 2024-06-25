<!--  -->
<template>
  <iframe :src="swaggerUrl" frameborder="0" width="100%" height="800px" />
</template>

<script lang="ts" setup>
const swaggerUrl = import.meta.env.VITE_APP_SWAGGER_URL;
</script>

<style lang="scss" scoped></style>
