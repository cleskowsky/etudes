package org.example;

public class App {
    public static void main(String[] args) {
        System.out.println(1);
    }

    public int findFloor(String s) {
        return s.chars().map(c -> c == '(' ? 1 : -1).sum();
    }
}
