<!--  -->
<template>
  <VideoPlay ref="videoPlay" />
</template>

<script lang="ts" setup>
const videoPlay = ref();

onMounted(() => {
  videoPlay.value.createVideo([]);
});
</script>

<style lang="scss" scoped></style>
