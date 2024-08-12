export function menusTree() {
  return Service.configuration.menu.list().then((res: any) => {
    const menuList = res.data.map((item: any) => {
      return { ...item, label: item.title, value: item.id };
    });

    return FormatData.handleTree(menuList);
  });
}

export function menusList() {
  return Service.configuration.menu.list().then((res: any) => {
    const menuList = res.data.map((item: any) => {
      return { ...item, label: item.title, value: item.id };
    });

    return menuList;
  });
}
