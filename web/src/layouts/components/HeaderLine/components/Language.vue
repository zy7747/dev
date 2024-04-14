<!--  -->
<template>
  <CDropdown :options="langs" @command="command">
    <template #title>
      <svg-icon :name="icon" class="icon" />
    </template>
  </CDropdown>
</template>

<script lang="ts" setup>
import { useI18n } from "vue-i18n";
const { locale } = useI18n();

const langs = ref([
  { icon: "cn", label: "中文", value: "zh" },
  { icon: "us", label: "English", value: "en" },
  { icon: "jp", label: "日本語", value: "jp" },
  { icon: "kr", label: "한국인", value: "kr" },
]);

const icon = computed(() => {
  const obj: any = unref(langs).find(
    (item: any) => item.value === locale.value
  );

  return obj.icon;
});

function command(row: any) {
  localStorage.setItem("language", row.value);
  locale.value = row.value;
  refresh();
}

function refresh() {
  location.reload();
}
</script>

<style lang="scss" scoped>
.icon {
  width: 25px;
  height: 25px;
}
</style>
