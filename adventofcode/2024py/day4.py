sample_input = """\
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX"""
print(sample_input)


def parse_grid(s):
    """Parse grid string into {point: value} dict"""
    g = {}
    for i, row in enumerate(s.splitlines()):
        parse_row(row, i, g)
    return g


def parse_row(s, row_number, g):
    """Add row to grid"""
    for i, val in enumerate(s):
        g[(i, row_number)] = val


g = parse_grid(open('inputs/day4.txt').read())
# g = parse_grid(sample_input)
print(g)

# NW, N, NE
# W,   , E
# SW, S, SE
headings = [
    (-1, -1), (0, -1), (1, -1),
    (-1, 0), (1, 0),
    (-1, 1), (0, 1), (1, 1)
]


def find_xmas(pt, h, g):
    s = ''
    try:
        for i in range(0, 4):
            next_char = g[(pt[0] + h[0] * i,
                           pt[1] + h[1] * i)]
            s = s + next_char
    except KeyError:
        return False
    return s == 'XMAS'


# part 1

x = 0
for k, v in g.items():
    if v == 'X':
        for h in headings:
            if find_xmas(k, h, g):
                x += 1
print(x)


# part 2

def find_mas(pt, h, g):
    # look for SAM or MAX from top left to bottom right
    s = ''
    try:
        for i in range(0, 3):
            next_char = g[(pt[0] + h[0] * i,
                           pt[1] + h[1] * i)]
            s = s + next_char
    except KeyError:
        return False
    return s == 'MAS' or s == 'SAM'


x = 0
for k, v in g.items():
    if v == 'A':
        top_left = (k[0] - 1, k[1] - 1)
        bottom_left = (k[0] - 1, k[1] + 1)
        if find_mas(top_left, (1, 1), g) and find_mas(bottom_left, (1, -1), g):
            x += 1
print(x)
