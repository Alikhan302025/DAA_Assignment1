package daa.util;

import java.util.Random;

public class ArrayUtils {
    private static final Random random = new Random();

    public static int[] generate(InputType type, int n) {
        int[] arr = new int[n];
        switch (type) {
            case RANDOM:
                for (int i = 0; i < n; i++) arr[i] = random.nextInt();
                break;
            case SORTED:
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case DUPLICATES:
                for (int i = 0; i < n; i++) arr[i] = random.nextInt(10); // Значения от 0 до 9
                break;
        }
        return arr;
    }
}