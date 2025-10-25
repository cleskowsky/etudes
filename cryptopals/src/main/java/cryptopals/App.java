package cryptopals;

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

        System.out.println(Integer.toBinaryString(15));
    }

    /**
     * Converts hex to binary
     */
    public byte[] toBinary(byte[] src) {
        if (src.length % 2 != 0) {
            throw new IllegalArgumentException("byte[] src must have even length");
        }

        var dst = new byte[src.length / 2];
        for (int i = 0; i < src.length; i += 2) {
            dst[i / 2] = (byte) (src[i] << 4 | src[i + 1]);
        }

        return dst;
    }
}
