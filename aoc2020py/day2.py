import re


def parse_password_policy(p):
    a, b, c, pw = re.findall(r'[^-\s:]+', line)
    return (int(a), int(b), c, pw)


with open('input/2.txt') as f:
    cnt = 0
    for line in f:
        a, b, x, pw = parse_password_policy(line)
        n = pw.count(x)
        if a <= n <= b:
            cnt += 1

print(cnt)
