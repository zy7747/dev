<!--  -->
<template>
  <el-popover placement="bottom" trigger="click" :width="400">
    <template #reference>
      <el-badge
        :is-dot="isDot"
        class="dot cursor-pointer flex items-center flex-col"
      >
        <svg-icon name="邮件" class="icon" />
        <span style="font-size: 12px; margin-top: 3px">
          {{ $t("system.message") }}
        </span>
      </el-badge>
    </template>

    <div class="notice">
      <el-tabs v-model="active">
        <el-tab-pane
          :label="item.label"
          :name="item.name"
          v-for="item in massageBoxList"
        >
          <MessageBox @refresh="getMessage" :list="item.list" />
        </el-tab-pane>
      </el-tabs>
    </div>
  </el-popover>
</template>

<script lang="ts" setup>
import { getWebsocket } from "@/utils/websocket";
import MessageBox from "./components/MessageBox.vue";
import { useUserStore } from "@/store/user";

const userStore = useUserStore();

const isDot = ref(false);
const massageBoxList = ref([
  {
    label: "通知",
    name: "通知",
    list: [],
  },
  {
    label: "邮件",
    name: "邮件",
    list: [],
  },
  {
    label: "公告",
    name: "公告",
    list: [],
  },
]);

const active = ref("通知");

function getMessage() {
  Service.message.list({ receiver: userStore.userId }).then((res: any) => {
    const mail: any = [];
    const notice: any = [];
    const announcement: any = [];

    res.data.forEach((item: any) => {
      if (item.type === "notice") {
        notice.push(item);
      } else if (item.type === "mail") {
        mail.push(item);
      } else {
        announcement.push(item);
      }
    });

    //全部已读
    isDot.value = !res.data.every((item: any) => item.isRead !== "0");

    massageBoxList.value[0].list = notice;
    massageBoxList.value[1].list = mail;
    massageBoxList.value[2].list = announcement;
  });
}

getWebsocket(getMessage);
</script>

<style lang="scss" scoped>
.icon {
  width: 25px;
  height: 25px;
}
</style>
