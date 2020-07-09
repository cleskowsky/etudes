const openOrSenior = require('./app');

test('senior member', () => {
    expect(openOrSenior([[55, 21]])).toStrictEqual(['Senior']);
    expect(openOrSenior([[55, 1]])).toStrictEqual(['Open']);
    expect(openOrSenior([
        [55, 7],
        [55, 8],
        [1, 8]
    ])).toStrictEqual(['Open', 'Senior', 'Open']);
});
