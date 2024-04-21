<!--  -->
<template>
  <div>
    <el-tag v-if="dict.color && dict.color != 'default'" :type="dict.color">
      {{ dict.label }}
    </el-tag>
    <span v-else>{{ dict.label }}</span>
  </div>
</template>

<script lang="ts" setup>
import { useDictStore } from "@/store/dict";
const useDict = useDictStore();

const value: any = defineModel();
const { params } = defineProps({
  params: {
    text: "列参",
    type: Object as any,
  },
});

function getDictTag(dictCode: string, value: string) {
  const dictMap: any = useDict.dictMap;
  const dict = dictMap[dictCode] ? dictMap[dictCode] : [];
  const i = dict.find((item: any) => item.value === value);

  return i ? i : { label: "", value: "", color: null };
}

const dict: any = computed(() => {
  if (Array.isArray(params.translate)) {
    const data = params.translate.find(
      (item: any) => item.value === unref(value)
    );
    return data;
  } else {
    return getDictTag(params.translate, unref(value));
  }
});
</script>

<style lang="scss" scoped></style>
