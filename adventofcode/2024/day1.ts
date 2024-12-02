function parseInput(file: string) {
  const left = [];
  const right = [];

  const lines = Deno.readTextFileSync(file);
  for (const line of lines.split("\n")) {
    const split = line.split(/\s+/);
    left.push(split[0]);
    right.push(split[1]);
  }

  return {
    left: left.sort(),
    right: right.sort(),
  };
}

// const locations = parseInput("day1_sample.txt");
const locations = parseInput("day1_in.txt");

function sumDistances(left: string[], right: string[]) {
  let sum = 0;
  for (let i = 0; i < left.length; i++) {
    const x = parseInt(left[i]);
    const y = parseInt(right[i]);
    sum += Math.abs(y - x);
  }
  return sum;
}
console.log(sumDistances(locations.left, locations.right));

const frequencies = new Map()
for (const x of locations.right) {
  frequencies.set(x, frequencies.get(x) + 1 || 1);
}
console.log(frequencies);

function sumSimilarity(left: string[]) {
  let sum = 0;
  for (let i = 0; i < left.length; i++) {
    const x = parseInt(left[i]);
    sum += x * frequencies.get(left[i]) || 0;
  }
  return sum;
}
console.log(sumSimilarity(locations.left));
