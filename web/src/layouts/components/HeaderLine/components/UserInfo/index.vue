<!--  -->
<template>
  <CDropdown :options="options" @command="command">
    <template #title>
      <div class="flex flex-col items-center">
        <el-avatar
          :size="25"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        />
        <span style="margin-top: 3px; font-size: 12px">
          {{ userInfo.user }}
        </span>
      </div>
    </template>
  </CDropdown>
</template>

<script lang="ts" setup>
import { useUserStore } from "@/store/user";
import loginBox from "@/views/system/login/loginBox.vue";
import { showModal } from "@/hooks/useModal";
const userStore = useUserStore();
const Router = useRouter();
const userInfo = userStore.userInfo;

function command(row: any) {
  if (row.value === "logOut") {
    logOut();
  } else if (row.value === "changeAccount") {
    changeAccount();
  } else if (row.value === "userCenter") {
    userCenter();
  }
}

const options = ref([
  { icon: "访客", label: "个人中心", value: "userCenter" },
  { icon: "切换账号", label: "切换账号", value: "changeAccount" },
  { icon: "关闭", label: "退出登录", value: "logOut" },
]);
//登出
function logOut() {
  userStore.logout();
}
//切换账号
function changeAccount() {
  showModal(loginBox, {
    title: "登录",
    width: 500,
    showFooter: false,
  });
}
//个人中心
function userCenter() {
  Router.push({ path: "/user/center" });
}
</script>
