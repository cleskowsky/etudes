def depth_increases(readings=None):
    if readings is None:
        readings = []

    cnt = 0
    prev = None
    for d in readings:
        if prev:
            if d > prev:
                cnt += 1
        prev = d

    return cnt


sample_input = list(map(int, open('day1_sample.txt').read().split()))
input = list(map(int, open('day1.txt').read().split()))

# Part 1
print(depth_increases(sample_input))
print(depth_increases(input))


# Part 2

def sliding_window(readings=None):
    cnt = 0
    prev = None
    for w in range(len(readings) - 2):
        x = sum(readings[w:w + 3])
        print(w, x)
        if prev:
            if x > prev:
                cnt += 1
        prev = x

    return cnt


print(sliding_window(sample_input))
print(sliding_window(input))
