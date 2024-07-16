export function permissionTree() {
  return Service.configuration.permission.list().then((res: any) => {
    const permissionList = res.data.map((item: any) => {
      return { ...item, label: item.name, value: item.id, parentId: "1" };
    });

    return [
      {
        id: "1",
        parentId: null,
        label: "全部",
        value: "1",
        children: permissionList,
      },
    ];
  });
}
