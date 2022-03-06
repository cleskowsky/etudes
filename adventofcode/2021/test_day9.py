from day9 import get_neighbours


def test_get_neighbours():
    data = [
        [2, 1, 9, 9, 9, 4, 3, 2, 1, 0],
        [3, 9, 8, 7, 8, 9, 4, 9, 2, 1]
    ]
    x = get_neighbours(0, 0, data)
    assert x == [1, 3]
    x = get_neighbours(9, 0, data)
    assert x == [1, 1]
    x = get_neighbours(0, 1, data)
    assert x == [2, 9]
