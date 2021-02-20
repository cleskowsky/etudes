import re

required_fields = {'byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid'}

Passport = dict
valid_passport = required_fields.issubset


def parse_passport(s):
    return Passport(re.findall(r'([a-z]+):([^\s]+)', s))


passports = list(map(parse_passport, open('input/4.txt').read().split('\n\n')))
print(sum([1 for pp in passports if (valid_passport(pp))]))

field_validators = dict(
    byr=lambda v: 1920 <= int(v) <= 2002,
    iyr=lambda v: 2010 <= int(v) <= 2020,
    eyr=lambda v: 2020 <= int(v) <= 2030,
    hgt=lambda v: ((v.endswith('cm')) and 150 <= int(v[:-2]) <= 193 or
                   (v.endswith('in')) and 59 <= int(v[:-2]) <= 76),
    hcl=lambda v: re.match(r'#[0-9a-f]{6}$', v),
    ecl=lambda v: re.match('(amb|blu|brn|gry|grn|hzl|oth)$', v),
    pid=lambda v: re.match(r'[0-9]{9}$', v)
)

cnt = 0
for pp in passports:
    if required_fields.issubset(pp) and all([field_validators[field](pp[field]) for field in required_fields]):
        cnt += 1
print(cnt)
