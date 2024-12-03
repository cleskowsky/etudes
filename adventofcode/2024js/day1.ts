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

// Part 1

function totalDistance(left: number[], right: number[]) {
  let sum = 0;
  for (let i = 0; i < left.length; i++) {
    sum += Math.abs(left[i] - right[i]);
  }
  return sum;
}

// Part 2

function similarityScore(left: number[], right: number[]) {
  const frequencies = new Map();
  for (const x of right) {
    frequencies.set(x, frequencies.get(x) + 1 || 1);
  }

  let sum = 0;
  for (let i = 0; i < left.length; i++) {
    sum += left[i] * frequencies.get(left[i]) || 0;
  }
  return sum;
}

const { left: l1, right: r1 } = parseInput("day1_sample.txt");
console.log(totalDistance(l1, r1));
console.log(similarityScore(l1, r1));

const { left: l2, right: r2 } = parseInput("day1_in.txt");
console.log(totalDistance(l2, r2));
console.log(similarityScore(l2, r2));
