from itertools import combinations
from operator import mul

def input(file_name):
    data = open(file_name).read()
    return [tuple(map(int, x.split("x"))) for x in data.split("\n")]

boxes = input("day2.txt")
print(boxes[:5])

# Part 1

def wrapping_paper_needed(b):
    sides = [mul(*x) for x in combinations(b, 2)]
    sides.sort()
    # print(sides, sum(sides), sum(sides + sides), sum(sides + sides + sides[:1]))
    return sum(sides + sides + sides[:1])

print(sum(map(wrapping_paper_needed, boxes)))

# Part 2

ribbon_length_needed = 0
for b in boxes:
    sides = list(combinations(b, 2))

    # Calculate ribbon required for each side
    # Add min ribbon length to running sum
    perimeters = [2 * x[0] + 2 * x[1] for x in sides]
    ribbon_length_needed += min(perimeters)

    # Calculate ribbon for box volume and add that to running sum too
    bow_length = b[0] * b[1] * b[2]
    ribbon_length_needed += bow_length

    print(f"box {b}, sides {sides}, perimeters {perimeters}, min(perimeters) {min(perimeters)}, bow_length {bow_length}")


# Final amount of ribbon
print(ribbon_length_needed)