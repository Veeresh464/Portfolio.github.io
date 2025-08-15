import java.util.ArrayList;
import java.util.List;

public class RoundRobinBalancer {

    private static int rrIndex = 0;

    public static String roundRobin(List<String> instances) {
        if (instances.isEmpty()) return "";

        String chosen = instances.get(rrIndex);
        rrIndex = (rrIndex + 1) % instances.size();

        System.out.println("Processing request for " + chosen + "...");

        // Simulate processing delay
        for (int i = 0; i < 100000000; i++) {
            int temp = i * i;
        }

        return chosen;
    }

    public static void main(String[] args) {
        List<String> instances = new ArrayList<>();
        instances.add("Inst1");
        instances.add("Inst2");
        instances.add("Inst3");
        instances.add("Inst4");
        instances.add("Inst5");

        for (int i = 0; i < 10; i++) {
            String inst = roundRobin(instances);
            System.out.println("Request " + (i + 1) + " assigned to " + inst + " using RR algo");
        }
    }
}
