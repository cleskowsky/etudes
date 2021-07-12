from itertools import combinations
from operator import mul

def input(file_name):
    data = open(file_name).read()
    return [tuple(map(int, x.split("x"))) for x in data.split("\n")]

boxes = input("day2.txt")
print(boxes[:5])

def wrapping_paper_needed(b):
    sides = [mul(*x) for x in combinations(b, 2)]
    sides.sort()
    # print(sides, sum(sides), sum(sides + sides), sum(sides + sides + sides[:1]))
    return sum(sides + sides + sides[:1])

print(sum(map(wrapping_paper_needed, boxes)))