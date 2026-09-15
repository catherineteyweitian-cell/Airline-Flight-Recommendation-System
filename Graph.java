import java.util.*;

public class Graph {

    // Represents one flight route: destination, flight time, and price
    public static class Edge {
        String destination;
        String time;
        double price;

        public Edge(String destination, String time, double price) {
            this.destination = destination;
            this.time = time;
            this.price = price;
        }
    }

    public Map<String, List<Edge>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    public Map<String, List<Edge>> getAdjList(){
            return adjList;
        }

    public void addVertex(String city) {
        adjList.putIfAbsent(city, new ArrayList<>());
    }

    public void removeVertex(String city) {
        adjList.remove(city);
        // also remove any edges pointing to this city from other cities
        for (List<Edge> edges : adjList.values()) {
            edges.removeIf(edge -> edge.destination.equals(city));
        }
    }

    public void addEdge(String source, String destination, String time, double price) {
        addVertex(source);
        addVertex(destination);
        adjList.get(source).add(new Edge(destination, time, price));
    }

    public void removeEdge(String source, String destination) {
        if (adjList.containsKey(source)) {
            adjList.get(source).removeIf(edge -> edge.destination.equals(destination));
        }
    }

    // Prints the whole network, city by city
    public void printGraph() { 
    long startTime = System.nanoTime(); 

    for (String city : adjList.keySet()) { 

        for (Edge edge : adjList.get(city)) { 
            System.out.printf(String.format(
                "%-20s --> %-20s | %-18s | RM %.0f%n",
                city, edge.destination, edge.time, edge.price
            )); 
        }

        System.out.println();   
    }

    long endTime = System.nanoTime(); 
    long btwTime = endTime - startTime; 

    System.out.println("\n\nExecution time for search airport :" + btwTime + " ns."); 
}

    // Searches for a city and prints its outgoing routes
    public void searchAirport(String city) {
        long startTime = System.nanoTime();

        if (!adjList.containsKey(city)) {
            System.out.println("\n\nAirport not found: " + city);
            long endTime = System.nanoTime();
            long btwTime = endTime - startTime ;//btwtime : between time


            System.out.println("\n\nExecution time for search airport :" + btwTime + " ns.");
                
            return;
        }

        List<Edge> edges = adjList.get(city);
        if (edges.isEmpty()) {
            System.out.println("\n\nSorry. " + city + " has no outgoing routes.");
           
            long endTime = System.nanoTime();
            long btwTime = endTime - startTime ;//btwtime : between time

            System.out.println("\n\nExecution time for search airport :" + btwTime + " ns.");
            
            return;
        }

        System.out.println("\n\n======================================================================");        
        System.out.println("Routes from " + city + "         | Flight Time        | Price");
        System.out.println("======================================================================");


        for (Edge edge : edges) {
            System.out.printf("  --> %-20s | %-18s | RM %.0f%n",edge.destination, edge.time, edge.price);
        }
        long endTime = System.nanoTime();
        long btwTime = endTime - startTime ;//btwtime : between time


        System.out.println("\n\nExecution time for search airport :" + btwTime + " ns.");

    }

    public boolean hasCity(String city) {
        return adjList.containsKey(city);
    }

    public Set<String> getCities() {
        return adjList.keySet();
    }

}
