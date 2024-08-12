export function filesTree() {
  return Service.file.list({ fileType: "folder" }).then((res: any) => {
    const fileList = res.data.map((item: any) => {
      return { ...item, label: item.fileName, value: item.id };
    });

    return FormatData.handleTree(fileList);
  });
}
