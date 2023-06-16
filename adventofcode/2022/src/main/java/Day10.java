import util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Day10 {
    public static void main(String[] args) {
        System.out.println(execute(FileUtils.readLines("10_sample")));
        System.out.println(execute(FileUtils.readLines("10")));
    }

    /**
     * Execute program
     */
    static int execute(List<String> prg) {
        var crt = new CRT();
        var cpu = new CPU().crt(crt);
        for (String instr : prg) {
            var split = instr.split(" ");
            switch (split[0]) {
                case "addx" -> cpu.add(Integer.parseInt(split[1]));
                case "noop" -> cpu.noop();
            }
        }
        System.out.println(crt);
        return cpu.signal();
    }

    static class CPU {
        private int cycle = 0;
        private int x = 1;
        private CRT crt;

        // Part 1
        private int signal = 0;

        public void tick() {
            cycle++;
            if (List.of(20, 60, 100, 140, 180, 220).contains(cycle)) {
                signal += cycle * x;
            }
            crt.drawPixel();
        }

        public void add(int n) {
            tick();
            tick();
            x += n;
            crt.sprite(x);
        }

        public void noop() {
            tick();
        }

        public int signal() {
            return signal;
        }

        public CPU crt(CRT crt) {
            this.crt = crt;
            return this;
        }
    }

    static class CRT {
        private int penX = 0;
        private int penY = 0;

        private List<Integer> sprite = List.of(0, 1);
        private final List<String> buffer = new ArrayList<>();
        private int screenHeight = 6;
        private int screenWidth = 40;

        public CRT() {
            for (int i = 0; i < screenHeight; i++) {
                buffer.add("");
            }
        }

        public void sprite(int x) {
            sprite = List.of(x - 1, x, x + 1);
        }

        public void drawPixel() {
            var buff = buffer.get(penY);

            if (sprite.contains(penX)) {
                buff += "#";
            } else {
                buff += '.';
            }
            buffer.set(penY, buff);

            penX++;
            if (penX % screenWidth == 0) {
                penX = 0;
                penY++;
            }
        }

        @Override
        public String toString() {
            return String.join("\n", buffer);
        }
    }
}
