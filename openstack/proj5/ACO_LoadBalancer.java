import java.util.*;

class Inst {
    int id;
    double cpu;
    double mem;
    double normLoad;

    Inst(int id, double cpu, double mem) {
        this.id = id;
        this.cpu = cpu;
        this.mem = mem;
        this.normLoad = (cpu + mem) / 2.0;
    }
}

public class ACO_LoadBalancer {
    static Random rand = new Random();

    static List<Inst> getMetrics() {
        List<Inst> insts = new ArrayList<>();
        insts.add(new Inst(0, 70, 60));
        insts.add(new Inst(1, 80, 55));
        insts.add(new Inst(2, 50, 30));
        insts.add(new Inst(3, 60, 40));
        return insts;
    }

    static void initACO(double[][] ph, double[][] heur, List<Inst> insts) {
        int n = insts.size();
        double initPh = 0.1;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                ph[i][j] = initPh;
                if (i != j) {
                    heur[i][j] = 1.0 / (1.0 + Math.abs(insts.get(i).normLoad - insts.get(j).normLoad));
                } else {
                    heur[i][j] = 0.0;
                }
            }
        }
    }

    static int selectNext(int cur, double[][] ph, double[][] heur) {
        int n = ph.length;
        double[] probs = new double[n];
        double totalProb = 0.0;
        double alpha = 1.0;
        double beta = 2.0;

        for (int i = 0; i < n; ++i) {
            if (i != cur) {
                probs[i] = Math.pow(ph[cur][i], alpha) * Math.pow(heur[cur][i], beta);
                totalProb += probs[i];
            }
        }

        for (int i = 0; i < n; ++i) {
            probs[i] /= totalProb;
        }

        double randNum = rand.nextDouble();
        double cumProb = 0.0;

        for (int i = 0; i < n; ++i) {
            cumProb += probs[i];
            if (randNum < cumProb) {
                return i;
            }
        }

        return n - 1;
    }

    static void updatePh(double[][] ph, List<List<Integer>> paths, double evapRate) {
        int n = ph.length;

        // Evaporation
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                ph[i][j] *= (1.0 - evapRate);

        // Pheromone Deposit
        for (List<Integer> path : paths) {
            for (int i = 0; i < path.size() - 1; ++i) {
                int from = path.get(i);
                int to = path.get(i + 1);
                ph[from][to] += 1.0 / path.size();
            }
        }
    }

    static void runACO(int maxIters, int ants, double evapRate) {
        List<Inst> insts = getMetrics();
        int n = insts.size();
        double[][] ph = new double[n][n];
        double[][] heur = new double[n][n];

        initACO(ph, heur, insts);

        for (int iter = 0; iter < maxIters; ++iter) {
            List<List<Integer>> paths = new ArrayList<>();

            for (int ant = 0; ant < ants; ++ant) {
                List<Integer> path = new ArrayList<>();
                Set<Integer> visited = new HashSet<>();

                int start = rand.nextInt(n);
                path.add(start);
                visited.add(start);

                while (path.size() < n) {
                    int cur = path.get(path.size() - 1);
                    int next = selectNext(cur, ph, heur);

                    // Ensure no revisit
                    while (visited.contains(next)) {
                        next = rand.nextInt(n);
                    }

                    path.add(next);
                    visited.add(next);
                }

                paths.add(path);
            }

            updatePh(ph, paths, evapRate);
        }

        System.out.println("ACO completed successfully.");
    }

    public static void main(String[] args) {
        int maxIters = 100;
        int ants = 10;
        double evapRate = 0.1;

        runACO(maxIters, ants, evapRate);
    }
}

