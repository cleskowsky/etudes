class Report:
    def __init__(self, levels):
        self.levels = levels

    def __repr__(self):
        return 'Record: [' + ', '.join(map(str, self.levels)) + ']'


def increasing(r):
    for i in range(len(r.levels) - 1):
        curr = r.levels[i]
        next = r.levels[i + 1]
        if curr > next:
            return False

    return True


def decreasing(r):
    for i in range(len(r.levels) - 1):
        curr = r.levels[i]
        next = r.levels[i + 1]
        if curr < next:
            return False

    return True


def safe_report(r):
    if increasing(r) or decreasing(r):
        for i in range(len(r.levels) - 1):
            step = abs(r.levels[i + 1] - r.levels[i])
            if step < 1 or step > 3:
                return False

        print('safe report')
        print(r)
        return True

    return False


assert safe_report(Report([1, 3, 6, 7, 9]))
assert not safe_report(Report([1, 2, 7, 8, 9]))


def parse_input(filename):
    records = []

    lines = open(filename).read().splitlines()
    for line in lines:
        levels = line.split(' ')
        levels = list(map(int, levels))
        records.append(Report(levels))

    return records


def recover_report(r):
    r_levels = r.levels
    for i in range(len(r_levels)):
        r2 = Report(r_levels.copy())
        del r2.levels[i]
        if safe_report(r2):
            print('recovered report')
            print('unsafe: ', r)
            print('safe: ', r2)
            return True

    return False


# x = parse_input('day2_sample.txt')
x = parse_input('day2.txt')
print(x)

safe_reports = 0
recovered_reports = 0
for r in x:
    if safe_report(r):
        safe_reports += 1
    elif recover_report(r):
        recovered_reports += 1

print('safe reports: ', safe_reports)
print('recovered reports: ', safe_reports + recovered_reports)
