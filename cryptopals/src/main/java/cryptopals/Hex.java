package cryptopals;

public class Hex {

    /**
     * Converts hex string to binary
     */
    public static byte[] toBinary(String src) {
        int len = src.length();
        byte[] retval = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            retval[i / 2] = (byte) ((Character.digit(src.charAt(i), 16) << 4)
                    + Character.digit(src.charAt(i + 1), 16));
        }

        return retval;
    }
}
