import hashlib

print(hashlib.md5('a'.encode('utf-8')).hexdigest())
x = 0
while True:
    h = hashlib.md5(f'bgvyzdsv{x}'.encode('utf-8')).hexdigest()
    if h.startswith('000000'):
        print(h, x)
        break
    x += 1
