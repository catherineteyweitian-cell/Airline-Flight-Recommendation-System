import java.util.*;

public class Algorithm {

    Graph graph;
    Map<String, List<Graph.Edge>> adjList;
    
    public Algorithm(Graph graph){
        this.graph = graph;
        this.adjList = graph.getAdjList();
    }

    //o(1)- constant time - Best case
    public int getFirstElement(int[] numbers){
        return numbers[0];
    }
    
    //o(n) - linear time - worst case
public boolean containsValue(int[] numbers, int target){
    for (int num : numbers){
        if (num == target){
            return true;
        }
    }
    return false;
}

    // Breadth First Search - explores level by level using a queue

    public List<String> bfs(String start) {
        List<String> visitOrder = new ArrayList<>();
        if (!adjList.containsKey(start)) {
            System.out.println("Airport not found: " + start);
            return visitOrder;
        }
 
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
 
        queue.add(start);
        visited.add(start);
 
        while (!queue.isEmpty()) {
            String current = queue.poll();
            visitOrder.add(current);
 
            for (Graph.Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.destination)) {
                    visited.add(edge.destination);
                    queue.add(edge.destination);
                }
            }
        }
        return visitOrder;
    }

    public void bfsWithPath(String start, String end) {
        if (!adjList.containsKey(start) || !adjList.containsKey(end)) {
            System.out.println("Airport not found.");
            return;
        }

        // Uses a BFS-style search to find every possible route between start and end,
        // then prints the fastest, cheapest, and balanced options together
        compareRoutes(start, end);
    }

    // ===== Route comparison: fastest / cheapest / balanced =====

    // Helper class to hold one complete route and its totals
    private static class RouteResult {
        List<String> path = new ArrayList<>();
        List<Graph.Edge> edges = new ArrayList<>();
        int totalMinutes = 0;
        double totalPrice = 0;

        RouteResult copyAndExtend(Graph.Edge edge, int minutesAdded) {
            RouteResult copy = new RouteResult();
            copy.path.addAll(this.path);
            copy.path.add(edge.destination);
            copy.edges.addAll(this.edges);
            copy.edges.add(edge);
            copy.totalMinutes = this.totalMinutes + minutesAdded;
            copy.totalPrice = this.totalPrice + edge.price;
            return copy;
        }
    }

    // Converts "2 Hours 25 Minutes" style strings into total minutes
    private int parseTimeToMinutes(String time) {
        int hours = 0, minutes = 0;
        String[] parts = time.trim().split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equalsIgnoreCase("Hour") || parts[i].equalsIgnoreCase("Hours")) {
                hours = Integer.parseInt(parts[i - 1]);
            }
            if (parts[i].equalsIgnoreCase("Minute") || parts[i].equalsIgnoreCase("Minutes")) {
                minutes = Integer.parseInt(parts[i - 1]);
            }
        }
        return (hours * 60) + minutes;
    }

    // Finds every simple path (no repeated city) from start to end using a BFS-style queue,
    // but instead of stopping at the first match, it keeps expanding until all paths are found
    private List<RouteResult> findAllRoutes(String start, String end) {
        List<RouteResult> allRoutes = new ArrayList<>();
        if (!adjList.containsKey(start) || !adjList.containsKey(end)) {
            return allRoutes;
        }

        Queue<RouteResult> queue = new LinkedList<>();
        RouteResult initial = new RouteResult();
        initial.path.add(start);
        queue.add(initial);

        while (!queue.isEmpty()) {
            RouteResult current = queue.poll();
            String lastCity = current.path.get(current.path.size() - 1);

            if (lastCity.equals(end) && current.path.size() > 1) {
                allRoutes.add(current);
                continue; // route complete, don't expand further from here
            }

            for (Graph.Edge edge : adjList.get(lastCity)) {
                if (!current.path.contains(edge.destination)) { // avoid revisiting a city (no cycles)
                    int minutesAdded = parseTimeToMinutes(edge.time);
                    queue.add(current.copyAndExtend(edge, minutesAdded));
                }
            }
        }
        return allRoutes;
    }

    private void printRoute(RouteResult r, String label) {
        System.out.println("\n=== " + label + " ===");
        System.out.println("======================================================================");
        for (int i = 0; i < r.edges.size(); i++) {
            Graph.Edge e = r.edges.get(i);
            System.out.printf("%-20s --> %-20s | %-18s | RM %.0f%n",
                    r.path.get(i), r.path.get(i + 1), e.time, e.price);
        }
        System.out.println("======================================================================");
        System.out.printf("Total Time: %d minutes | Total Price: RM %.0f%n", r.totalMinutes, r.totalPrice);
    }

    // Compares all routes and prints the fastest, cheapest, and most balanced option
    public void compareRoutes(String start, String end) {
        List<RouteResult> allRoutes = findAllRoutes(start, end);

        if (allRoutes.isEmpty()) {
            System.out.println("No route found from " + start + " to " + end);
            return;
        }

        RouteResult fastest = allRoutes.get(0);
        RouteResult cheapest = allRoutes.get(0);
        RouteResult mostExpensive = allRoutes.get(0);

        for (RouteResult r : allRoutes) {
            if (r.totalMinutes < fastest.totalMinutes) 
                fastest = r;
            if (r.totalPrice < cheapest.totalPrice) 
                cheapest = r;
            if (r.totalPrice > mostExpensive.totalPrice)
                mostExpensive = r;
        }

        // Balanced route: normalize time and price (0 to 1 scale) and pick the lowest combined score
        int maxMinutes = 0;
        double maxPrice = 0.0;

        for (RouteResult r : allRoutes) {
            maxMinutes = Math.max(maxMinutes, r.totalMinutes);
            maxPrice = Math.max(maxPrice, r.totalPrice);
        }

        RouteResult balanced = allRoutes.get(0);
        double bestScore = Double.MAX_VALUE;
        for (RouteResult r : allRoutes) {
            double normTime = (maxMinutes == 0) ? 0 : (double) r.totalMinutes / maxMinutes;
            double normPrice = (maxPrice == 0) ? 0 : r.totalPrice / maxPrice;
            double score = normTime + normPrice; // equal weight to both factors
            if (score < bestScore) {    //lowest combined score is selected as the balanced route
                bestScore = score;
                balanced = r;
            }
        }

        printRoute(fastest, "Fastest Route (Least Flight Time)");
        printRoute(cheapest, "Cheapest Route (Least Price)");
        printRoute(mostExpensive,"Most Expensive Route (Most Price)");
        printRoute(balanced, "Balanced Route (Time & Price)");
    }
}