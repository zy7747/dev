export function user() {
  return Service.configuration.roles.list().then((res: any) => {
    return res;
  });
}
