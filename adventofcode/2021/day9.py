def parse_input(file):
    """Return a heightmap as a 2d array from data in file"""
    hm = []
    for line in open(file).readlines():
        line = line.strip()
        hm.append([int(x) for x in line])
    return hm


def get_neighbours(i, j, hm):
    """Returns nearest neighbours of item at (i, j) in the heightmap"""
    neighbours = [(-1, 0), (0, -1), (1, 0), (0, 1)]

    val = []
    for x in neighbours:
        dx = i + x[0]
        dy = j + x[1]

        # Are we still on the map?
        if dx < 0 or dx > len(hm[0]):
            continue

        if dy < 0 or dy > len(hm):
            continue

        try:
            n = hm[dy][dx]
            val.append(n)
        except IndexError:
            pass

    return val


def main():
    hm = parse_input('in/9.txt')
    parta = []
    for i in range(len(hm[0])):
        for j in range(len(hm)):
            adj = get_neighbours(i, j, hm)
            loc = hm[j][i]
            smaller_adj = [x for x in adj if loc >= x]
            print('i:', i, ', j:', j, ', loc:', loc, ', adj:', adj, ', smaller_adj:', smaller_adj)
            if len(smaller_adj) == 0:
                print('low point')
                parta.append(loc + 1)
    print(parta)
    print(sum(parta))


if __name__ == '__main__':
    main()
    # 222 is too low!
    # I tried with the sample input. This isn't right for the sample
    # input anyways. Shouldn't have wasted a guess ...
    # 1685 is too high ... :/
