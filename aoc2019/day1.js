// Returns fuel required to lift a list of integer masses
function needsFuel(m) {
    return m
        .map((x) => Math.floor(x / 3) - 2)
        .reduce((sum, x) => sum += x, 0);
}

module.exports = needsFuel;
