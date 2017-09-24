import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by emol on 9/24/17.
 */
public class VirtualFriends_260683698 {
    public static void main(String[] args) {
//        test();

        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();  // test case cnt
        for (int i = 0; i < tc; i++) {
            Graph g = new Graph();
            int lineCnt = sc.nextInt();
            sc.nextLine();
            while (lineCnt > 0){
                String input = sc.nextLine();
                String[] names = input.split(" ");
                g.addEdge(names[0], names[1]);
                System.out.println(g.findCommonSetRep(names[0], names[1]));
                lineCnt --;
            }

        }
    }




    //-----------------testing-------------------------
    private static void test(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            // get test case info
            int size = sc.nextInt();
            int subsetCnts = sc.nextInt();
            int[] subsetSizes = new int[subsetCnts];
            for (int i = 0; i < subsetCnts; i++){
                subsetSizes[i] = sc.nextInt();
            }

            System.out.println("Total: " + size);
            for (int i = 0; i< subsetCnts; i++){
                System.out.println("Subset " + i + ": " + subsetSizes[i]);
            }

            // construct test case
            Graph g = new Graph();
            int startUserIndex = 1;
            for (int i : subsetSizes){
                int endUserIndex = startUserIndex + i - 1;
                // User1 - User2 - User3 ... connect the whole subset
                for (int  j = startUserIndex; j < endUserIndex; j++){
                    g.addEdge("User" + j, "User" + (j + 1));
                    if (g.findCommonSetRep("User" + j, "User" + (j + 1)) != j + 2 - startUserIndex){
                        if (j + 2 - startUserIndex ==  -48){
                            System.out.print("sd");
                        }
                        System.err.println("Error in phase 1. User" + j + "  User" + (j + 1) + "  Should be: " + (j + 2 - startUserIndex));
                        System.err.println("Output is: " + g.findCommonSetRep("User" + j, "User" + (j + 1)));
                        exit(1);
                    }
                }

                // add random edge in the subset, subset cnt should remain the same
                int randomEdgeCnt = (int) Math.random() * 100;
                for (int k = 0; k< randomEdgeCnt; k++){
                    String u1 = "User" + randomWithRange(startUserIndex, endUserIndex);
                    String u2 = "User" + randomWithRange(startUserIndex, endUserIndex);
                    g.addEdge(u1, u2);
                    if (g.findCommonSetRep(u1, u2) != i){
                        System.err.println("Error in phase 2." + u1 + "    " + u2 + "Should be: " + i);
                        System.err.println("Output is: " + g.findCommonSetRep(u1, u2));
                        exit(1);
                    }
                }

                startUserIndex = endUserIndex + 1;
            }
            System.out.println("Test 1 & 2 passed!");
        }
    }
    // helper function used in testing functions
    private static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}



class Node{
//    String parent;  // in this question it's the name of a person
    String name;
    int rank;   // for performance optimization
    int cnt;    // # of nodes in this subset (which the node represents)

    public Node(String name){
        this.name = name;
        this.rank = 1;
        this.cnt = 1;

    }
}


class Graph{
    HashMap<String, Node> nodes;
    HashMap<Node, Node> edges;  // key: src, value: dest

    public Graph(){
        this.nodes = new HashMap<String, Node>();
        this.edges = new HashMap<Node, Node>();
    }
    //-----------public interface------------------------

    // for user to add a new edge in the graph
    public void addEdge(String n1, String n2){
        Node i = nodes.containsKey(n1) ? nodes.get(n1) : newNode(n1);
        Node j = nodes.containsKey(n2) ? nodes.get(n2) : newNode(n2);
        union(i, j);
    }

    // FIXME: maybe we only need one node as input? as the second node is connected with the first one for sure
    public int findCommonSetRep(String n1, String n2){
        Node i = nodes.get(n1);
//        Node j = nodes.get(n2);
        return find(i).cnt;
    }




    // ----------------private methods--------------------
    private Node newNode(String name){
        Node n = new Node(name);
        this.nodes.put(name, n);
        this.edges.put(n, n);   // new node points to itself (parent of the new node is itself)
        return n;
    }

    // find the rep of the subset node i belongs to
    private Node find(Node i){
        while (i != edges.get(i)) i = edges.get(i);
        return i;
    }

    private void union(Node i, Node j){
        if (i == j) return;
        Node pi = find(i);
        Node pj = find(j);
        if (pi == pj) return;
        // attach smaller rank tree under root of high rank tree, so we will not end up with link list like structure
        if (pi.rank < pj.rank){
            // pj is the parent, pi (src) points to pj
            edges.put(pi, pj);
            // update subset cnt (in parent node)
            pj.cnt += pi.cnt;
        }
        else {
            edges.put(pj, pi);
            pi.cnt += pj.cnt;

            if (pi.rank == pj.rank) pi.rank ++;
        }
    }

}