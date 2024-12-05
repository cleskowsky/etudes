from runpy import run_path


class Report:
    def __init__(self, levels):
        self.levels = levels

    def __repr__(self):
        return 'Record: [' + ', '.join(map(str, self.levels)) + ']'


def increasing(r):
    for i in range(len(r.levels) - 1):
        if r.levels[i] > r.levels[i + 1]:
            return False

    return True


def decreasing(r):
    for i in range(len(r.levels) - 1):
        if r.levels[i] < r.levels[i + 1]:
            return False

    return True


def safe_report(r):
    if increasing(r) or decreasing(r):
        for i in range(len(r.levels) - 1):
            step = abs(r.levels[i + 1] - r.levels[i])
            if step < 1 or step > 3:
                return False

        return True

    return False


assert not safe_report(Report([1, 2, 7, 8, 9]))
assert safe_report(Report([1, 3, 6, 7, 9]))


def parse_input(filename):
    records = []

    lines = open(filename).read().splitlines()
    for line in lines:
        levels = line.split(' ')
        levels = list(map(int, levels))
        records.append(Report(levels))

    return records


# x = parse_input('day2_sample.txt')
x = parse_input('day2.txt')
print(x)

n = 0
for r in x:
    if safe_report(r):
        print('safe report')
        print(r)
        n += 1
    else:
        print('unsafe report found')
        print(r)
        for i in range(len(r.levels)):
            print(r.levels)
            r2 = Report(r.levels.copy())
            del r2.levels[i]
            if safe_report(r2):
                n += 1
                break

print(n)
