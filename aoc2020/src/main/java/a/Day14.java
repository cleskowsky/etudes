package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Day14 {
    public static boolean DEBUG = false;

    public static void main(String[] args) throws Exception {
        for (String a : args) {
            System.out.println(a);
        }

        for (String a : args) {
            switch (a) {
                case "-v":
                    DEBUG = true;
                    break;
                default:
                    throw new Exception("Unknown argument: " + a);
            }
        }

//        I haven't done bit shifting in quite a while
//        Back to the basics! :)
//        System.out.println(1 << 6 ^ 1 << 1);
//         66

//        int n = 11;
//        n = n | (1 << 6);
//        n = n & ~(1 << 1);
//        System.out.println(n);

        // and for 0
        // Xs should be 0
        // 0 && 1 => 0 good
        // 0 && 0 => 0 good

        // or for 1
        // Xs should be 1
        // 1 && 0 => 0 bad
        // 1 && 1 => 1 good

        // Our initialization vector includes mask updates and store instructions
//        var initV = Files.readAllLines(Path.of("inputs/14sample.txt"));
        var initV = Files.readAllLines(Path.of("inputs/14.txt"));
        var mem = new HashMap<Integer, Long>();
        long onesMask = 0;
        long zerosMask = Long.MAX_VALUE;
        for (String s : initV) {
            if (s.length() == 0) {
                System.out.println("");
                continue;
            }
            switch (s.substring(0, 4)) {
                case "mask":
                    onesMask = 0;
                    zerosMask = 0;
                    char[] c = s.split("=")[1].trim().toCharArray();
                    for (int i = 0; i < c.length; i++) {
                        switch (c[i]) {
                            case '1':
                                onesMask = onesMask | (1l << 35 - i);
                                break;
                            case '0':
                                zerosMask = zerosMask | (1l << 35 - i);
                                break;
                            case 'X':
                                break;
                            default:
                                throw new Exception("Couldn't parse bit: " + c[i]);
                        }
                    }
                    zerosMask = ~zerosMask;
                    if (DEBUG) {
                        System.out.println("Input: " + new String(c));
                        System.out.println("ZerosMask: " + Long.toBinaryString(zerosMask));
                        System.out.println("OnesMask: " + Long.toBinaryString(onesMask));
                    }
                    break;
                case "mem[":
                    if (DEBUG) {
                        System.out.println(s);
                    }
                    String[] split = s.split("=");
                    long val = Long.parseLong(split[1].trim());
                    split = split[0].split("\\[");
                    int loc = Integer.parseInt(
                            split[1].substring(0, split[1].indexOf("]"))
                    );
                    val &= zerosMask;
                    val |= onesMask;
                    if (DEBUG) {
                        System.out.printf("%d %d%n", loc, val);
                    }
                    mem.put(loc, val);
                    break;
                default:
                    throw new Exception("Couldn't parse instruction: " + s);
            }
        }
        long sum = mem.values().stream()
                .collect(Collectors.summingLong(Long::longValue));
        System.out.println(sum);
        System.out.println(Long.MAX_VALUE);
        // too low: 243965416522 :/
    }
}
