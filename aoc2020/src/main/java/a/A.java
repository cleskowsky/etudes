package a;

import java.util.Arrays;

public class A {
    public static void main(String[] args) {
        System.out.println("Hello, world");
        Integer[] arr = new Integer[]{3, 1, 2};
        Arrays.sort(arr, (x, y) -> {
            if (x < y) {
                return 1;
            } else if (x > y) {
                return -1;
            } else {
                return 0;
            }
    });
//        Arrays.sort(arr);
        System.out.println(arr);
}
}
