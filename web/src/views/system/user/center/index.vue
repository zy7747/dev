<!--  -->
<template>
  <Collapse title="用户中心" padding="0">
    <template #content>
      <div class="user-card">
        <UserInfo :userInfo="userInfo" />
      </div>
    </template>
  </Collapse>

  <el-container>
    <el-main style="padding: 0 20px 0 0">
      <Collapse title="个人主页">
        <template #content>
          <div class="user-home">个人主页</div>
        </template>
      </Collapse>

      <Collapse title="我的收藏">
        <template #content>
          <div class="user-collection">20</div>
        </template>
      </Collapse>
    </el-main>

    <el-aside>
      <Collapse title="公告">
        <template #content>
          <div class="announcement">编辑个人公共</div>
        </template>
      </Collapse>
      <Collapse title="列表">
        <template #content>
          <div class="user-list">列表</div>
        </template>
      </Collapse>
    </el-aside>
  </el-container>
</template>

<script lang="ts" setup>
import { useUserStore } from "@/store/user";
import UserInfo from "./components/userInfo.vue";

const userStore = useUserStore();

const userInfo = userStore.userInfo;
</script>

<style lang="scss" scoped>
.user-card {
  width: 100%;
  height: 150px;
  background: url("https://cos-1307762674.cos.ap-shanghai.myqcloud.com/picture/banner/userBg.png")
    0 0 no-repeat;
  background-position: center center;
  background-size: cover;
}

.announcement {
  height: 150px;
}
.user-list {
  height: 300px;
}

.user-home {
  height: 300px;
}
.user-collection {
  height: 150px;
}
</style>
