import re


# Given a garbled line of text which is a calibration value find the
# first number in the line and the last and combine them to get back
# the lost calibration value

# eg '1abc2'
# calibration value 12

# Another interesting eg 'treb7uchet' only has a single digit (first_digit = last_digit)
# calibration value = 77

def calibration_value(line):
    """Returns calibration value from string"""
    nums = [int(x) for x in re.findall(r'[0-9]', line)]
    return nums[0] * 10 + nums[-1]


assert calibration_value("1abc2") == 12
assert calibration_value("1abc23") == 13
assert calibration_value("treb7uchet") == 77

# Part 1
print('part 1 sample answer:', sum(calibration_value(x)
                                   for x in open('day1_sample.txt').readlines()))

print('part 1 answer:', sum(calibration_value(x)
                            for x in open('day1_in.txt').readlines()))

# Part 2
digits = ['zero', '0',
          'one', '1',
          'two', '2',
          'three', '3',
          'four', '4',
          'five', '5',
          'six', '6',
          'seven', '7',
          'eight', '8',
          'nine', '9']

word_to_number = {'zero': 0,
                  'one': 1,
                  'two': 2,
                  'three': 3,
                  'four': 4,
                  'five': 5,
                  'six': 6,
                  'seven': 7,
                  'eight': 8,
                  'nine': 9}


def first_digit(s: str):
    """Returns numbers in s
    * Numbers can be words or digits (eg 'one' or 1)
    * Numbers can overlap (eg oneight -> [1, 8])"""

    print(f'\nEntered first_digit s={s}')

    min_i = len(s)
    val = None
    for digit in digits:
        if digit in s:
            i = s.find(digit)
            print(f'found digit={digit} at i={i}')
            if i < min_i:
                print(f'updated val (digit={digit} at i={i} comes before val={val} at i={min_i})')
                min_i = s.find(digit)
                try:
                    val = int(digit)
                except ValueError:
                    val = word_to_number[digit]

    if val is None:
        raise ValueError(f"Couldn't find number in {s}")

    return val


# today I learned python truthy ...
print(bool(-1))  # True
print(bool(0))  # False
print(bool(1))  # True
print(bool(''))  # False
print(bool('a'))  # True
print(bool('None'))  # False

assert first_digit('zoneight234') == 1
assert first_digit('4nineeightseven2') == 4

# test invalid calibration string
try:
    first_digit('a')
    raise ValueError('expect exception is thrown')
except ValueError:
    pass
