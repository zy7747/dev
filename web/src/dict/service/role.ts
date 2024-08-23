export function roles() {
  return Service.config.role.list().then((res: any) => {
    return res.data.map((item: any) => {
      return { label: item.roleName, value: item.id };
    });
  });
}
