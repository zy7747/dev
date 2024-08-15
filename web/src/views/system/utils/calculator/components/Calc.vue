<!--  -->
<template>
  <div class="calc">
    <div class="screen">{{ result }}</div>
    <div class="input">
      <el-input
        v-model="input"
        style="width: 500px"
        placeholder="Please input"
      />
    </div>
    <ul class="keyboard">
      <li
        v-for="item in keyboards"
        class="calc-btn cursor-pointer"
        @click="onKeyUp(item)"
      >
        {{ item }}
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
const keyboards = ref([
  "(",
  ")",
  "C",
  "删除",
  7,
  8,
  9,
  "/",
  4,
  5,
  6,
  "*",
  1,
  2,
  3,
  "-",
  0,
  ".",
  "计算",
  "+",
]);
const input = ref("");
const list: any = ref([]);
const result = ref("");

function onKeyUp(item: any) {
  unref(list).push(item);
  if (item === "计算") {
    result.value = input.value;
  } else if (item === "删除") {
    unref(list).pop();
    input.value = unref(input).slice(0, -1);
  } else if (item === "C") {
    list.value = unref(list).slice(0);
    input.value = "";
  } else {
    if (typeof item === "number" || item === ".") {
      input.value = `${input.value}${item}`;
    } else {
      input.value = `${input.value} ${item} `;
    }
  }
}
</script>

<style lang="scss" scoped>
.calc {
  width: 530px;
  padding: 15px;
  border-radius: 15px;
  border: 1px solid black;
}
.screen {
  width: 500px;
  height: 150px;
  border: 1px solid black;
  background-color: #4b4e54;
  box-shadow: 0 1px 2px 0 #00000080;
  margin-bottom: 10px;
}
.keyboard {
  width: 500px;
  display: grid;
  align-content: start;
  grid-template-columns: repeat(4, 25%);
  .calc-btn {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 5px 30px;
    margin: 5px;
    border-radius: 8px;
    background-color: #4b4e54;
    box-shadow: 0 1px 2px 0 #00000080;
    &:hover {
      background-color: #414449;
    }
  }
}
</style>
