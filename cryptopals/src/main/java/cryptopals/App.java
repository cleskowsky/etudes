package cryptopals;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        var x = 0x4 << 4;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(String.format("%8s",
                Integer.toBinaryString(x)).replace(' ', '0'));
    }

    Map<Character, String> hexToBinaryMap = Map.ofEntries(
            Map.entry('0', "0000"),
            Map.entry('1', "0001"),
            Map.entry('2', "0010"),
            Map.entry('3', "0011"),
            Map.entry('4', "0100"),
            Map.entry('5', "0101"),
            Map.entry('6', "0110"),
            Map.entry('7', "0111"),
            Map.entry('8', "1000"),
            Map.entry('9', "1001"),
            Map.entry('a', "1010"),
            Map.entry('b', "1011"),
            Map.entry('c', "1100"),
            Map.entry('d', "1101"),
            Map.entry('e', "1110"),
            Map.entry('f', "1111"));

    /**
     * Convert hex string to binary
     */
    public String hexToBinary(String hex) {

        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have even length");
        }

        var ret = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            var c1 = hex.charAt(i);
            var c2 = hex.charAt(i + 1);
            ret.append(hexToBinaryMap.get(c1));
            ret.append(hexToBinaryMap.get(c2));
        }

        return ret.toString();
    }
}
