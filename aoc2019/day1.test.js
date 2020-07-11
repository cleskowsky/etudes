const fs = require('fs');

const needsFuel = require('./day1');

test('Fuel to lift mass: 100756 is 33583', () => {
    expect(needsFuel([100756])).toBe(33583);
});

test('With puzzle input', () => {
    const input = fs.readFileSync('input/1.txt', "utf-8")
        .split('\n')
        .map((x) => parseInt(x))
        .filter((x) => !isNaN(x));
    expect(needsFuel(input)).toBe(3390596);
});