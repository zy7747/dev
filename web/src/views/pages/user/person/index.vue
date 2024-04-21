<!--  -->
<template>
  <c-grid ref="gridRef" />
</template>

<script lang="ts" setup>
const gridRef = ref();

function getGrid() {
  Service.configuration.page.detail({ pageCode: "Person" }).then((res: any) => {
    const options = JSON.parse(res.data.options);

    nextTick(() => {
      unref(gridRef).render({
        title: "用户信息",
        ...options,
      });
    });
  });
}

getGrid();
</script>

<style lang="scss" scoped></style>
