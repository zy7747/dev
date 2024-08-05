<!--  -->
<template>
  <div class="">
    <User :countObj="countObj" />
    <div style="width: 100%; overflow: hidden">
      <el-row :gutter="20">
        <el-col :span="6">
          <UserVisits :countObj="countObj" />
          <UserBar :countObj="countObj" />
        </el-col>
        <el-col :span="12"> <UserMap :countObj="countObj" /></el-col>
        <el-col :span="6">
          <UserVisits :countObj="countObj" />
          <UserBar :countObj="countObj" />
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script lang="ts" setup>
import User from "./user/index.vue";
import UserMap from "./user/map.vue";
import UserVisits from "./user/visits.vue";
import UserBar from "./user/bar.vue";

const countObj = ref({});

setInterval(() => {
  getCount();
}, 30000);

function getCount() {
  Service.count.get().then((res: any) => {
    countObj.value = res.data;
  });
}

getCount();
</script>

<style lang="scss" scoped></style>
