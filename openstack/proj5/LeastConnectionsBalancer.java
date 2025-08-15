import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Instance {
    String name;
    int conns;

    Instance(String name, int conns) {
        this.name = name;
        this.conns = conns;
    }
}

public class LeastConnectionsBalancer {

    public static String leastConns(List<Instance> instances) {
        if (instances.isEmpty()) return "";

        int minConns = Integer.MAX_VALUE;
        String chosen = null;
        Instance chosenInst = null;

        for (Instance inst : instances) {
            if (inst.conns < minConns) {
                minConns = inst.conns;
                chosen = inst.name;
                chosenInst = inst;
            }
        }

        // Simulate processing time
        Random rand = new Random();
        for (int i = 0; i < 100000000; i++) {
            int temp = rand.nextInt(100);
        }

        // Update chosen instance's connections
        if (chosenInst != null) {
            chosenInst.conns++;
        }

        return chosen;
    }

    public static void main(String[] args) {
        List<Instance> instances = new ArrayList<>();
        instances.add(new Instance("Inst1", 5));
        instances.add(new Instance("Inst2", 2));
        instances.add(new Instance("Inst3", 8));
        instances.add(new Instance("Inst4", 4));
        instances.add(new Instance("Inst5", 1));

        for (int i = 0; i < 10; i++) {
            String inst = leastConns(instances);
            System.out.println("Request " + (i + 1) + " assigned to " + inst + " using Least Conns algo");
        }
    }
}
