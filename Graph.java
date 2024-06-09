import java.util.*;

public class Graph {
    // Method to check if the graph has an Euler path
    static boolean hasEulerPath(int[][] graph) {
        int oddDegreeCount = 0;
        // Count the degree of each vertex
        for (int i = 0; i < graph.length; i++) {
            int degree = 0;
            for (int j = 0; j < graph[i].length; j++) {
                degree += graph[i][j];
            }
            // Check if the degree of the vertex is odd
            if (degree % 2 != 0)
                oddDegreeCount++;
        }
        // If there are 0 or 2 vertices with odd degree, an Euler path is possible
        return oddDegreeCount == 0 || oddDegreeCount == 2;
    }

    // Method to check if the graph has an Euler circuit
    static boolean hasEulerCircuit(int[][] graph) {
        // Check if each vertex has an even degree
        for (int i = 0; i < graph.length; i++) {
            int degree = 0;
            for (int j = 0; j < graph[i].length; j++) {
                degree += graph[i][j];
            }
            // If any vertex has an odd degree, return false
            if (degree % 2 != 0)
                return false;
        }
        // If all vertices have even degree, an Euler circuit is possible
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a graph (set of edges):");
        String input = scanner.nextLine();
        
        // Parsing input to get edges
        String[] edges = input.split("\\s+");
        int[][] graph = new int[edges.length / 2][edges.length / 2];
        for (int i = 0; i < edges.length; i += 2) {
            // Parse the vertices of each edge and mark them in the adjacency matrix
            int u = Integer.parseInt(edges[i].trim());
            int v = Integer.parseInt(edges[i + 1].trim());
            graph[u - 1][v - 1] = 1;
            graph[v - 1][u - 1] = 1;
        }

        System.out.print("The graph G = ");
        System.out.print("{");
        boolean firstEdge = true;
        for (int i = 0; i < graph.length; i++) {
            for (int j = i + 1; j < graph[i].length; j++) {
                // Print the edges of the graph
                if (graph[i][j] == 1) {
                    if (!firstEdge)
                        System.out.print(", ");
                    System.out.print("{" + (i + 1) + ", " + (j + 1) + "}");
                    firstEdge = false;
                }
            }
        }
        System.out.println("}");

        // Check if the graph has an Euler path and/or an Euler circuit
        if (hasEulerPath(graph))
            System.out.println("There is an Euler path");
        else
            System.out.println("There is no Euler path");

        if (hasEulerCircuit(graph))
            System.out.println("There is an Euler circuit");
        else
            System.out.println("There is no Euler circuit");
    }
}
