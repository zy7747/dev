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

//列处理
function tableColumns(item: any) {
  if (!item.width) {
    item.minWidth = 140;
  }

  if (item.type === "checkbox") {
    item.width = 50;
  }

  if (item.type === "seq") {
    item.title = $t("system.no", "序号");
    item.width = 60;
  }

  if (item.cType === "action") {
    item.title = $t("system.action", "操作");
    item.minWidth = 200;
    item.fixed = "right";
  }

  return item;
}

//插槽处理
function tableSlot(item: any, filters: any) {
  const col: any = {
    resizable: true,
    "show-header-overflow": "ellipsis",
    slots: {},
    params: {},
    columns: {},
  };

  //编辑插槽
  if (!item.editRender) {
    if (item.form) {
      col.editRender = {};
      col.slots.edit = "tableEdit";

      Object.assign(col.params, { form: item.form });
    }
  }

  //自定义渲染插槽
  if ((item.cType && item.cType !== "action") || item.translate) {
    col.slots.default = "tableSlot";

    Object.assign(col.params, item);
  }

  //过滤
  if (item.isFilters) {
    col.filters = unref(filters)[item.field];
    col.filterRender = { name: "FilterInput" };
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

  return col;
}
//列
export function getTableCols(columns: any, filters: any) {
  if (!columns) return [];
  return unref(columns).map((item: any) => {
    return { ...tableColumns(item), ...tableSlot(item, filters) };
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
