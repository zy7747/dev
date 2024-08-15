<!--  -->
<template>
  <c-grid ref="gridRef" />
</template>

<script lang="ts" setup>
const gridRef = ref();
const Route = useRoute();

function getGrid() {
  Service.configuration.page
    .detail({ pageCode: Route.name })
    .then((res: any) => {
      const options = JSON.parse(res.data.options);

      nextTick(() => {
        unref(gridRef).render({
          title: Route.meta.title,
          ...options,
        });
      });
    });
}

getGrid();
</script>

<style lang="scss" scoped></style>
