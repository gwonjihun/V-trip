import { toStringByFormatting } from "./utils";

function dateFilter(value) {
  const split = value.split(" ");
  if (split[0] === toStringByFormatting(new Date())) return split[1];
  return split[0];
}

function tooLongText(value, length) {
  if (value.length < length) return value;
  return value.slice(0, length) + "...";
}

export { dateFilter, tooLongText };
