const fs = require('fs');

const { massesNeedFuel, fuelHasWeightToo } = require('./day1');

test('Fuel to lift mass: 100756 is 33583', () => {
    expect(massesNeedFuel([100756])).toBe(33583);
});

function input() {
    return fs.readFileSync('input/1.txt', "utf-8")
        .split('\n')
        .map((x) => parseInt(x))
        .filter((x) => !isNaN(x));
}

test('With puzzle input', () => {
    expect(massesNeedFuel(input())).toBe(3390596);
});

test('Test fuel has weight too', () => {
    expect(fuelHasWeightToo([1969])).toBe(966);
    expect(fuelHasWeightToo([100756])).toBe(50346);
    expect(fuelHasWeightToo(input())).toBe(5083024);
});
