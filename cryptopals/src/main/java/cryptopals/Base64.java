package cryptopals;

public class Base64 {

    /**
     * Converts binary to base64
     */
    public static String toBase64(byte[] src) {
        var encoder = java.util.Base64.getEncoder();
        return encoder.encodeToString(src);
    }
}
