<!--  -->
<template>
  <el-image
    v-if="type === 'img'"
    style="width: 50px; height: 50px; border-radius: 5px"
    :src="fileUrl + value"
    :zoom-rate="1.2"
    :max-scale="7"
    :min-scale="0.2"
    :preview-src-list="[fileUrl + value]"
    :initial-index="4"
    fit="cover"
  />

  <div v-else-if="type === 'icon'" class="iconBox">
    <svg-icon :name="value" class="icon" />
  </div>

  <DictTag v-else-if="params.translate" :params="params" v-model="value" />
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_FILE_API;

defineProps({
  params: {
    text: "类型",
    type: [Object] as any,
  },
  type: {
    text: "类型",
    type: [String],
  },
});

const value: any = defineModel();
</script>

<style lang="scss" scoped>
.iconBox {
  width: 100%;
  display: flex;
  justify-content: center;
  .icon {
    width: 20px;
    height: 20px;
  }
}
</style>
