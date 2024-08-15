function usePage(config: any = {}) {
  const pageRef = ref();
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

  function query() {
    unref(pageRef).query();
  }
  function getActive() {
    unref(pageRef).getActive();
  }

  //删除成功后提示
  function removeSuccess(res: any) {
    if (res.code === 200) {
      ElNotification({
        title: "Success",
        message: "删除成功",
        type: "success",
      });
      unref(pageRef).query();
    }
  }

  //新增成功后提示
  function submitSuccess(res: any) {
    if (res.code === 200) {
      ElNotification({
        title: "Success",
        message: "提交成功",
        type: "success",
      });
      unref(pageRef).query();
      unref(pageRef).handleClose();
    }
  }

  return {
    ids,
    query,
    getActive,
    removeSuccess,
    submitSuccess,
    pageOption,
    pageRef,
    checkboxData,
  };
}

export default usePage;
