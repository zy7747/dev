<!--  -->
<template>
  <div class="">
    <c-button class="btn" text="清除已读"></c-button>
    <c-button text="清除所有"></c-button>

    <vxe-list
      v-if="list.length"
      height="550"
      :data="list"
      :scroll-y="{ enabled: true }"
    >
      <template #default="{ items }">
        <li
          :class="
            'messageBox ' + (item.isRead === '1' ? 'readClass' : 'noReadClass')
          "
          v-for="(item, index) in items"
          :key="index"
          @click="viewMessage(item)"
        >
          <div class="icon">
            <svg-icon v-if="item.isRead === '1'" name="已读邮件" />
            <svg-icon v-else name="新邮件" />
          </div>

          <div class="title">
            <div class="noticeName">{{ item.messageName }}</div>
          </div>
        </li>
      </template>
    </vxe-list>
  </div>
</template>

<script lang="ts" setup>
const emit = defineEmits(["refresh"]);

defineProps({
  list: {
    text: "消息列表",
    type: [Array as any],
    default: () => {
      return [{}];
    },
  },
});

function viewMessage(item: any) {
  if (item.isRead === "0") {
    Service.system.message.save({ ...item, isRead: "1" }).then(() => {
      emit("refresh");
    });
  }
}
</script>

<style lang="scss" scoped>
.readClass {
  background-color: rgb(162, 226, 142);
}

.noReadClass {
  background-color: rgb(78, 210, 228);
}
.messageBox {
  display: flex;
  cursor: pointer;
}
</style>
