function useTable(config: any = {}) {
  const tableRef: any = ref<HTMLElement | null>(null);
  const tableConfig = config;

  if (config.tableRef) {
    tableRef.value = config.tableRef.value;
  }

  function checkboxData() {
    return unref(tableRef).checkboxData();
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
    checkboxData,
  };
}

export default useTable;
