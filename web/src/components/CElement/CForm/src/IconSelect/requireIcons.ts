const app = import.meta.glob(["@/icons/app/**.svg"], {
  eager: true,
});

const files = import.meta.glob(["@/icons/files/**.svg"], {
  eager: true,
});

const flag = import.meta.glob(["@/icons/flag/**.svg"], {
  eager: true,
});

const menu = import.meta.glob(["@/icons/menu/**.svg"], {
  eager: true,
});

const icons: any = {
  app,
  files,
  flag,
  menu,
};

export default function iconSelect(iconList: string) {
  return Object.keys(icons[iconList]).map((key: string) => {
    return key.replace(".svg", "").replace(`/src/icons/${iconList}/`, "");
  });
}
