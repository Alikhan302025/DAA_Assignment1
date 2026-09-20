package daa.bench;

import daa.algorithms.MergeSort;
import daa.algorithms.QuickSelect;
import daa.algorithms.QuickSort;
import daa.metrics.Metrics;
import daa.metrics.Result;
import daa.util.ArrayUtils;
import daa.util.InputType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Benchmark {
    private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final int RUNS = 5;

    public List<Result> run() {
        List<Result> results = new ArrayList<>();

        for (InputType type : InputType.values()) {
            for (int n : SIZES) {
                System.out.println("Тестируем " + type + ", размер: " + n);

                results.add(benchmarkSort("MergeSort", type, n));
                results.add(benchmarkSort("QuickSort", type, n));
                results.add(benchmarkSelect("QuickSelect", type, n));
            }
        }
        return results;
    }

    private Result benchmarkSort(String algoName, InputType type, int n) {
        long[] times = new long[RUNS];
        Metrics lastMetrics = null;

        for (int i = 0; i < RUNS; i++) {
            int[] arr = ArrayUtils.generate(type, n);
            lastMetrics = new Metrics();

            if (algoName.equals("MergeSort")) {
                MergeSort.sort(arr, lastMetrics);
            } else {
                QuickSort.sort(arr, lastMetrics);
            }
            times[i] = lastMetrics.getTimeMs();
        }

        Arrays.sort(times);
        long medianTime = times[RUNS / 2];

        return new Result(algoName, type.name().toLowerCase(), n, medianTime,
                lastMetrics.getComparisons(), lastMetrics.getMaxDepth());
    }

    private Result benchmarkSelect(String algoName, InputType type, int n) {
        long[] times = new long[RUNS];
        Metrics lastMetrics = null;

        for (int i = 0; i < RUNS; i++) {
            int[] arr = ArrayUtils.generate(type, n);
            int k = n / 2;
            lastMetrics = new Metrics();

            QuickSelect.select(arr, k, lastMetrics);
            times[i] = lastMetrics.getTimeMs();
        }

        Arrays.sort(times);
        long medianTime = times[RUNS / 2];

        return new Result(algoName, type.name().toLowerCase(), n, medianTime,
                lastMetrics.getComparisons(), lastMetrics.getMaxDepth());
    }
}
