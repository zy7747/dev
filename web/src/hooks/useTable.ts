function useTable(config: any = {}) {
  const tableRef: any = ref<HTMLElement | null>(null);
  const tableConfig = config;

  if (config.tableRef) {
    tableRef.value = config.tableRef.value;
  }

  function getCheckboxData() {
    return unref(tableRef).checkboxData();
  }

  function findCheckDataIndex(list: any): any {
    const checkboxData = unref(tableRef).checkboxData();

    return list.filter((item: any) => {
      const has = checkboxData.every(
        (i: any) => i._row_index !== item._row_index
      );

      return has;
    });
  }

  function apis(params: String) {
    let api = Service;

    params.split(".").forEach((item) => {
      api = api[item];
    });

    return api;
  }

  function tableIds() {
    const list = unref(tableRef).checkboxData();
    if (list.length) {
      return list.map((item: any) => {
        return item.id;
      });
    } else {
      return [];
    }
  }

  return {
    tableIds,
    apis,
    tableConfig,
    tableRef,
    getCheckboxData,
    findCheckDataIndex,
  };
}

export default useTable;
