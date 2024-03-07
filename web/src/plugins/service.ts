const modules = import.meta.glob(["@/apis/**.ts", "@/apis/**/*.ts"], {
  import: "default",
  eager: true,
});

const apis: any = {};

Object.keys(modules).forEach((key: any) => {
  const api = key.split("/");
  const newKey = api[api.length - 1].replace(".ts", "");
  apis[newKey] = modules[key];
});

export default apis;
