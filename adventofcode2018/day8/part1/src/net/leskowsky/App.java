package net.leskowsky;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) {
        String in = "";
        try {
            in = new String(Files.readAllBytes(Paths.get("in.txt")));
        } catch(IOException e) {
            e.printStackTrace();
        }
        Node tree = Input.parse(in);
        System.out.println(tree.sum());
    }
}
