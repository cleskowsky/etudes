map(int, ['a'])
PART_B = True

# moves = open('day2_sample.txt').read().split('\n')
moves = open('day2.txt').read().split('\n')

x = 0
y = 0
aim = 0
for m in moves:
    parts = m.split()
    d = parts[0]
    how_much = int(parts[1])
    if d == 'forward':
        x += how_much
        if PART_B:
            y += aim * how_much
    elif d == 'down':
        if PART_B:
            aim += how_much
        else:
            y += how_much
    elif d == 'up':
        if PART_B:
            aim -= how_much
        else:
            y -= how_much

print(x, y, x * y)
