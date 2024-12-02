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
const locations = parseInput("day1_in.txt");

function sumDistances(left: number[], right: number[]) {
  let sum = 0;
  for (let i = 0; i < left.length; i++) {
    const x = left[i];
    const y = right[i];
    sum += Math.abs(y - x);
  }
  return sum;
}
console.log(sumDistances(locations.left, locations.right));

function sumSimilarity(left: number[], right: number[]) {
  let sum = 0;
  for (let i = 0; i < left.length; i++) {
    const x = left[i];
    let occurences = 0;
    for (let j = 0; j < left.length; j++) {
      if (right[j] === x) {
        occurences += 1;
      }
    }
    sum += x * occurences;
  }
  return sum;
}
console.log(sumSimilarity(locations.left, locations.right));
