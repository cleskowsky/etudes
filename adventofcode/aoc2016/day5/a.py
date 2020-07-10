import hashlib

door_id = 'wtnhxymk'

def find_passcode1():
    for i in range(10000000):
        hash = hashlib.md5()
        hash.update(door_id.encode('utf-8'))
        hash.update(str(i).encode('utf-8'))
        d = hash.hexdigest()
        if d.startswith('00000'):
            print(i, d)

# 2231254 0000027b9705c7e6fa3d4816c490bbfd
# 2440385 00000468c8625d85571d250737c47b5a
# 2640705 0000013e3293b49e4c78a5b43b21023b
# 3115031 0000040bbe4509b48041007dec6123bd
# 5045682 00000b11810477f9e49840991fb2151e
# 8562236 00000cc461c8945671046cf632be4473
# 9103137 000007c1da6865df78b2c0addf28913d
# 9433034 00000700ce8beb0a8ffc83fa9986d577
# 9669127 00000d76f344ea0a8643bb2bcf0323c4

# 2414bc77

def find_passcode():
    print(1)

find_passcode()
