import re


def parse_password_policy(p):
    a, b, c, pw = re.findall(r'[^-\s:]+', p)
    return (int(a), int(b), c, pw)


def input():
    with open('input/2.txt') as f:
        lines = f.readlines()
        return [parse_password_policy(l) for l in lines]


policies = input()

# Part 1
cnt = 0
for p in policies:
    a, b, x, pw = p
    n = pw.count(x)
    if a <= n <= b:
        cnt += 1
print(cnt)


# Part 2
def is_valid_password(p):
    a, b, x, pw = p
    return (x == pw[a - 1]) ^ (x == pw[b - 1])


a = [1 for p in policies if is_valid_password(p)]
print(sum(a))
