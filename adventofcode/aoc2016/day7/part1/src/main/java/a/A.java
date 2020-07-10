package a;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class A {
    public boolean isAnABBA(String s) {
        if (s == null || s.length() != 4) {
            return false;
        }
        // We can't make an abba with a hypernet
        // start symbol
        if (s.contains("[")) {
            return false;
        }
        char[] arr = s.toCharArray();
        return arr[0] == arr[3] &&
                arr[1] == arr[2] &&
                arr[0] != arr[1];
    }

    public boolean supportsTls(String ip) {
        boolean found = false;
        boolean hypernet = false;
        for (int i = 0; i <= ip.length() - 4; i++) {
            if (ip.charAt(i) == '[') {
                hypernet = true;
            }
            if (ip.charAt(i) == ']') {
                hypernet = false;
            }
            String s = ip.substring(i, i + 4);
            if (isAnABBA(s)) {
                if (hypernet) {
                    return false;
                }
                found = true;
            }
        }
        return found;
    }

    public static void main(String[] args) {
        A a = new A();
        int cnt = 0;

        try (FileReader fr = new FileReader("in.txt")) {
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (a.supportsTls(line)) {
                    System.out.println(line);
                    cnt++;
                }
            }
            System.out.println("Ips supporting tls: " + cnt);
        } catch (IOException e) {
            System.err.format("Except: %s%n", e);
        }

    }
}
