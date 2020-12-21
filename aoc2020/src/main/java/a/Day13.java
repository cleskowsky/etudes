package a;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

public class Day13 {
    @Data
    public static class Pair {
        int id, idx;

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

//        s = "17,x,13,19";
//        s = "67,7,59,61";
//        s = "1789,37,47,1889";
        s = "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,479,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,373,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19";

        busIDs = Input(s);

        long start = new Date().getTime();
        long tick = 0;
        while (true) {
            int sum = 0;
            for (int i = 0; i < busIDs.length; i++) {
                Pair b = busIDs[i];
                if ((tick + b.getIdx()) % b.getId() == 0) {
                    sum++;
                } else {
                    break;
                }
            }
            if (sum == busIDs.length) {
                break;
            }
            if (tick % 10000000 == 0) {
                // Let's see progress. This is going to take awhile
                System.out.println(tick);
            }
            tick++;
        }
        System.out.println(tick);
        // Got here : 1074979300000
        // Still no match :/
        System.out.println(new Date().getTime() - start);
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
        return busIDs.toArray(new Pair[]{});

//        // Using an arraylist
//        var busIDs = new ArrayList<Integer>();
//        for (String b : s.split(",")) {
//            if (b.equals("x")) {
//                busIDs.add(0);
//            } else {
//                busIDs.add(Integer.parseInt(b));
//            }
//        }

    }
}
