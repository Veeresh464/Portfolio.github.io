import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DataCollector {
    public static List<float[]> getMetrics(int numInst) {
        List<float[]> metrics = new ArrayList<>();
        Random rand = new Random();

        try (FileWriter logFile = new FileWriter("metrics.log")) {
            for (int i = 0; i < numInst; i++) {
                float cpu = rand.nextInt(100);  // CPU Usage %
                float mem = rand.nextInt(100);  // Memory Usage %
                metrics.add(new float[]{cpu, mem});
                logFile.write("Inst " + (i + 1) + " - CPU: " + cpu + ", Mem: " + mem + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }

        return metrics;
    }

    // Method to normalize the metrics
    public static void normalize(List<float[]> metrics) {
        float maxCpu = 0, maxMem = 0;

        for (float[] m : metrics) {
            if (m[0] > maxCpu) maxCpu = m[0];
            if (m[1] > maxMem) maxMem = m[1];
        }

        for (float[] m : metrics) {
            m[0] /= maxCpu;
            m[1] /= maxMem;
        }
    }

    // Method to display the normalized metrics
    public static void showMetrics(List<float[]> metrics) {
        System.out.println("Normalized Metrics (CPU, Mem):");
        for (int i = 0; i < metrics.size(); i++) {
            System.out.printf("Inst %d: %.2f, %.2f%n", i + 1, metrics.get(i)[0], metrics.get(i)[1]);
        }
    }

    // Main method
    public static void main(String[] args) {
        int numInst = 5;
        List<float[]> metrics = getMetrics(numInst);
        normalize(metrics);
        showMetrics(metrics);
    }
}
