calories = """\
1000
2000
3000

4000

5000
6000

7000
8000
9000

10000"""

# calories = open('day1.txt').read()


def mapt(f, *seq):
    return tuple(map(f, *seq))


# parse
a = [mapt(int, x.split()) for x in calories.split('\n\n')]
print(a)

# part 1
print(max(sum(x) for x in a))

# part 2
# a = sorted(map(sum, a))
print(sum(sorted(sum(x) for x in a)[-3:]))
