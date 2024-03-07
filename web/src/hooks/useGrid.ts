function useGrid(config = {}) {
  const pageRef = ref();
  const pageOption = config;

  const checkboxData = computed(() => {
    return unref(pageRef).checkboxData;
  });

  return {
    pageOption,
    pageRef,
    checkboxData,
  };
}

export default useGrid;
