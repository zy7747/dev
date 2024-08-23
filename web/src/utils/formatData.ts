import formatdata from "zy-formatdata";

const format = import.meta.glob(["@/utils/formatData/**.ts"], {
  eager: true,
});

console.log(123, formatdata.toHump("user_name"));

const formatDataMap: any = {};

Object.values(format).map((value: any) => {
  Object.keys(value).map((key: any) => {
    formatDataMap[key] = value[key];
  });
});

export default formatDataMap;
