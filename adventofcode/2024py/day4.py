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


g = parse_grid(sample_input)
print(g)

# NW, N, NE
# W,   , E
# SW, S, SE
headings = [
    (-1, -1), (0, -1), (1, -1),
    (-1, 0), (1, 0),
    (-1, 1), (0, 1), (1, 1)
]


def find_word(pt, h, g, len=4):
    s = ''
    try:
        for i in range(0, len):
            next_char = g[(pt[0] + h[0] * i,
                           pt[1] + h[1] * i)]
            s = s + next_char
    except KeyError:
        return False
    return s == 'XMAS'


x = 0
for k, v in g.items():
    if v == 'X':
        for h in headings:
            if find_word(k, h, g):
                x += 1
print(x)
