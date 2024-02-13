import java.util.*;

// Class to represent an edge in the graph
class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    // Constructor
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    // Compare edges based on their weights
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

// Class to represent a disjoint set (Union-Find)
class DisjointSet {
    int[] parent;
    int[] rank;

    // Constructor
    public DisjointSet(int vertices) {
        parent = new int[vertices];
        rank = new int[vertices];
        // Initialize each vertex as a separate set with rank 0
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find the set to which a vertex belongs
    public int find(int vertex) {
        if (parent[vertex] != vertex) {
            // Path compression: Make the parent of the vertex the root of its set
            parent[vertex] = find(parent[vertex]);
        }
        return parent[vertex];
    }

    // Merge two sets based on their ranks
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return; // The elements are already in the same set
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

// Class to represent a graph and perform Kruskal's algorithm
class Graph {
    int vertices;
    List<Edge> edges;

    // Constructor
    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList<>();
    }

    // Add an edge to the graph
    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    // Find the minimum spanning tree using Kruskal's algorithm
    public List<Edge> kruskalMST() {
        List<Edge> mst = new ArrayList<>();
        // Sort edges in increasing order of weight using a minimum heap
        PriorityQueue<Edge> pq = new PriorityQueue<>(edges);

        // Create a disjoint set to keep track of sets of vertices
        DisjointSet ds = new DisjointSet(vertices);

        // Iterate through all edges
        while (!pq.isEmpty()) {
            Edge edge = pq.poll(); // Get the edge with the smallest weight

            // Check if adding this edge forms a cycle
            int sourceRoot = ds.find(edge.source);
            int destinationRoot = ds.find(edge.destination);
            if (sourceRoot != destinationRoot) {
                // If not, add the edge to the minimum spanning tree
                mst.add(edge);
                // Union the sets of the source and destination vertices
                ds.union(sourceRoot, destinationRoot);
            }
        }

        return mst;
    }
}

public class Question_3_b {
    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);

        // Add edges to the graph (source, destination, weight)
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);

        // Find the minimum spanning tree using Kruskal's algorithm
        List<Edge> mst = graph.kruskalMST();

        // Print the edges of the minimum spanning tree
        System.out.println("Edges of the Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}
