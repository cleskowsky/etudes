import static java.lang.IO.println;

void main() throws IOException {
    assert isValid(1188511880);
    assert !isValid(1188511885);

    var t1 = invalidIds(1188511880, 1188511890);
    assert 1 == t1.size();
    assert 1188511885 == t1.getFirst();

    var t2 = invalidIds(446443, 446449);
    assert 1 == t2.size();
    assert 446446 == t2.getFirst();

    assert isValid(101);
    assert !isValid(592592);

    // sample
//    var input = Files.readString(Path.of("inputs/day2_sample.txt"));
//    println(input);
//
//    var badIds = new ArrayList<Long>();
//
//    for (String range : input.split(",")) {
//        var split = range.split("-");
//        badIds.addAll(invalidIds(
//                Long.parseLong(split[0]),
//                Long.parseLong(split[1])
//        ));
//    }
//    println(badIds);
//    println(badIds.stream().reduce(0L, Long::sum));

    // part 1
    var input = Files.readString(Path.of("inputs/day2.txt"));
    println(input);

    var badIds = new ArrayList<Long>();

    for (String range : input.split(",")) {
        var split = range.split("-");
        badIds.addAll(invalidIds(
                Long.parseLong(split[0]),
                Long.parseLong(split[1])
        ));
    }

    // too low : 9283763888 :/
    // That's not the right answer; your answer is too low. If you're stuck, make sure you're using the full input data; there are also some general tips on the about page, or you can ask for hints on the subreddit. Please wait one minute before trying again. [Return to Day 2]
    // i had a bad cast to int when i was stuffing digits into my byte buffer :'(

    println(badIds);
    println(badIds.stream().reduce(0L, Long::sum));
    println(badIds.stream().mapToLong(Long::longValue).sum());

    // part 2
}

List<Long> invalidIds(long begin, long end) {
    var val = new ArrayList<Long>();
    for (long i = begin; i <= end; i++) {
        if (!isValid(i)) {
            val.add(i);
        }
    }
    return val;
}

boolean isValid(long id) {

    // extract digits to array
    byte[] buf = new byte[50];
    int size = 0;
    while (id > 0) {
        buf[size] = (byte) (id % 10);
        id = id / 10;
        size++;
    }

    // ids with an odd number of digits are valid
    if (size % 2 == 1) {
        return true;
    }

    // look at pairs starting at 0, mid
    // an id is valid when we find a pair of different numbers
    for (int i = 0, j = size / 2; j < size; i++, j++) {
        if (buf[i] != buf[j]) {
            return true;
        }
    }

    // we're not valid so we must be invalid
    return false;
}