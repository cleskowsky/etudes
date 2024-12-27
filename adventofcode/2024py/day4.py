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

the_grid = {}
print(type(the_grid))


def parse_grid(s):
    """Parse grid string into {point: value} dict"""
    g = {}
    for i, row in enumerate(s.splitlines()):
        parse_row(row, i, g)
    return g


def parse_row(s, row_number, g):
    """Add row to grid"""
    print(s)


g = parse_grid(sample_input)
print(g)
