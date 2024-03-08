function useGrid(config = {}) {
  const pageRef = ref();
  const pageOption = config;

  const checkboxData = computed(() => {
    return unref(pageRef).checkboxData;
  });

  const ids = computed(() => {
    if (unref(pageRef).checkboxData.length) {
      return unref(pageRef).checkboxData.map((item: any) => {
        return item.id;
      });
    } else {
      return [];
    }
  });

  return {
    ids,
    pageOption,
    pageRef,
    checkboxData,
  };
}

export default useGrid;
