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

print(sum(calibration_value(x)
          for x in open('day1_sample.txt').readlines()))

print(sum(calibration_value(x)
          for x in open('day1_in.txt').readlines()))
