<!--  -->
<template>
  <div class="">
    <c-form
      @keyup.enter.native="login"
      ref="formRef"
      :model="loginInfo"
      :formConfig="formConfig"
    />
    <el-checkbox v-model="loginInfo.rememberMe" label="记住我" />
    <div>
      <c-button :loading="loading" @click="login" text="登录" />
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
const loading = ref(false);

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
  loading.value = true;
  unref(formRef)
    .submitForm()
    .then((valid: boolean) => {
      if (valid) {
        userStore
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
    })
    .finally(() => {
      loading.value = false;
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
