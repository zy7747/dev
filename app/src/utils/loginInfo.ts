const LoginInfoKey = "LoginInfo";

export function getLoginInfo() {
  return uni.getStorageSync(LoginInfoKey);
}

export function setLoginInfo(info: string) {
  return uni.setStorageSync(LoginInfoKey, info);
}

export function removeLoginInfo() {
  return uni.removeStorageSync(LoginInfoKey);
}
