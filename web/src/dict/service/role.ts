export function roles() {
  return Service.configuration.role.list().then((res: any) => {
    return res.data.map((item: any) => {
      return { label: item.roleName, value: item.id };
    });
  });
}
