import Cookies from "js-cookie";

const LoginInfoKey = "LoginInfo";

export function getLoginInfo() {
  return Cookies.get(LoginInfoKey);
}

export function setLoginInfo(info: string) {
  console.log(info);

  return Cookies.set(LoginInfoKey, info);
}

export function removeLoginInfo() {
  return Cookies.remove(LoginInfoKey);
}
