<!--  -->
<template>
  <Collapse title="JSON浏览">
    <template #content>
      <pre><code class="language-plaintext" v-html="highlightedCode({...pageData,options:null})"></code></pre>
    </template>
  </Collapse>

  <Collapse title="JSON浏览">
    <template #content>
      <MdPreview :editorId="id" :modelValue="text" />
      <MdCatalog :editorId="id" :scrollElement="scrollElement" />
    </template>
  </Collapse>

  <Collapse title="JSON编辑">
    <template #content>
      <MdEditor ref="editorRef" v-model="text"></MdEditor>
    </template>
  </Collapse>
</template>

<script lang="ts" setup>
import { js_beautify } from "js-beautify";
import { MdEditor, MdPreview, MdCatalog } from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import "highlight.js/styles/devibeans.css";
import highlight from "@/plugins/highlight";

const pageData: any = defineModel();

defineProps({
  active: {
    text: "active",
    type: [Number] as any,
  },
});

const id = "preview-only";
const t = "```json\n" + js_beautify(JSON.stringify(pageData.value)) + "\n```";
const text = ref(t);
const editorRef = ref();
const scrollElement = document.documentElement;

/**
 * 代码高亮
 */
const highlightedCode = (code: any) => {
  return highlight(js_beautify(JSON.stringify(code)), "json");
};
</script>

<style lang="scss" scoped></style>
