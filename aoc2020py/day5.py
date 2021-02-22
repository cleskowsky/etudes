import math


def bsp(low, high, s):
    for c in s:
        if c in 'FL':
            high -= math.ceil((high - low) / 2)
        else:
            low += math.ceil((high - low) / 2)
    return low


def seat_id(bp):
    row = bsp(0, 127, bp[:-3])
    col = bsp(0, 7, bp[-3:])
    return row * 8 + col

# Part a
boarding_passes = open('input/5.txt').read().split()
print(len(boarding_passes))
print(boarding_passes)
ids = [seat_id(bp) for bp in boarding_passes]
print(max(ids))

# Part b
missing = set(range(min(ids), max(ids))) - set(ids)
print(missing)
