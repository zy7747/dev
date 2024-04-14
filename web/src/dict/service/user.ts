export function user() {
  return Service.configuration.roles.list().then((res: any) => {
    console.log(res);
  });
}
