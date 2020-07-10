import re
from collections import Counter


def parse(line):
    name = ''.join(line.split('-')[:-1])
    sector = int(re.search(r'\d+', line).group(0))
    checksum = re.search(r"\[(.*)\]", line).group(1)
    return name, sector, checksum


def is_room(name, checksum):
    """Return true if a room is valid"""
    # Count how often each letter appears in the
    # encrypted room name
    letter_freq = Counter(''.join(name))
    sorted_letter_freq = sorted(letter_freq, key=lambda L: (-letter_freq[L], L))
    return checksum == ''.join(sorted_letter_freq[:5])


def decrypt(name, sector):
    shift = sector % 26
    # shift c to a value between 0..25
    # add the shift and mod 26 to ensure a
    # value in the 0..25 range
    decrypted_name = ''
    for c in name:
        decrypted_name += chr(ord('a') + ((ord(c) - ord('a') + shift) % 26))
    return decrypted_name


with open('in.txt', 'r') as fh:
    for line in fh:
        name, sector, checksum = parse(line)
        if is_room(name, checksum):
            print(decrypt(name, sector), sector)
