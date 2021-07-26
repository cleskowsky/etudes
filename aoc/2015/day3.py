data = open('day3.txt').read()

ways = {
    '>': (1, 0),
    '<': (-1, 0),
    '^': (0, 1),
    'v': (0, -1)
}

# Part 1

santa = (0, 0)
seen = {santa}

def move(p, w):
    """
    Returns next point p' from p in direction w
    """
    return p[0] + ways[w][0], p[1] + ways[w][1]

for d in data:
    santa = move(santa, d)
    seen.add(santa)

print(len(seen))

# Part 2

santas = [(0, 0), (0, 0)]
seen = {(0, 0)}

for i, c in enumerate(data):
    x = i % 2
    santas[x] = move(santas[x], c)
    seen.add(santas[x])

print(len(seen))
