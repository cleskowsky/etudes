function parseInput(file: string) {
  const left = [];
  const right = [];

  const lines = Deno.readTextFileSync(file);
  for (const line of lines.split("\n")) {
    const split = line.split(/\s+/);
    left.push(parseInt(split[0]));
    right.push(parseInt(split[1]));
  }

  return {
    left: left.sort(),
    right: right.sort(),
  };
}

// const locations = parseInput("day1_sample.txt");
const {left, right} = parseInput("day1_in.txt");

function sumDistances(left: number[], right: number[]) {
  let sum = 0;
  for (let i = 0; i < left.length; i++) {
    const x = left[i];
    const y = right[i];
    sum += Math.abs(y - x);
  }
  return sum;
}
console.log(sumDistances(left, right));

const frequencies = new Map()
for (const x of right) {
  frequencies.set(x, frequencies.get(x) + 1 || 1);
}
console.log(frequencies);

function sumSimilarity(left: number[]) {
  let sum = 0;
  for (let i = 0; i < left.length; i++) {
    sum += left[i] * frequencies.get(left[i]) || 0;
  }
  return sum;
}
console.log(sumSimilarity(left));
