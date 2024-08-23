export function user() {
  return Service.config.roles.list().then((res: any) => {
    return res;
  });
}
