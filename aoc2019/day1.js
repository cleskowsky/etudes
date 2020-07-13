// Part 1

// Returns fuel required to lift a list of integer masses
function massesNeedFuel(masses) {
    return masses
        .map((m) => Math.floor(m / 3) - 2)
        .reduce((sum, m) => sum += m, 0);
}

module.exports = {
    // Part 1
    massesNeedFuel: massesNeedFuel,

    // Part 2
    fuelHasWeightToo: fuelHasWeightToo
}

// Part 2

function getFuelForMass(m) {
    return Math.floor(m / 3) - 2;
}

function fuelHasWeightToo(masses) {
    let sum = 0;
    masses.forEach((m) => {
        let f = getFuelForMass(m);
        sum += f;

        let x = getFuelForMass(f);
        while (x > 0) {
            sum += x;

            // Calculate fuel needed for f
            f = x;
            x = getFuelForMass(f);
        }
    });
    return sum;
}
