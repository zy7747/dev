const app = import.meta.glob(["@/icons/app/**.svg"], {
  eager: true,
});

const system = import.meta.glob(["@/icons/system/**.svg"], {
  eager: true,
});

const flag = import.meta.glob(["@/icons/flag/**.svg"], {
  eager: true,
});

const icons: any = {
  app,
  system,
  flag,
};

export default function iconSelect(iconList: string) {
  return Object.keys(icons[iconList]).map((key: string) => {
    return key.replace(".svg", "").replace(`/src/icons/${iconList}/`, "");
  });
}
