package Dijkstra_Algorithm;
import java.util.ArrayList;
class Pair{
    public int val;
    public int wt;

    Pair(int p1, int p2){
        this.val = p1;
        this.wt = p2;
    }

    int getVal(){
        return this.val;
    }
    int getWt(){
        return this.wt;
    }
}

class Heap {
    Pair h[] = new Pair[100];
    int sizeh = 0;
    int m[] = new int[100];

    void swaph(int i, int j) {
        Pair temp = h[i];
        h[i] = h[j];
        h[j] = temp;
        m[h[i].val] = i;
        m[h[j].val] = j;
    }

    void insert(Pair p) {
        h[sizeh] = p;
        m[p.val] = sizeh;
        sizeh++;

        int i = sizeh - 1;
        while (i > 0 && h[i].getWt() < h[(i - 1) / 2].getWt()) {
            swaph(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    void decreaseKey(int val, int newWeight) {
        int i = m[val];
        h[i].wt = newWeight;

        while (i > 0 && h[i].getWt() < h[(i - 1) / 2].getWt()) {
            swaph(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    void displayHeap() {
        for (int i = 0; i < sizeh; i++) {
            System.out.print("(" + h[i].getVal() + ", " + h[i].getWt() + ") ");
        }
        System.out.println();
    }

    void displayM() {
        for (int i = 0; i < sizeh; i++) {
            System.out.println("Node " + h[i].getVal() + " is at heap index " + m[h[i].getVal()]);
        }
        System.out.println();
    }

    Pair extractMin() {
        if (sizeh == 0) {
            return null; // Heap is empty
        }

        Pair u = h[0]; // Extract min
        h[0] = h[sizeh - 1]; // Move last element to root
        m[u.val] = -1; // Mark node as removed
        m[h[0].val] = 0; // Update root index
        sizeh--;

        heapify(0);
        return u;
    }

    void heapify(int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int min = i;

        if (l < sizeh && h[l].getWt() < h[min].getWt()) {
            min = l;
        }
        if (r < sizeh && h[r].getWt() < h[min].getWt()) {
            min = r;
        }
        if (min != i) {
            swaph(i, min);
            heapify(min);
        }
    }

    boolean isEmpty(){
        return sizeh ==0;
    }
}

public class Dijk {
    public static void main(String[] args) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i=0; i<5; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Pair(1,30));
        adj.get(0).add(new Pair(4,5));
        adj.get(0).add(new Pair(3,20));

        adj.get(1).add(new Pair(0,30));
        adj.get(1).add(new Pair(2,4));

        adj.get(2).add(new Pair(1,4));
        adj.get(2).add(new Pair(3,7));
        adj.get(2).add(new Pair(4,8));

        adj.get(3).add(new Pair(0,20));
        adj.get(3).add(new Pair(2,7));
        adj.get(3).add(new Pair(4,6));

        adj.get(4).add(new Pair(0,5));
        adj.get(4).add(new Pair(2,8));
        adj.get(4).add(new Pair(3,6));


        Heap heap = new Heap();

        heap.insert(new Pair(0,Integer.MAX_VALUE));
        heap.insert(new Pair(1,Integer.MAX_VALUE));
        heap.insert(new Pair(2,Integer.MAX_VALUE));
        heap.insert(new Pair(3,Integer.MAX_VALUE));
        heap.insert(new Pair(4,Integer.MAX_VALUE));

        int parent[] = new int[5];
        int distance[] = new int[5];
        int src = 0;
        heap.decreaseKey(0, 0);
        parent[src] = -1;


        while(!heap.isEmpty()){
            Pair u = heap.extractMin();
            int node = u.getVal();
            int wt = u.getWt();
            distance[node] = wt;


            for(int i=0; i<adj.get(node).size(); i++){
                Pair nu= adj.get(node).get(i);
                int newNode = nu.getVal();
                int newWt = nu.getWt();
                if(heap.m[newNode] != -1 && wt+newWt < heap.h[heap.m[newNode]].wt){
                    heap.decreaseKey(newNode, newWt+wt);
                    parent[newNode] = node;
                }
            }

        }

        printParent(parent);
        printDistance(distance);
    }

    private static void printDistance(int[] distance) {
        System.out.println("Distance: ");
        System.out.println();
        for(int i=0; i<distance.length; i++){
            System.out.println(i + " " + distance[i]);
        }
        System.out.println();
    }

    private static void printParent(int[] parent) {
        System.out.println("Parent: ");
        System.out.println();
        for(int i=0; i<parent.length; i++) {
            System.out.println(i + " " + parent[i]);
        }
        System.out.println();
    }
}
