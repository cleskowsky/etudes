public class Day12 {
    public static void main(String[] args) {
        System.out.println(1);
    }

    public static class InputParser {

        public static Farm parse(String s) {
            return new Farm(1);
        }
    }

    record Farm(int x) {
        public int plots() {
            return x;
        }
    }
}
