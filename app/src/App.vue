<script setup lang="ts">
import { onLaunch, onShow, onHide } from "@dcloudio/uni-app";
import { useDictStore } from "@/store/dict";
import { useUserStore } from "@/store/user";
const dictStore = useDictStore();
const userStore = useUserStore();

onLaunch(async () => {
  //判断是否有TOKEN

  await userStore.login({
    loginType: "account",
    account: "visitor",
    password: "user123",
  });

  dictStore.getDict();

  console.log(dictStore, userStore);
});
onShow(() => {
  console.log("App Show");
});
onHide(() => {
  console.log("App Hide");
});
</script>
<style lang="scss"></style>
