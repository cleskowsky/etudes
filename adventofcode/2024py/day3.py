import re


def mult(s):
    lhs, rhs = map(int, re.findall(r'[0-9]+', s))
    return lhs * rhs


def run_it(prog):
    statements = re.findall(r'mul\([0-9]+,[0-9]+\)', prog)
    return sum(mult(s) for s in statements)


assert run_it('xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))') == 161

# Part 1

part1_input = open('day3.txt').read()
print(run_it(part1_input))
