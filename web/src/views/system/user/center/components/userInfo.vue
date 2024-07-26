<!--  -->
<template>
  <div class="userInfo">
    <div class="avatar flex flex-col items-center">
      <el-avatar
        :size="70"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      />
      <div class="user-name" style="margin-top: 3px; font-size: 12px">
        尊敬的 <span style="color: #252525">({{ userInfo.user }})</span> 用户
      </div>
      <div class="user-name">您好!!!</div>
    </div>
  </div>
</template>

<script lang="ts" setup>
defineProps({
  userInfo: {
    text: "参数",
    type: [Object, Function] as any,
  },
});
</script>

<style lang="scss" scoped>
.userInfo {
  width: 100%;
}

.user-name {
  font-size: 25px !important;
  color: #ffffff;
  font-weight: 600;
}
</style>
