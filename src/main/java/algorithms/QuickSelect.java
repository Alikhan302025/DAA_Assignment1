package algorithms;
import metrics.Metrics;

import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid input: array is empty or k is out of range");
        }

        metrics.startTimer();
        int result = quickSelect(a, 0, a.length - 1, k, 1, metrics);
        metrics.stopTimer();
        return result;
    }

    private static int quickSelect(int[] arr, int left, int right, int k, int depth, Metrics metrics) {
        metrics.updateDepth(depth);

        while (left <= right) {
            if (left == right) {
                return arr[left];
            }

            int[] pivots = partition(arr, left, right, metrics);
            int lt = pivots[0]; // Конец части, где элементы меньше пивота
            int gt = pivots[1]; // Начало части, где элементы больше пивота

            if (k <= lt) {
                right = lt;
            } else if (k >= gt) {
                left = gt;
            } else {
                return arr[k];
            }
            depth++;
            metrics.updateDepth(depth);
        }
        return -1;
    }

    private static int[] partition(int[] arr, int left, int right, Metrics metrics) {
        int pivotIndex = left + random.nextInt(right - left + 1);
        swap(arr, left, pivotIndex);
        int pivot = arr[left];

        int lt = left;
        int gt = right;
        int i = left + 1;

        while (i <= gt) {
            metrics.incrementComparisons();
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else {
                metrics.incrementComparisons();
                if (arr[i] > pivot) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }
        }
        return new int[]{lt - 1, gt + 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}