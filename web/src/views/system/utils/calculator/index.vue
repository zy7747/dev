<!--  -->
<template>
  <Collapse title="计算器" padding="0">
    <template #content>
      <div class="calculator-card">
        <Calc />
      </div>
    </template>
  </Collapse>
</template>

<script lang="ts" setup>
import Calc from "./components/Calc.vue";
</script>

<style lang="scss" scoped>
.calculator-card {
  height: 750px;
}
</style>
