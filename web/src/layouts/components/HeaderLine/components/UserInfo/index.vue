<!--  -->
<template>
  <CDropdown :options="options" @command="command">
    <template #title>
      <div class="flex flex-col items-center">
        <el-avatar
          :size="25"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        />
        <span style="margin-top: 3px; font-size: 12px">{{
          userInfo.user
        }}</span>
      </div>
    </template>
  </CDropdown>
</template>

<script lang="ts" setup>
import { useUserStore } from "@/store/user";

const userStore = useUserStore();

const userInfo = userStore.userInfo;

function command(row: any) {
  console.log(row);
}

const options = ref([
  { icon: "访客", label: "个人中心", value: "visitor" },
  { icon: "切换账号", label: "切换账号", value: "changeAccount" },
  { icon: "关闭", label: "退出登录", value: "logOut" },
]);
</script>
