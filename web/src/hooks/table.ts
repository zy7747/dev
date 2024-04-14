//过滤
export function getFilter(columns: any, filterMap: any) {
  columns.forEach((item: any) => {
    if (item.isFilters) {
      filterMap[item.field] = [];
    }

    if (item.children && item.children.length) {
      getFilter(item.children, filterMap);
    }
  });
}
//列
export function getTableCols(columns: any, filters: any) {
  if (!columns) return [];
  return unref(columns).map((item: any) => {
    const col: any = {
      resizable: true,
      slots: {},
      params: {},
      "show-header-overflow": "ellipsis",
    };

    //编辑插槽
    if (!item.editRender && item.form) {
      col.editRender = {};
      col.slots.edit = "tableEdit";

      Object.assign(col.params, { form: item.form });
    }

    //自定义渲染插槽
    if ((item.cType && item.cType !== "action") || item.translate) {
      col.slots.default = "tableSlot";
      Object.assign(col.params, {
        cType: item.cType,
        translate: item.translate,
      });
    }

    //过滤
    if (item.isFilters) {
      col.filters = filters.value[item.field];
    }

    //自定义插槽
    if (item.slots) {
      col.slots = item.slots;
    }

    if (item.children && item.children.length) {
      col.children = getTableCols(item.children, filters);
    }

    if (item.cType === "action") {
      col.slots = { default: "operate" };
    }

    return { ...item, ...col };
  });
}
//校验
export function getRules(column: any, rules: any) {
  if (!column) return rules;

  column.forEach((item: any) => {
    if (item.rules) {
      rules[item.field] = item.rules;
    }

    if (item.children) {
      getRules(item.children, rules);
    }
  });

  return rules;
}
