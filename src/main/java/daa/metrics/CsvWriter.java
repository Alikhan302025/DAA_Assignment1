package daa.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    public static void write(String filename, List<Result> results) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("algorithm,input,n,time_ms,comparisons,max_depth\n");
            for (Result r : results) {
                writer.write(String.format("%s,%s,%d,%d,%d,%d\n",
                        r.algorithm, r.input, r.n, r.timeMs, r.comparisons, r.maxDepth));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}