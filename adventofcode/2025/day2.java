import static java.lang.IO.println;

void main() {
    assert isValid(1188511880);
    assert !isValid(1188511885);

    var t1 = invalidIds(1188511880, 1188511890);
    assert 1 == t1.size();
    assert 1188511885 == t1.getFirst();

    var t2 = invalidIds(446443, 446449);
    assert 1 == t2.size();
    assert 446446 == t2.getFirst();

    // part 1
    // part 2
}

List<Long> invalidIds(long begin, long end) {
    println("invalidIds called begin=" + begin + " end=" + end);
    var val = new ArrayList<Long>();
    for (long i = begin; i <= end; i++) {
        if (!isValid(i)) {
            val.add(i);
        }
    }
    return val;
}

boolean isValid(long id) {
    println("isValid called id=" + id);

    // extract digits to array
    int[] buf = new int[50];
    int size = 0;
    while (id > 0) {
        buf[size] = (int) id % 10;
        id = id / 10;
        size++;
    }

    // look at pairs starting at 0, mid
    // an id is valid when we find a pair of different numbers
    for (int i = 0, j = size / 2; j < size - 1; i++, j++) {
        if (buf[i] != buf[j]) {
            println("valid");
            return true;
        }
    }

    println("invalid");
    return false;
}
