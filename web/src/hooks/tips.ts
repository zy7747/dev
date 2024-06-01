export function removeSuccess(res: any, pageRef: any) {
  if (res.code === 200) {
    ElMessage({
      message: "删除成功",
      type: "success",
    });
    unref(pageRef).query();
  }
}

//新增成功后提示
export function submitSuccess(res: any, pageRef: any) {
  if (res.code === 200) {
    ElMessage({
      message: "提交成功",
      type: "success",
    });
    unref(pageRef).query();
    unref(pageRef).handleClose();
  }
}

//导入成功后提示
export function importSuccess(res: any, pageRef: any) {
  if (res.code === 200) {
    ElMessage({
      message: "导入成功",
      type: "success",
    });
    unref(pageRef).query();
  }
}
export function exportSuccess(res: any) {
  if (res.code === 200) {
    ElMessage({
      message: "导出成功",
      type: "success",
    });
  }
}
