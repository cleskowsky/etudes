/**
 * Given a program, execute instructions in our
 * mini language, returning when the halt instruction
 * is encountered ...
 * 
 * Opcodes:
 * 1 - add
 * 2 - mult
 * 99 - halt
 */
function compute(s) {
    let prg = s.trim().split(',').map(x => Number.parseInt(x, 10));

    // Program counter
    let pc = 0;

    // Opcode
    let oc = prg[pc];

    while (true) {
        // Processing loop
        switch (oc) {
            case 1: {
                // Add
                let memloc1 = prg[pc + 1];
                let memloc2 = prg[pc + 2];
                let outloc = prg[pc + 3];
                prg[outloc] = prg[memloc1] + prg[memloc2];
                pc += 4;
                break;
            }
            case 2: {
                // Mult
                let memloc1 = prg[pc + 1];
                let memloc2 = prg[pc + 2];
                let outloc = prg[pc + 3];
                prg[outloc] = prg[memloc1] * prg[memloc2];
                pc += 4;
                break;
            }
            case 99:
                return prg;
        }

        oc = prg[pc];
    }
}
console.log(compute("1,0,0,0,99"));
console.log(compute("2,4,4,5,99,0"));
console.log(compute("1,1,1,4,99,5,6,0,99"));
console.log(compute("1,9,10,3,2,3,11,0,99,30,40,50"));

// Now for my puzzle input

var fs = require('fs');

// If I don't specify the string encoding I get back
// a buffer not string from readFileSync ...

console.log(compute(fs.readFileSync('input/2.txt', 'utf8').trim())[0]);

// Not right : 1210687
// :/

// Ah forgot to start with special values in pos 1,2
// Answer: 4090689
