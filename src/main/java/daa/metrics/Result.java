package daa.metrics;

public class Result {
    public final String algorithm;
    public final String input;
    public final int n;
    public final long timeMs;
    public final long comparisons;
    public final int maxDepth;

    public Result(String algorithm, String input, int n, long timeMs, long comparisons, int maxDepth) {
        this.algorithm = algorithm;
        this.input = input;
        this.n = n;
        this.timeMs = timeMs;
        this.comparisons = comparisons;
        this.maxDepth = maxDepth;
    }
}