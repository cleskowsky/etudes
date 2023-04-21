package a;

import com.google.common.io.BaseEncoding;

import java.io.IOException;
import java.util.Base64;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    static {
        try {
            LogManager.getLogManager().readConfiguration(
                    App.class.getResourceAsStream("/logging.properties")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Logger logger = Logger.getLogger(App.class.getName());

    static byte[] hexToBytes(String s) {
        return BaseEncoding.base16().decode(s.toUpperCase());
    }

    static String bytesToHex(byte[] b) {
        return BaseEncoding.base16().encode(b).toLowerCase();
    }

    static byte[] xorBytes(byte[] arr1, byte[] arr2) {
        var x = new byte[arr1.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = (byte) (arr1[i] ^ arr2[i]);
        }
        return x;
    }

    public static void main(String[] args) {

        // Challenge 1 Base64 encode a hex string
        // Use google guava base16 (hex) encoder / decoder to get byte[]
        var b = hexToBytes(
                "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d".toUpperCase());
        // Then java's built-in base64 encoder
        System.out.println(Base64.getEncoder().encodeToString(b));

        // Challenge 2 XOR to hex strings
        var x = xorBytes(
                hexToBytes("1c0111001f010100061a024b53535009181c"),
                hexToBytes("686974207468652062756c6c277320657965")
        );
        System.out.println(bytesToHex(x));
    }

    public static boolean maybeEnglish(String s) {
        String shrdlu = "etaoin shrdlu";

        var count = 0;
        for (var c : s.toLowerCase().toCharArray()) {
            if (shrdlu.indexOf(c) >= 0) {
                count++;
            } else if (!Character.isAlphabetic(c)) {
                count--;
            }
        }

        logger.finest("String: " + s + ", score: " + count);

        return count > 15;
    }
}