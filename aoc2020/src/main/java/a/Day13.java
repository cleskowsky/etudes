package a;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

public class Day13 {
    @Data
    public static class Pair {
        private int id, idx;

        public Pair(int id, int idx) {
            this.id = id;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        // Java division returns an integer (not float), and floors the result
        // Just what I need :)
        System.out.println(939 / 59);

        // sample
        int ts = 939;
        String s = "7,13,x,x,59,x,31,19";

        // Parta
//        int ts = 1000417;
//        String s = "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,479,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,373,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19";

        Pair[] busIDs = Input(s);

        int min = Integer.MAX_VALUE;
        int earliestBusID = 0;
        int waitTime = 0;
        for (Pair b : busIDs) {
            int nextDepartureTimeAfterTS = (ts / b.getId()) * b.getId() + b.getId();
            if (nextDepartureTimeAfterTS < min) {
                min = nextDepartureTimeAfterTS;
                earliestBusID = b.getId();
                waitTime = nextDepartureTimeAfterTS - ts;
            }
        }
        System.out.println(earliestBusID);
        System.out.println(earliestBusID * waitTime);

        // Partb

        s = "17,x,13,19";
        s = "67,7,59,61";
        s = "1789,37,47,1889";
        s = "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,479,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,373,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19";

        // From LizTheGray completely. I am not quite sure about this yet. Made
        // a note to come back here. There's an idea here I'd like to understand
        busIDs = Input(s);
        long minValue = 0;
        long runningProduct = 1;
        for (Pair b : busIDs) {
            while ((minValue + b.getIdx()) % b.getId() != 0) {
                minValue += runningProduct;
            }
            runningProduct *= b.getId();
        }
        System.out.println(minValue);
    }

    public static Pair[] Input(String data) {

        var busIDs = new ArrayList<>();
        String[] split = data.split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("x")) {
                continue;
            }
            // x: bus number, y: index
            busIDs.add(new Pair(Integer.parseInt(split[i]), i));
        }
        var sorted = busIDs.toArray(new Pair[]{});
        Arrays.sort(sorted, (x, y) -> {
            if (x.getId() < y.getId()) {
                return 1;
            } else if (x.getId() > y.getId()) {
                return -1;
            } else {
                return 0;
            }
        });
        return sorted;
    }
}
