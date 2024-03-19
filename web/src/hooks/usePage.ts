function usePage(config: any = {}) {
  const pageRef: any = ref();
  const pageOption = config;

  if (config.pageRef) {
    pageRef.value = config.pageRef.value;
  }

  function checkboxData() {
    return unref(pageRef).checkboxData();
  }

  function ids() {
    const list = unref(pageRef).checkboxData();
    if (list.length) {
      return list.map((item: any) => {
        return item.id;
      });
    } else {
      return [];
    }
  }

  return {
    ids,
    pageOption,
    pageRef,
    checkboxData,
  };
}

export default usePage;
