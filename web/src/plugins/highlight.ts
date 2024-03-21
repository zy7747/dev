import hljs from "highlight.js"; // 导入代码高亮文件
import java from "highlight.js/lib/languages/java";
import xml from "highlight.js/lib/languages/xml";
import javascript from "highlight.js/lib/languages/javascript";
import sql from "highlight.js/lib/languages/sql";
import typescript from "highlight.js/lib/languages/typescript";
import json from "highlight.js/lib/languages/json";
import shell from "highlight.js/lib/languages/shell";
import bash from "highlight.js/lib/languages/bash";

//需要高亮的语言
hljs.registerLanguage("java", java);
hljs.registerLanguage("xml", xml);
hljs.registerLanguage("javascript", javascript);
hljs.registerLanguage("sql", sql);
hljs.registerLanguage("typescript", typescript);
hljs.registerLanguage("json", json);
hljs.registerLanguage("shell", shell);
hljs.registerLanguage("bash", bash);

function highlight(code: string, language: string) {
  const textBox = hljs.highlight(language, code || "", true).value;

  return `<div class="botTalkCode">${textBox}</div>`;
}

export default highlight;
