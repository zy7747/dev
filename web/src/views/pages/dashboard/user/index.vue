<!--  -->
<template>
  <Card padding="0">
    <template #content>
      <div class="user-card">
        <div class="avatar flex flex-col items-center">
          <el-avatar
            :size="70"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          />
          <div class="user-name" style="margin-top: 3px; font-size: 12px">
            尊敬的
            <span style="color: #252525">({{ userInfo.user }})</span> 用户
          </div>
          <div class="user-name">您好!!!,欢迎回来</div>
        </div>
      </div>
    </template>
  </Card>
</template>

<script lang="ts" setup>
import { useUserStore } from "@/store/user";

const userStore = useUserStore();

const userInfo = userStore.userInfo;
</script>

<style lang="scss" scoped>
.user-card {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 150px;
  background: url("https://cos-1307762674.cos.ap-shanghai.myqcloud.com/picture/banner/userBg.png")
    0 0 no-repeat;
  background-position: center center;
  background-size: cover;
}

.user-name {
  font-size: 25px !important;
  color: #ffffff;
  font-weight: 600;
}
</style>
