import { handleTree } from "@/utils/formatData";

export function menusTree() {
  return Service.configuration.menu.list().then((res: any) => {
    const menuList = res.data.map((item: any) => {
      return { ...item, label: item.title, value: item.id };
    });

    return handleTree(menuList);
  });
}
