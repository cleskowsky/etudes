package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Day8 {
    public static void main(String[] args) throws Exception {
        String[] prg = Files.readAllLines(Path.of("inputs/8.txt")).toArray(new String[0]);

        // Part a
        System.out.println(runProgram(prg));

        // Part b
        for (int i = 0; i < prg.length; i++) {
            String stmt = prg[i];
            String[] split = stmt.split(" ");
            if (split[0].equals("jmp")) {
                prg[i] = String.format("%s %s", "nop", split[1]);
            } else if (split[0].equals("nop")) {
                prg[i] = String.format("%s %s", "jmp", split[1]);
            }
            int sum = runProgram(prg);
            if (sum == -1) {
                // Loop detected
            } else {
                System.out.println(sum);
            }
            prg[i] = stmt;
        }
    }

    public static int runProgram(String[] prg) {
        Map<Integer, Integer> seen = new HashMap<>();
        int pc = 0;
        int sum = 0;

        while (true) {
            String stmt = prg[pc];
            // Exit if we've been here before
            int n = seen.getOrDefault(pc, 0);
            if (n > 0) {
                return -1;
            }
            seen.put(pc, 1);

            String[] split = stmt.split(" ");
            String instr = split[0];
            int arg = Integer.parseInt(split[1]);
            switch (instr) {
                case "acc":
                    sum += arg;
                    pc++;
                    break;
                case "jmp":
                    pc += arg;
                    if (pc < 0 || pc > prg.length) {
                        System.out.println("We just jumped outside our program. pc: " + pc);
                    }
                    break;
                case "nop":
                    pc++;
                    break;
                default:
                    System.out.println("Found an invalid instruction: " + instr);
                    break;
            }

            // Prg finished
            if (pc >= prg.length) {
                break;
            }
        }
        return sum;
    }
}
