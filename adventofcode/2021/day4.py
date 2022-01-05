def get_row(b, n):
    return [b[i + n * 5] for i in range(5)]


def get_col(b, n):
    return [b[i * 5 + n] for i in range(5)]


def input():
    # draws, *b = open('day4_sample.txt').read().split('\n\n')
    draws, *b = open('day4.txt').read().split('\n\n')
    c = [x.split() for x in b]
    c = list(map(tuple, c))
    return draws.split(','), c


def bingo_winner(b, drawn, d):
    """Return true if draw d fills in a row or column in board b"""
    if d in b:
        x = b.index(d) % 5
        y = b.index(d) // 5
        r = get_row(b, y)
        c = get_col(b, x)
        return all([i in drawn for i in r]) or \
               all([i in drawn for i in c])
    return False


def bingo_score(b, drawn, d):
    not_drawn = [int(x) for x in b if x not in drawn]
    return sum(not_drawn) * int(d)


def bingo():
    draws, boards = input()

    winning_boards = set()
    drawn = set()
    for _, d in enumerate(draws):
        drawn.add(d)
        for b in boards:
            if bingo_winner(b, drawn, d):
                winning_boards.add(b)
                if len(winning_boards) == len(boards):
                    print(bingo_score(b, drawn, d))
                    return


bingo()
