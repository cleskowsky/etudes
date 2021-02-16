picture = open('input/3.txt').read().split()


def t(dx, dy=1):
    cnt = 0
    for i, r in enumerate(picture[::dy]):
        if r[dx * i % len(r)] == '#':
            cnt += 1
    return cnt


print(t(1), t(3))

print(t(1) * t(3) * t(5) * t(7) * t(1, 2))
