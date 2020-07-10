package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String s = "";
        try {
            s = new String(Files.readAllBytes(Paths.get("in.txt")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Node t = Input.parse(s);
        System.out.println(t.count());
        System.out.println(t.value());
    }
}
