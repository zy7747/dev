// 下划线转换驼峰
export function toHump(name: string) {
  // eslint-disable-next-line no-useless-escape
  return name.replace(/\_(\w)/g, function (_all, letter: string) {
    return letter.toUpperCase();
  });
}
