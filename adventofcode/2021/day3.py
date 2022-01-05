# data = open('day3_sample.txt').read().split()
data = open('day3.txt').read().split()


def most_common_bit(data, i):
    b = [x[i] for x in data]
    return '1' if b.count('1') >= b.count('0') else '0'


def least_common_bit(data, i):
    b = [x[i] for x in data]
    return '1' if b.count('1') < b.count('0') else '0'


def gamma_rate(data):
    """Return most common bits by column in data"""
    x = [most_common_bit(data, i) for i in range(len(data[0]))]
    return ''.join(x)


def epsilon_rate(data):
    """Returns least common bits by column in data"""
    x = [least_common_bit(data, i) for i in range(len(data[0]))]
    return ''.join(x)


def oxygen_generator_rating(strs):
    for i in range(len(strs[0])):
        if len(strs) == 1:
            return strs[0]
        bit = most_common_bit(strs, i)
        strs = [x for x in strs if x[i] == bit]

    if len(strs) == 1:
        return strs[0]
    else:
        raise RuntimeError("Couldn't find oxygen generator value")


def co2_scrubber_rating(strs):
    for i in range(len(strs[0])):
        if len(strs) == 1:
            return strs[0]
        bit = least_common_bit(strs, i)
        strs = [x for x in strs if x[i] == bit]

    if len(strs) == 1:
        return strs[0]
    else:
        raise RuntimeError("Couldn't find co2 scrubber value")


# Part 1

gr = int(gamma_rate(data), 2)
er = int(epsilon_rate(data), 2)
print(gr * er)

# Part 2

og = int(oxygen_generator_rating(data), 2)
cs = int(co2_scrubber_rating(data), 2)
print(og * cs)
