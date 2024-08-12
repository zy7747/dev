//查找下标相同的一组
export function findConsecutiveElements(arr: any) {
  let result: any = [];
  if (arr.length === 0) return result;

  let startIndex = 0;
  let count = 1;

  for (let i = 1; i < arr.length; i++) {
    if (arr[i] === arr[i - 1]) {
      count++;
    } else {
      if (count > 1) {
        result.push({ startIndex: startIndex, count: count });
      }
      startIndex = i;
      count = 1;
    }
  }

  // Check for the last sequence
  if (count > 1) {
    result.push({ startIndex: startIndex, count: count });
  }

  return result;
}

// 示例使用
// const array = [1, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1];
// const result = findConsecutiveElements(array);
// console.log(result);
// 输出: [ { startIndex: 0, count: 2 }, { startIndex: 2, count: 3 }, { startIndex: 5, count: 2 }, { startIndex: 7, count: 4 } ]
