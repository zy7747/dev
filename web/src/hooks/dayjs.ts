import dayjs from "dayjs";

export function getDateNow() {
  return dayjs().format("YYYY-MM-DD");
}

export function getDateAfterMonth(month: number) {
  return dayjs().add(month, "month").format("YYYY-MM-DD");
}
