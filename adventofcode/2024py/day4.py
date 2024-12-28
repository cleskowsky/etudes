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


def find_word(pt, dir, g, word='XMAS'):
    s = ''
    try:
        for i in range(0, len(word)):
            next_char = g[(pt[0] + dir[0] * i,
                           pt[1] + dir[1] * i)]
            s = s + next_char
    except KeyError:
        return False
    return s == word


# part 1

x = 0
for k, v in g.items():
    if v == 'X':
        for h in headings:
            if find_word(k, h, g):
                x += 1
print(x)

# part 2

x = 0
for k, v in g.items():
    if v == 'A':
        stroke_1 = any([
            find_word((k[0] - 1, k[1] - 1), (1, 1), g, 'MAS'),
            find_word((k[0] - 1, k[1] - 1), (1, 1), g, 'SAM')
        ])
        stroke_2 = any([
            find_word((k[0] - 1, k[1] + 1), (1, -1), g, 'MAS'),
            find_word((k[0] - 1, k[1] + 1), (1, -1), g, 'SAM')
        ])
        if stroke_1 and stroke_2:
            x += 1
print(x)
