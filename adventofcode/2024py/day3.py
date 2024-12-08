import re


def recover_prog(s):
    stmt_finders = [
        r"mul\([0-9]+,[0-9]+\)",
        r"do\(\)",
        r"don't\(\)"
    ]
    stmts = re.findall(('|').join(stmt_finders), s)

    recovered_prog = []
    drop = False
    for i, val in enumerate(stmts):
        if stmts[i].startswith("don't"):
            drop = True
        elif stmts[i].startswith("do"):
            drop = False
        else:
            if not drop:
                recovered_prog.append(val)

    print(recovered_prog)
    return recovered_prog


def mult(s):
    lhs, rhs = map(int, re.findall(r'[0-9]+', s))
    return lhs * rhs


def compute(prog):
    statements = recover_prog(prog)
    return sum(mult(s) for s in statements)


assert compute("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))") == 161

# Part 1

part1_input = open('day3.txt').read()
print(compute(part1_input))

assert compute(part1_input) == 179571322

# Part 2


recovered_prog = [
    'mul(2,4)',
    'mul(8,5)'
]
assert recover_prog("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))") == recovered_prog

part2_input = open('day3.txt').read()
print(compute(part2_input))

assert compute(part2_input) == 103811193
