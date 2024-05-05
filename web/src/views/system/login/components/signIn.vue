<!--  -->
<template>
  <div class="flex flex-col items-center">
    <h2 style="font-size: revert">{{ "登 录" }}</h2>
    <c-form
      @keyup.enter.native="login"
      ref="formRef"
      :model="loginInfo"
      :formConfig="formConfig"
    />
    <div style="width: 100%">
      <el-checkbox v-model="loginInfo.rememberMe" label="记住我" />
    </div>

    <div>
      <c-button @handleClick="login" text="START" />
      <c-button @click="handleCheck" text="去注册" />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { useUserStore } from "@/store/user";

const userStore = useUserStore();
const Router = useRouter();

const formRef = ref();
const show: any = defineModel();

const loginInfo: any = ref({});
const formConfig = ref({
  formParams: [
    {
      label: $t("user.account", "账号"),
      prop: "account",
      type: "input",
      span: 24,
    },
    {
      label: $t("user.password", "密码"),
      prop: "password",
      type: "input",
      params: {
        type: "password",
        "show-password": true,
      },
      span: 24,
    },
  ],
});
//登录
function login() {
  return unref(formRef)
    .submitForm()
    .then((valid: boolean) => {
      if (valid) {
        return userStore
          .login({
            loginType: "account",
            ...loginInfo.value,
          })
          .then((res: any) => {
            if (res) {
              Router.push("/");
              ElNotification({
                title: "登录成功",
                message: `欢迎回来 ${res.userInfo.user}`,
                type: "success",
              });
            }
          });
      }
    });
}
//跳转到注册
function handleCheck() {
  show.value = "signUp";
}
//返回记住的参数
function hasRemember() {
  const info = localStorage.getItem("rememberMe");
  if (info !== null) {
    loginInfo.value = JSON.parse(info);
  }
}

hasRemember();
</script>

<style lang="scss" scoped></style>
