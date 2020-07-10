import re
from collections import Counter


def is_room(r):
    """Return true if a room is valid"""
    encrypted_name = r.split('-')[:-1]
    # Count how often each letter appears in the
    # encrypted room name
    letter_freq = Counter(''.join(encrypted_name))
    sorted_letter_freq = sorted(letter_freq, key=lambda L: (-letter_freq[L], L))
    checksum = re.search(r"\[(.*)\]", r).group(1)
    return checksum == ''.join(sorted_letter_freq[:5])


# print(is_room("aaaaa-bbb-z-y-x-123[abxyz]"))

sum = 0
with open('in.txt', 'r') as fh:
    for line in fh:
        if is_room(line):
            sector_id = re.search(r'\d+', line).group(0)
            sum += int(sector_id)
print(sum)

# 280724 is too high
# tested without confirming sort order of the repeated letters
# 278221 is the right answer
