import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Server {
    String serverName;
    int assignedWeight;
    int currentScore;
    int effectiveWeight;

    Server(String name, int weight) {
        this.serverName = name;
        this.assignedWeight = weight;
        this.effectiveWeight = weight;
        this.currentScore = 0;
    }
}

public class WeightedRoundRobinSimulator {

    public static String pickServer(List<Server> servers) {
        if (servers.isEmpty()) return "";

        Server bestChoice = null;
        int totalWeightsSum = 0;

        for (Server srv : servers) {
            // Increase current score by the server's effective weight
            srv.currentScore += srv.effectiveWeight;
            // Keep track of total effective weight sum
            totalWeightsSum += srv.effectiveWeight;

            // Select the server with highest current score
            if (bestChoice == null || srv.currentScore > bestChoice.currentScore) {
                bestChoice = srv;
            }
        }

        bestChoice.currentScore -= totalWeightsSum;

        Random randomGenerator = new Random();
        for (int i = 0; i < 100000000; i++) {
            int dummy = randomGenerator.nextInt(100);
        }
        return bestChoice.serverName;
    }

    public static void main(String[] args) {
        List<Server> serverList = new ArrayList<>();
        serverList.add(new Server("ServerAlpha", 1));
        serverList.add(new Server("ServerBeta", 2));
        serverList.add(new Server("ServerGamma", 3));

        for (int requestNumber = 1; requestNumber <= 10; requestNumber++) {
            String assignedServer = pickServer(serverList);
            System.out.println("Request " + requestNumber + " routed to " + assignedServer);
        }
    }
}
