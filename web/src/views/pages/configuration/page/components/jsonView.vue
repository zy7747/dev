<!--  -->
<template>
  <div style="height: 600px">
    <pre><code class="language-plaintext" v-html="highlightedCode({...pageData,options:null})"></code></pre>

    <MdEditor></MdEditor>
  </div>
</template>

<script lang="ts" setup>
import { js_beautify } from "js-beautify";
import { MdEditor } from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import "highlight.js/styles/devibeans.css";
import highlight from "@/plugins/highlight";

defineProps({
  active: {
    text: "active",
    type: [Number] as any,
  },
  pageData: {
    text: "页面数据",
    type: [Object] as any,
  },
});

/**
 * 代码高亮
 */
const highlightedCode = (code: any) => {
  return highlight(js_beautify(JSON.stringify(code)), "json");
};
</script>

<style lang="scss" scoped></style>
