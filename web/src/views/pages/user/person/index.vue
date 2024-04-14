<!--  -->
<template>
  <div class="">123</div>
</template>

<script lang="ts" setup>
const obj = {
  a: 1,
  //具名函数指向调用者OBJ
  module() {
    console.log(this);
  },
  //匿名函数指向undefined
  c: () => {
    console.log(this);
  },
};

// obj.c.bind(obj.module);

console.log(obj);
</script>

<style lang="scss" scoped></style>
