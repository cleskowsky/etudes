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
function computer(prg) {
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
            default:
                console.log('Bad op code: ' + oc);
                throw new Error("Shouldn't get here");
        }

        oc = prg[pc];
    }
}

function Input(s) {
    return s.trim().split(',').map(x => Number.parseInt(x, 10));
}

console.log(computer(Input("1,0,0,0,99")));
console.log(computer(Input("2,4,4,5,99,0")));
console.log(computer(Input("1,1,1,4,99,5,6,0,99")));
console.log(computer(Input("1,9,10,3,2,3,11,0,99,30,40,50")));

// Now for my puzzle input

var fs = require('fs');

// If I don't specify the string encoding I get back
// a buffer not string from readFileSync ...

const s = fs.readFileSync('input/2.txt', 'utf8');

// Not right : 1210687 :/
// Ah forgot to start with special values in pos 1,2
// Answer: 4090689
console.log(computer(Input(s))[0]);

// Part 2 wants me to find the values that would produce a specific
// output: 19690720
// I'll be replacing pos 1, and 2 in the input array with values
// between 0 and 99

var start = Date.now();
for (i = 0; i < 100; i++) {
    for (j = 0; j < 100; j++) {
        let x = Input(s);
        x[1] = i;
        x[2] = j;
        if (computer(x)[0] === 19690720) {
            console.log(`${i} ${j}`);
            process.exit();
        }
    }
}
console.log('Elapsed: ' + Date.now() - start);

// 77 33
