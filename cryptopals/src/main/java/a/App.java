package a;

import com.google.common.io.BaseEncoding;

import java.util.Base64;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        // Challenge 1
        // Use google guava base16 (hex) encoder / decoder to get byte[]
        var b = BaseEncoding.base16().decode("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d".toUpperCase());
        // Java has a built in base64 encoder
        System.out.println(Base64.getEncoder().encodeToString(b));
    }
}
