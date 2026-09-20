package daa.algorithms;

import daa.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmsTest {
    private final Random random = new Random();

    @Test
    void testSortsOn100RandomArrays() {
        for (int i = 0; i < 100; i++) {
            int size = random.nextInt(1000) + 1;
            int[] original = generateRandomArray(size);

            int[] mergeArr = Arrays.copyOf(original, size);
            int[] quickArr = Arrays.copyOf(original, size);
            int[] expectedArr = Arrays.copyOf(original, size);

            Arrays.sort(expectedArr);
            MergeSort.sort(mergeArr, new Metrics());
            QuickSort.sort(quickArr, new Metrics());

            assertArrayEquals(expectedArr, mergeArr, "MergeSort failed on random array");
            assertArrayEquals(expectedArr, quickArr, "QuickSort failed on random array");
        }
    }

    @Test
    void testEdgeCases() {
        int[] empty = {};
        MergeSort.sort(empty, new Metrics());
        QuickSort.sort(empty, new Metrics());
        assertArrayEquals(new int[]{}, empty);

        int[] single = {42};
        MergeSort.sort(single, new Metrics());
        QuickSort.sort(single, new Metrics());
        assertArrayEquals(new int[]{42}, single);

        int[] equal = {7, 7, 7, 7, 7};
        int[] equalQuick = {7, 7, 7, 7, 7};
        MergeSort.sort(equal, new Metrics());
        QuickSort.sort(equalQuick, new Metrics());
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, equal);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, equalQuick);

        int[] sorted = {1, 2, 3, 4, 5};
        int[] quickSorted = {1, 2, 3, 4, 5};
        MergeSort.sort(sorted, new Metrics());
        QuickSort.sort(quickSorted, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, quickSorted);
    }

    @Test
    void testQuickSortDepthOnSortedArray() {
        int n = 100_000;
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = i;
        }

        Metrics metrics = new Metrics();
        QuickSort.sort(sorted, metrics);

        double maxAllowedDepth = 2 * (Math.log(n) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth,
                "Depth exceeded: " + metrics.getMaxDepth() + " > " + maxAllowedDepth);
    }

    @Test
    void testQuickSelectOn100RandomArrays() {
        for (int i = 0; i < 100; i++) {
            int size = random.nextInt(1000) + 1;
            int[] original = generateRandomArray(size);
            int k = random.nextInt(size);

            int[] expectedArr = Arrays.copyOf(original, size);
            Arrays.sort(expectedArr);

            int result = QuickSelect.select(Arrays.copyOf(original, size), k, new Metrics());
            assertEquals(expectedArr[k], result, "QuickSelect failed to find k-th element");
        }
    }


    @Test
    void testQuickSelectExceptions() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuickSelect.select(new int[]{}, 0, new Metrics());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            QuickSelect.select(new int[]{1, 2, 3}, 5, new Metrics());
        });
    }

    private int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }
}