package Ai_longest_path;

import java.util.*;

public class Graph {
    int V;
    ArrayList<ArrayList<Node>> adj;
    int counter[];

    ArrayList<Double> nweight;


    Graph(int V) {
        this.V = V;
        this.nweight = new ArrayList<>(Collections.nCopies(V, 0.0));
        adj = new ArrayList<>(V);
        this.counter = new int[V];
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

    }

    void printGraph(Graph g) {
        for (int i = 0; i < g.V; i++) {
          //  System.out.print("For Node " + i + " => ");
            counter[i]=adj.get(i).size();
//            if (i != g.V - 2) {
//                for (int j = 0; j < adj.get(i).size(); j++) {
//                //    System.out.print(" Vertex [" + adj.get(i).get(j).vertex + "] weight [" + adj.get(i).get(j).weight + "]");
//                }
//              // System.out.println(" ");
//            }

        }
    }


    void addEdge(int u, int v, double weight) {
        Node node = new Node(v, weight);
        adj.get(u).add(node);
        nweight.set(u, weight);
    }


    Stack topohelper(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int i = 0; i < adj.get(v).size(); i++) {
            Node node = adj.get(v).get(i);
            if (!visited[node.getVertex()])
                topohelper(node.getVertex(), visited, stack);
        }
        stack.push(v);
        return stack;
    }

    void astar(int s, Stack stack) {
        int[] ans = new int[V];
        for (int i = 0; i < V; i++) {
            ans[i] = adj.get(i).size();
        }

        Double[] dist = new Double[V];
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topohelper(i, visited, stack);
        }
        for (int i = 0; i < V; i++)
            dist[i] = -1.0;
        dist[s] = 0.0;
        while (!stack.isEmpty()) {
            int u = (int) stack.peek();
            stack.pop();
            if (dist[u] != Double.MIN_VALUE) {
                for (int i = 0; i < adj.get(u).size(); i++) {
                    Node node = adj.get(u).get(i);
                    if (dist[node.getVertex()] < dist[u] + node.getWeight())
                        dist[node.getVertex()] = dist[u] + node.getWeight();
                }
            }
        }

        Arrays.sort(dist);
        System.out.println("\n" + "This is the max time: " + dist[V - 1]);
    }
     void rankArray(int[] input)
    {
        int newArray[]
                = Arrays
                .copyOfRange(input,
                        0,
                        input.length);

        // Sort newArray[] in ascending order
        Arrays.sort(newArray);
        int i;

        // Map to store the rank of
        // the array element
        Map<Integer, Integer> ranks
                = new HashMap<>();

        int rank = 1;

        for (int index = 0;
             index < newArray.length;
             index++) {

            int element = newArray[index];

            // Update rank of element
            if (ranks.get(element) == null) {

                ranks.put(element, rank);
                rank++;
            }
        }

        // Assign ranks to elements
        for (int index = 0;
             index < input.length;
             index++) {

            int element = input[index];
            input[index]
                    = ranks.get(input[index]);

        }
    }

    void fun(Graph g, int s) {
        printGraph(g);
        rankArray(counter);
        double[] heuritic = new double[g.V];
        //Arrays.fill(heuritic,0.0);
        for (int i = 0; i < heuritic.length; i++) {
            heuritic[i]=counter[i]*-1.0;
            //  System.out.println("Herusitic of node "+i+" "+heuritic[i]);
        }
        heuritic[s]=0.0;
        heuritic[s-1]=0;
        double dist[] = new double[g.V];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[s] = 0.0;
        PriorityQueue<Node> pQueue = new PriorityQueue<>();
        pQueue.add(new Node(s, 0.0, s + "",heuritic[s]));
        boolean[] visited = new boolean[V];

        while (pQueue.size() > 0) {
            Node rem = pQueue.remove();
            visited[rem.getVertex()] = true;
//

            for (int i = 0; i < g.adj.get(rem.getVertex()).size(); i++) {
                if (!visited[g.adj.get(rem.getVertex()).get(i).getVertex()]) {
                    int verc = g.adj.get(rem.getVertex()).get(i).getVertex();
                    String tmp = rem.psf + " " + g.adj.get(rem.getVertex()).get(i).getVertex();
                    if (dist[verc] > dist[rem.getVertex()] + nweight.get(verc)) {
                        dist[verc] = dist[rem.getVertex()] + nweight.get(verc);
                        pQueue.add(new Node(verc, rem.weight + nweight.get(verc), tmp,heuritic[verc]));
                    }
                }
                if (dist[g.adj.get(rem.getVertex()).get(i).getVertex()] > dist[rem.getVertex()] + nweight.get(g.adj.get(rem.getVertex()).get(i).getVertex())) {
                    dist[g.adj.get(rem.getVertex()).get(i).getVertex()] = dist[rem.getVertex()] + nweight.get(g.adj.get(rem.getVertex()).get(i).getVertex());
                    pQueue.add(new Node(g.adj.get(rem.getVertex()).get(i).getVertex(), dist[g.adj.get(rem.getVertex()).get(i).getVertex()], "",heuritic[g.adj.get(rem.getVertex()).get(i).getVertex()]));
                }
            }
        }
        System.out.println(-1 * dist[dist.length - 2]);
    }
}


