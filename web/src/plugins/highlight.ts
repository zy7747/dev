import hljs from "highlight.js"; // 导入代码高亮文件
import "highlight.js/lib/common";

function highlight(code: string, language: string) {
  const textBox = hljs.highlight(language, code || "", true).value;

  return `<div class="botTalkCode">${textBox}</div>`;
}

export default highlight;
