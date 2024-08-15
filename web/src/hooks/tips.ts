export function removeSuccess(res: any, pageRef: any) {
  if (res.code === 200) {
    ElNotification({
      title: "Success",
      message: "删除成功",
      type: "success",
    });
    if (pageRef) {
      unref(pageRef).query();
    }
  }
}

//新增成功后提示
export function submitSuccess(res: any, pageRef?: any) {
  if (res.code === 200) {
    ElNotification({
      title: "Success",
      message: "提交成功",
      type: "success",
    });

    if (pageRef) {
      unref(pageRef).query();
      unref(pageRef).handleClose();
    }
  }
}

//导入成功后提示
export function importSuccess(res: any, pageRef: any) {
  if (res.code === 200) {
    ElNotification({
      title: "Success",
      message: "导入成功",
      type: "success",
    });

    if (pageRef) {
      unref(pageRef).query();
    }
  }
}
export function exportSuccess(res: any) {
  if (res.code === 200) {
    ElNotification({
      title: "Success",
      message: "导出成功",
      type: "success",
    });
  }
}
export function uploadSuccess(res: any) {
  if (res.code === 200) {
    ElNotification({
      title: "Success",
      message: "上传成功",
      type: "success",
    });
  }
}
export function validateFail() {
  ElNotification({
    title: "Warning",
    message: "必填字段校验不通过,请先填写",
    type: "warning",
  });
}
