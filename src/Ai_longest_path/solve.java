package Ai_longest_path;

import java.util.*;
import java.io.*;

class solve {

    public static void main(String[] args) throws FileNotFoundException {
//        PrintStream stream = new PrintStream(new FileOutputStream("console.txt", true));
//        System.setOut(stream);
        String[] arr = {"Test.txt", "Test2", "test_large.dag", "test_large_sparse.dag", "test_medium.dag", "test_medium_sparse.dag", "test_small.dag", "test_small_sparse.dag", "test_xlarge.dag", "test_xlarge_sparse.dag"};
        for (int i = 0; i < arr.length; i++) {
            test_with_dijkstra(arr[i], -1.0);
            test_with_correct(arr[i],1.0 );
        }
        //test_with_dijkstra(arr[0], -1.0);
    }

    public static void test_with_correct(String filename, Double dc) {
        int nodes;
        Scanner sc = null;
        try {
            File file = new File("C:\\Users\\golur\\IdeaProjects\\Ai_longest_path\\src\\Ai_longest_path\\data\\" + filename);
            sc = new Scanner(file);

        } catch (Exception x) {
            System.err.print(x);
        }


//int[][] arr;
        ArrayList<Double> db = new ArrayList<>();
        assert sc != null;
        nodes = Integer.parseInt(sc.nextLine());
        nodes++;
        boolean[] is_child = new boolean[nodes];
        int org_node = nodes + 1;
        Graph graph = new Graph(org_node);
        for (int i = 0; i < nodes; i++) {
            if (sc.hasNextLine() && i != nodes - 1) {
                double temp = Double.parseDouble(sc.nextLine()) * dc;
                db.add(temp);

            } else {
                db.add(0.0);
            }

        }

        for (int i = 0; i < nodes; i++) {

            if (sc.hasNextLine()) {
                // System.out.println(sc.nextLine().split("\\s+"));
                String[] sad = sc.nextLine().split(" ");
                for (String s : sad) {
                    if (!Objects.equals(sad[0], "-1")) {
                        if (!is_child[Integer.parseInt(s)]) {
                            is_child[Integer.parseInt(s)] = true;
                        }
                        graph.addEdge(i, Integer.parseInt(s), db.get(i));

                    } else {
                        graph.addEdge(i, db.size() - 1, db.get(i));
                    }

                }


            }

        }

        for (int i = 0; i < is_child.length - 1; i++) {
            if (!is_child[i]) {
                graph.addEdge(org_node - 1, i, 0.0);
            }
        }
        System.out.println("For the file " + filename+" the code wih topo sort");
        System.out.print("Following are longest distances from  auxilary source vertex which is : " + (org_node - 1) + " and auxilary sink node : " + (org_node - 2) + " \n");
        long startTime = System.nanoTime();
        Stack<Integer> st = new Stack<>();
        graph.astar(org_node - 1,st);
        long stopTime = System.nanoTime();
        double elapsedTimeInSecond = (double) (stopTime - startTime) / 1_000_000_000;
        System.out.println(elapsedTimeInSecond + " seconds");
        System.out.println("=============================================================================================================================================");
    }
    public static void test_with_dijkstra(String filename, Double dc) {
        int nodes;
        Scanner sc = null;
        try {
            File file = new File("C:\\Users\\golur\\IdeaProjects\\Ai_longest_path\\src\\Ai_longest_path\\data\\" + filename);
            sc = new Scanner(file);

        } catch (Exception x) {
            System.err.print(x);
        }
        ArrayList<Double> db = new ArrayList<>();
        assert sc != null;
        nodes = Integer.parseInt(sc.nextLine());
        nodes++;
        boolean[] is_child = new boolean[nodes];
        int org_node = nodes + 1;
        Graph graph = new Graph(org_node);
        for (int i = 0; i < nodes; i++) {
            if (sc.hasNextLine() && i != nodes - 1) {
                double temp = Double.parseDouble(sc.nextLine()) * dc;
                db.add(temp);

            } else {
                db.add(0.0);
            }

        }

        for (int i = 0; i < nodes; i++) {

            if (sc.hasNextLine()) {
                String[] sad = sc.nextLine().split(" ");
                for (String s : sad) {
                    if (!Objects.equals(sad[0], "-1")) {
                        if (!is_child[Integer.parseInt(s)]) {
                            is_child[Integer.parseInt(s)] = true;
                        }
                        graph.addEdge(i, Integer.parseInt(s), db.get(i));

                    } else {
                        graph.addEdge(i, db.size() - 1, db.get(i));
                    }

                }


            }

        }

        for (int i = 0; i < is_child.length - 1; i++) {
            if (!is_child[i]) {
                graph.addEdge(org_node - 1, i, 0.0);
            }
        }
        System.out.println("For the file " + filename+" with dijstra");
        System.out.print("Following are longest distances from  auxilary source vertex which is : " + (org_node - 1) + " and auxilary sink node : " + (org_node - 2) + " \n");
        long startTime = System.nanoTime();
        Stack<Integer> st = new Stack<>();
         //graph.printGraph(graph);
         graph.fun(graph, org_node - 1);
        long stopTime = System.nanoTime();
        double elapsedTimeInSecond = (double) (stopTime - startTime) / 1_000_000_000;
        System.out.println(elapsedTimeInSecond + " seconds");
        System.out.println("=============================================================================================================================================");
    }
}