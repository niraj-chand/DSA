import javax.swing.*;
import java.util.HashMap;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 * Q5_aAns - A simple Java Swing application to visualize a network topology.
 * 
 * This program creates a graphical representation of a network using the JGraphX library.
 * It stores nodes (devices) and edges (connections) using HashMaps.
 * The network consists of a server and clients, with connections labeled by latency in milliseconds.
 */
public class Q5_aAns extends JFrame {
    
    private mxGraph graph; // Graph object to represent network topology
    private Object parent; // Root node of the graph
    private HashMap<String, Object> nodes;  // Stores nodes (servers/clients)
    private HashMap<String, Integer> edges; // Stores edges (connections with latency)

    /**
     * Constructor to initialize the network topology GUI.
     */
    public Q5_aAns() {
        super("Network Topology with HashMap"); // Set window title

        // Initialize the graph
        graph = new mxGraph();
        parent = graph.getDefaultParent();
        nodes = new HashMap<>();
        edges = new HashMap<>();
        
        // Begin updating the graph
        graph.getModel().beginUpdate();
        try {
            // Add network nodes
            addNode("Server", 100, 100);
            addNode("Client1", 300, 50);
            addNode("Client2", 300, 150);

            // Add network connections with latency
            addEdge("Server", "Client1", 5);
            addEdge("Server", "Client2", 10);
        } finally {
            graph.getModel().endUpdate(); // End update operation
        }

        // Create a graph component and add it to the JFrame
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);

        // Set JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close window on exit
        setSize(500, 300); // Set window size
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible
    }

    /**
     * Adds a node (device) to the graph and stores it in the HashMap.
     * 
     * @param name The name of the node.
     * @param x    The x-coordinate for positioning the node.
     * @param y    The y-coordinate for positioning the node.
     */
    private void addNode(String name, int x, int y) {
        Object node = graph.insertVertex(parent, null, name, x, y, 80, 30);
        nodes.put(name, node); // Store node reference in the HashMap
    }

    /**
     * Adds an edge (connection) between two nodes with a specified latency.
     * 
     * @param from    The starting node.
     * @param to      The destination node.
     * @param latency The latency value in milliseconds.
     */
    private void addEdge(String from, String to, int latency) {
        // Ensure both nodes exist before creating the connection
        if (nodes.containsKey(from) && nodes.containsKey(to)) {
            graph.insertEdge(parent, null, latency + " ms", nodes.get(from), nodes.get(to));
            edges.put(from + "-" + to, latency); // Store edge in the HashMap
        }
    }

    /**
     * Main method to launch the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Q5_aAns::new); // Launch GUI in the event dispatch thread
    }
}
