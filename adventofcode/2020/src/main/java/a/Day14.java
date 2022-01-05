package a;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day14 {
    public static boolean debug = false;
    public static boolean partA = false;

    public static void main(String[] args) throws Exception {
        for (String a : args) {
            System.out.println(a);
        }

        for (String a : args) {
            switch (a) {
                case "-v":
                    debug = true;
                    break;
                case "-a":
                    partA = true;
                    break;
                default:
                    throw new Exception("Unknown argument: " + a);
            }
        }

        // Input contains SetMask and SetMem instructions
//        var input = Files.readAllLines(Path.of("inputs/14sample.txt"));
        var input = Files.readAllLines(Path.of("inputs/14.txt"));
        var mem = new HashMap<String, Long>();
        long onesMask = 0;
        long zerosMask = Long.MAX_VALUE;
        char[] mask = new char[]{};
        for (String s : input) {
            switch (s.substring(0, 2)) {
                case "ma":
                    if (partA) {
                        onesMask = 0;
                        zerosMask = 0;
                        char[] c = s.split(" = ")[1].toCharArray();
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
                        if (debug) {
                            System.out.println("Input: " + new String(c));
                            System.out.println("ZerosMask: " + Long.toBinaryString(zerosMask));
                            System.out.println("OnesMask: " + Long.toBinaryString(onesMask));
                        }
                    } else {
                        // I think the mask will be a bit simpler in part B. Let's see
                        mask = s.split(" = ")[1].toCharArray();
                        if (debug) {
                            System.out.println("Mask: " + new String(mask));
                            System.out.println("Mask length: " + mask.length);
                        }
                    }
                    break;
                case "me":
                    // Parse mem line
                    if (debug) {
                        System.out.println("Input line: " + s);
                    }
                    String[] split = s.split(" = ");
                    long val = Long.parseLong(split[1]);
                    split = split[0].split("\\[");
                    String addr = split[1].substring(0, split[1].indexOf("]"));

                    // Puzzles
                    if (partA) {
                        val &= zerosMask;
                        val |= onesMask;
                        if (debug) {
                            System.out.printf("Set addr: %s to value: %d%n", addr, val);
                        }
                        mem.put(addr, val);
                    } else {
                        // Applying mask will give address with floated bits this time
                        // Further processing needed to get real addresses
                        addr = Integer.toBinaryString(Integer.parseInt(addr));
                        // From apache commons. Nice :)
                        addr = StringUtils.leftPad(addr, 36, "0");
                        char[] addrMasked = new char[36];

                        char[] c = addr.toCharArray();
                        for (int i = 0; i < c.length; i++) {
                            if (mask[i] == '0') {
                                addrMasked[i] = c[i];
                                continue;
                            }
                            addrMasked[i] = mask[i];
                        }
                        if (debug) {
                            System.out.println("Masked address: " + new String(addrMasked));
                        }
                        var floated = getAddresses(addrMasked);
                        for (String f : floated) {
                            if (debug) {
                                System.out.println("Floated address: " + f);
                            }
                            mem.put(f, val);
                        }
                    }
                    break;
                default:
                    throw new Exception("Couldn't parse instruction: " + s);
            }
        }
        long sum = mem.values().stream()
                .collect(Collectors.summingLong(Long::longValue));
        System.out.println(sum);
    }

    public static List<String> getAddresses(char[] c) {
        var addr = new ArrayList<String>();
        addr.add("");
        for (int i = 0; i < c.length; i++) {
            if ("01X".indexOf(c[i]) == -1) {
                throw new RuntimeException("Invalid char: " + c[i]);
            }
            int n = addr.size();
            for (int j = 0; j < n; j++) {
                String s = addr.remove(j);
                switch (c[i]) {
                    case '0':
                    case '1':
                        addr.add(j, s + c[i]);
                        break;
                    case 'X':
                        addr.add(j, s + "0");
                        addr.add(s + "1");
                        break;
                    default:
                        throw new RuntimeException("Bad first bit: " + c[0]);
                }
            }
        }
        return addr;
    }
}
