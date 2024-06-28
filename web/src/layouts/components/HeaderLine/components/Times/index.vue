<!--  -->
<template>
  <div class="flex justify-center flex-col items-center clock">
    <div>{{ date }}</div>
    <div>{{ time }}</div>
  </div>
</template>

<script lang="ts" setup>
import dayjs from "dayjs";
const date = ref(dayjs().format("YYYY-MM-DD"));
const time = ref(dayjs().format("mm:ss"));

setInterval(() => {
  date.value = dayjs().format("YYYY-MM-DD");
  time.value = dayjs().format("HH:mm:ss");
}, 1000);
</script>

<style lang="scss" scoped>
.clock {
  padding-top: 5px;
  font-size: 14px;
}
</style>
