package a;

import java.util.ArrayList;

public class Day13 {
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

        // Partb
        s = "17,x,13,19";
        s = "67,7,59,61";
        s = "1789,37,47,1889";
        s = "7,13,x,x,59,x,31,19";
//        s = "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,479,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,373,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19";

        var busIDs = new ArrayList<Integer>();
        for (String b : s.split(",")) {
            if (b.equals("x")) {
                busIDs.add(0);
            } else {
                busIDs.add(Integer.parseInt(b));
            }
        }

        // Parta

        int min = Integer.MAX_VALUE;
        int earliestBusID = 0;
        int waitTime = 0;
        for (Integer b : busIDs) {
            if (b == 0) {
                continue;
            }
            int nextDepartureTimeAfterTS = (ts / b) * b + b;
            if (nextDepartureTimeAfterTS < min) {
                min = nextDepartureTimeAfterTS;
                earliestBusID = b;
                waitTime = nextDepartureTimeAfterTS - ts;
            }
        }
        System.out.println(earliestBusID);
        System.out.println(earliestBusID * waitTime);

        // Partb
        long tick = 0;
        while (true) {
            int sum = 0;
            for (int i = 0; i < busIDs.size(); i++) {
                int b = busIDs.get(i);
                if (b == 0) {
                    // Trivially add 1 here otherwise my sum == busIDs.size() check below will fail
                    sum++;
                    continue;
                }
                if ((tick + i) % b == 0) {
                    sum++;
                } else {
                    break;
                }
            }
            if (sum == busIDs.size()) {
                break;
            }
            if (tick % 1000000 == 0) {
                // Let's see progress. This is going to take awhile
                System.out.println(tick);
            }
            tick++;
        }
        System.out.println(tick);
        // Got here : 1074979300000
        // Still no match :/
    }
}
