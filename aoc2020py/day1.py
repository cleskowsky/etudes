import itertools

with open('input/1.txt') as f:
    expenses = []
    for line in f:
        expenses.append(int(line))

print(type(expenses[0]))
print(len(expenses))
print(sum(expenses))
print(len(list(itertools.permutations(expenses, 2))))

for t in itertools.combinations(expenses, 2):
    if t[0] + t[1] == 2020:
        x, y = t
        print(t)
print("Part 1", x * y)

for t in itertools.combinations(expenses, 3):
    if t[0] + t[1] + t[2] == 2020:
        x, y, z = t
        print(t)
print("Part 2", x * y * z)
