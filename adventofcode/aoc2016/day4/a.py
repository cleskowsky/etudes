# qzmt-zixmtkozy-ivhz-343

# print(ord('q') + 343 % 26)
# print(ord('z'))
# print(chr(ord('q') + 5))
# print((ord('z') - ord('a') + 5) % 26)
# print(chr(ord('a') + ((ord('z') - ord('a') + 5) % 26)))


def decrypt(name, sector_id):
    shift = sector_id % 26
    # shift c to a value between 0..25
    # add the shift and mod 26 to ensure a
    # value in the 0..25 range
    decrypted_name = ''
    for c in name:
        decrypted_name += chr(ord('a') + ((ord(c) - ord('a') + shift) % 26))
    return decrypted_name

print(decrypt('qzmtzixmtkozyivhz', 343))
