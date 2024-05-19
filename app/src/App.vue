<script setup lang="ts">
import { onLaunch, onShow, onHide } from "@dcloudio/uni-app";
import { useDictStore } from "@/store/dict";
const dictStore = useDictStore();

onLaunch(() => {
  dictStore.getDict();
});
onShow(() => {
  console.log("App Show");
});
onHide(() => {
  console.log("App Hide");
});
</script>
<style lang="scss"></style>
