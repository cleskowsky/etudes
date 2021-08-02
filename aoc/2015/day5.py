def has_3_vowels(s):
    """
    Returns true if s has a repeated char
    eg 'abb' has a repeated char
    'abc' does not
    """
    n = 0
    for c in s:
        if c in "aeiou":
            n += 1
    if n < 3:
        print(f'{s} does not have 3 vowels')
        return False

    return True


def has_repeated_char(s):
    prev = ''
    for c in s:
        if prev == c:
            return True
        prev = c

    print(f'{s} does not have a repeated char')
    return False

def contains_special_string(s):
    x = ['ab', 'cd', 'pq', 'xy']
    for y in x:
        if y in s:
            print(f'{s} contains a bad string {y}')
            return False

    return True

def is_nice(s):
    return has_3_vowels(s) and has_repeated_char(s) and contains_special_string(s)


print(is_nice('ugknbfddgicrmopn'))
print(is_nice('jchzalrnumimnmhp'))
print(is_nice('haegwjzuvuyypxyu'))


data = open('day5.txt').readlines()
print(data)

# Part 1
print(len([x for x in data if is_nice(x)]))