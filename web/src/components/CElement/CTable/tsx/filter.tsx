import Filter from "../components/filter.vue";
import { VxeUI } from "vxe-table";

// 创建一个简单的输入框筛选
VxeUI.renderer.add("FilterInput", {
  // 不显示底部按钮，使用自定义的按钮
  showFilterFooter: false,
  // 筛选模板
  renderFilter(renderOpts: any, params: any) {
    return <Filter key={params.column.field} renderOpts={renderOpts} params={params}></Filter>;
  },
  // 重置数据方法
  filterResetMethod(params: any) {
    const { options } = params;
    options.forEach((option: any) => {
      option.data = "";
    });
  },
  // 重置筛选复原方法（当未点击确认时，该选项将被恢复为默认值）
  filterRecoverMethod({ option }: any) {
    option.data = "";
  },
  // 筛选方法
  filterMethod(params: any) {
    if (params.cellValue && params.value && params.cellValue == params.value) {
      return true;
    }
    return false;
  },
});
