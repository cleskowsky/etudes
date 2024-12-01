// 3   4
// 4   3
// 2   5
// 1   3
// 3   9
// 3   3

// sample data

const left = [3, 4, 2, 1, 3, 3].sort();
const right = [4, 3, 5, 3, 9, 3].sort();

// differences list (abs)

const x = left.map((val, i) => {
  return Math.abs(right[i] - val);
});
console.log(x);

// sum

let sum = 0;
for (let i = 0; i < x.length; i++) {
  sum += x[i];
}
console.log(sum);
