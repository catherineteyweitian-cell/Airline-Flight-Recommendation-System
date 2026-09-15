import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // 1. Create the Graph object
        Graph graph = new Graph();
        Algorithm Alg = new Algorithm(graph);

        // 2. Pre-load it with the MAS flight network
        initMASNetwork(graph);

        boolean running1 = true;

        while (running1) {
            System.out.println("\n\n\n\n\n=======================================================================");
            System.out.println("                    Welcome to the Airline Network!                    ");
            System.out.println("=======================================================================");
            System.out.println("1. Create Graph");
            System.out.println("2. View MAS Flight Network");
            System.out.println("3. Search for an Airport");
            System.out.println("0. Exit");
            System.out.println("Please select:");
            int ans1 = input.nextInt();
            input.nextLine(); // consume leftover newline after nextInt()

            switch (ans1) {
                case 1: {
                    boolean running2 = true;
                    while (running2) {
                        System.out.println("\n\n\n\n\n=======================================================================");
                        System.out.println("                              Create Graph                    ");
                        System.out.println("=======================================================================");
                        System.out.println("1. Add a vertex");
                        System.out.println("2. Remove a vertex");
                        System.out.println("3. Add an edge");
                        System.out.println("4. Remove an edge");
                        System.out.println("5. View the Graph");
                        System.out.println("0. Back");
                        System.out.println("Please select:");
                        int ans2 = input.nextInt();
                        input.nextLine();

                        switch (ans2) {
                            case 1: {
                                boolean keepAddVertex = true;

                                while(keepAddVertex){
                                    System.out.println("\nPlease enter the name of city (E.g. Kuala Lumpur, Johor Bahru): ");
                                    String cityName = input.nextLine();
                                    graph.addVertex(cityName);

                                    System.out.println("\n======================================================================");
                                    System.out.println(cityName + " added.");
                                    System.out.println("======================================================================\n");

                                    System.out.println("Need to Continue adding vertex? (Y/N)");
                                    String ans3 = input.nextLine();

                                    if (ans3.equalsIgnoreCase("Y")) {
                                        keepAddVertex = true;
                                    } else if (ans3.equalsIgnoreCase("N")) {
                                        keepAddVertex = false; 
                                    } else {
                                        System.out.println("ERROR: Invalid input! Return to sub-menu.\n");
                                        keepAddVertex = false;
                                    }
                                }
                                break;
                            }
                            case 2: {
                                boolean keepRemoveVertex = true;

                                while(keepRemoveVertex){
                                    System.out.println("\nPlease enter the name of city to remove: ");
                                    String cityName = input.nextLine();

                                    graph.removeVertex(cityName);

                                    System.out.println("\n======================================================================");
                                    System.out.println(cityName + " removed.");
                                    System.out.println("======================================================================\n");

                                    System.out.println("Need to Continue? (Y/N)");
                                    String ans3 = input.nextLine();

                                    if (ans3.equalsIgnoreCase("Y") || ans3.equalsIgnoreCase("y")){
                                        keepRemoveVertex = true;
                                    }else if (ans3.equalsIgnoreCase("N") || ans3.equalsIgnoreCase("n")){
                                        keepRemoveVertex = false;
                                    }else{
                                        System.out.println("ERROR: Invalid input ! \nPlease enter the valid charater.\nReturn to menu.");
                                        keepRemoveVertex = false;
                                    }

                                }
                                break;

                            }
                            case 3: {
                                boolean keepAddingEdge = true;

                                while (keepAddingEdge) {    
                                    System.out.println("\nEnter source city: ");
                                    String src = input.nextLine();//src = source

                                    System.out.println("\nEnter destination city: ");
                                    String dest = input.nextLine();

                                    System.out.println("\nEnter flight time (e.g. 1 Hour 05 Minutes): ");
                                    String time = input.nextLine();

                                    System.out.println("\nEnter price (e.g. 180): ");
                                    double price = Double.parseDouble(input.nextLine());

                                    graph.addEdge(src, dest, time, price);

                                    System.out.println("======================================================================");
                                    System.out.println("\nEdge added: " + src + " --> " + dest);
                                    System.out.println("======================================================================");

                                    
                                    System.out.println("Need to Continue? (Y/N)");
                                    String ans3 = input.nextLine();

                                    if (ans3.equalsIgnoreCase("Y") || ans3.equalsIgnoreCase("y")){
                                        keepAddingEdge = true;
                                    }else if (ans3.equalsIgnoreCase("N") || ans3.equalsIgnoreCase("n")){
                                        keepAddingEdge = false;
                                    }else{
                                        System.out.println("ERROR: Invalid input ! \nPlease enter the valid charater.\nReturn to menu.");
                                        keepAddingEdge = false;
                                    }

                                }
                                break;
                            }   
                            case 4: {
                                boolean keepRemovingEdge = true;

                                while (keepRemovingEdge) {
                                    System.out.println("Enter source city: ");
                                    String src = input.nextLine();

                                    System.out.println("Enter destination city: ");
                                    String dest = input.nextLine();

                                    graph.removeEdge(src, dest);
                                    
                                    System.out.println("======================================================================");
                                    System.out.println("\nEdge removed: " + src + " --> " + dest);
                                    System.out.println("======================================================================");

                                    System.out.println("Need to Continue? (Y/N)");
                                    String ans3 = input.nextLine();

                                    if (ans3.equalsIgnoreCase("Y") || ans3.equalsIgnoreCase("y")){
                                        keepRemovingEdge = true;
                                    }else if (ans3.equalsIgnoreCase("N") || ans3.equalsIgnoreCase("n")){
                                        keepRemovingEdge = false;
                                    }else{
                                        System.out.println("ERROR: Invalid input ! \nPlease enter the valid charater.\nReturn to menu.");
                                        keepRemovingEdge = false;
                                    }
                                }
                                break;
                            }
                            case 5:
                                System.out.println("==============================================================================");
                                System.out.println("                                View the Graph                            ");
                                System.out.println("==============================================================================");
                                System.out.println("\n\nRoute (Starting Point --> Destination)        | Flight Time         | Price");
                                System.out.println("==============================================================================");
                                graph.printGraph();
                                timeComplexity(Alg,graph);
                                break;

                            case 0:
                                running2 = false;
                                break;
                            default:
                                System.out.println("ERROR : Invalid input! \nPlease enter a valid number.");
                        }
                    }
                    break;
                }

                case 2: {
                    boolean running3 = true;
                    while (running3) {
                        System.out.println("==============================================================================");
                        System.out.println("                         MAS Flight Network                            ");
                        System.out.println("==============================================================================");
                        System.out.println("\n\nRoute (Starting Point --> Destination)        | Flight Time         | Price");
                        System.out.println("==============================================================================");
                        graph.printGraph();
                        System.out.println("==============================================================================\n\n\n");
                        timeComplexity(Alg,graph);

                        System.out.println("\n\n0. Back");
                        System.out.println("Please select:");

                        int ans2 = input.nextInt();
                        input.nextLine();

                        switch (ans2) {
                            case 0:
                                running3 = false;
                                break;
                            default:
                                System.out.println("ERROR : Invalid input! \nPlease enter a valid number.");
                                running3 = false;
                                break;
                        }
                    }
                    break;
                }

                case 3: {
                    boolean running4 = true;
                    while (running4) {
                        System.out.println("=======================================================================");
                        System.out.println("                         Search for an Airport                    ");
                        System.out.println("=======================================================================");
                        System.out.println("1. Search");
                        System.out.println("2. BFS Traversal");
                        System.out.println("0. Back");
                        System.out.println("\nPlease select:");
                        
                        int ans2 = input.nextInt();
                        input.nextLine();

                        switch (ans2) {
                            case 1: {
                                boolean continueNormal = true;

                                while(continueNormal){
                                System.out.println("\n\nEnter city name to search: ");
                                String cityName = input.nextLine();
                                graph.searchAirport(cityName);
                                timeComplexity(Alg,graph);
                                
                                System.out.println("Need to Continue?(Y/N)");
                                String ans4 = input.nextLine();

                                    if (ans4.equalsIgnoreCase("Y") || ans4.equalsIgnoreCase("y")) {
                                        continueNormal = true;
                                    } else if (ans4.equalsIgnoreCase("N") || ans4.equalsIgnoreCase("n")) {
                                        continueNormal = false; 
                                    } else {
                                        System.out.println("ERROR: Invalid input! Return to sub-menu.\n");
                                        continueNormal = false;
                                    }
                                }

                                break;
                            }
                            case 2: {
                                boolean continueBFS = true;
                                while (continueBFS){

                                    System.out.println("\nEnter starting city: ");
                                    String start = input.nextLine();
                                    
                                    System.out.println("\nEnter destination city: ");
                                    String end = input.nextLine();
                                    Alg.bfsWithPath(start, end);
                                    timeComplexity(Alg,graph);

                                System.out.println("\nNeed to Continue?(Y/N)");
                                String ans4 = input.nextLine();

                                    if (ans4.equalsIgnoreCase("Y") || ans4.equalsIgnoreCase("y")) {
                                        continueBFS = true;
                                    } else if (ans4.equalsIgnoreCase("N") || ans4.equalsIgnoreCase("n")) {
                                        continueBFS = false; 
                                    } else {
                                        System.out.println("ERROR: Invalid input! Return to sub-menu.\n");
                                        continueBFS = false;
                                    }
                                }

                                break;
                            }
                            case 0:
                                running4 = false;
                                break;
                        }
                    }
                    break;
                }

                case 0:
                    running1 = false;
                    break;
                default:
                    System.out.println("ERROR: Invalid input ! \nPlease enter a valid number.");
            }
        }

        input.close();
    }

    public static void timeComplexity(Algorithm Alg, Graph graph) {
        System.out.println("\n======================================================================");
        System.out.println("                            Time Complexity                    ");
        System.out.println("======================================================================");

        // pull real flight prices from the graph instead of a made-up array
        List<Integer> priceList = new ArrayList<>();
        for (String city : graph.getAdjList().keySet()) {
            for (Graph.Edge edge : graph.getAdjList().get(city)) {
                priceList.add((int) edge.price);
            }
        }
        int[] sampleNumbers = priceList.stream().mapToInt(Integer::intValue).toArray();

        System.out.println("Sample size (n): " + sampleNumbers.length);

        // Best Case - O(1)
        long start1 = System.nanoTime();
        int firstResult = Alg.getFirstElement(sampleNumbers);
        long end1 = System.nanoTime();

        System.out.println("\nBest Case - O(1) - getFirstElement():");
        System.out.println("Result: " + firstResult);
        System.out.println("Execution time: " + (end1 - start1) + " ns");

        // Worst Case - O(n), target not found at all
        long start2 = System.nanoTime();
        boolean worstResult = Alg.containsValue(sampleNumbers, -1); // -1 never exists as a price
        long end2 = System.nanoTime();

        System.out.println("\nWorst Case - O(n) - containsValue() - target not found:");
        System.out.println("Result: " + worstResult);
        System.out.println("Execution time: " + (end2 - start2) + " ns");

        // Average Case - O(n), target roughly in the middle
        int midValue = sampleNumbers[sampleNumbers.length / 2];
        long start3 = System.nanoTime();

        boolean avgResult = Alg.containsValue(sampleNumbers, midValue);
        long end3 = System.nanoTime();

        System.out.println("\nAverage Case - O(n) - containsValue() - target in the middle:");
        System.out.println("Result: " + avgResult);
        System.out.println("Execution time: " + (end3 - start3) + " ns");

    }

    // default airline network map
    public static void initMASNetwork(Graph graph) {

        // Kuala Lumpur
        graph.addEdge("Kuala Lumpur", "Penang", "1 Hour 00 Minutes", 120);
        graph.addEdge("Penang", "Kuala Lumpur", "1 Hour 00 Minutes", 120);

        graph.addEdge("Kuala Lumpur", "Johor Bahru", "0 Hour 55 Minutes", 100);
        graph.addEdge("Johor Bahru", "Kuala Lumpur", "0 Hour 55 Minutes", 100);

        graph.addEdge("Kuala Lumpur", "Kuching", "1 Hour 45 Minutes", 210);
        graph.addEdge("Kuching", "Kuala Lumpur", "1 Hour 45 Minutes", 210);

        graph.addEdge("Kuala Lumpur", "Kota Kinabalu", "2 Hours 45 Minutes", 270);
        graph.addEdge("Kota Kinabalu", "Kuala Lumpur", "2 Hours 45 Minutes", 270);

        graph.addEdge("Kuala Lumpur", "Singapore", "1 Hour 05 Minutes", 110);
        graph.addEdge("Singapore", "Kuala Lumpur", "1 Hour 05 Minutes", 110);

        // Penang
        graph.addEdge("Penang", "Johor Bahru", "1 Hour 15 Minutes", 160);
        graph.addEdge("Johor Bahru", "Penang", "1 Hour 15 Minutes", 160);

        graph.addEdge("Penang", "Singapore", "1 Hour 25 Minutes", 180);
        graph.addEdge("Singapore", "Penang", "1 Hour 25 Minutes", 180);

        graph.addEdge("Penang", "Kuching", "1 Hour 40 Minutes", 230);
        graph.addEdge("Kuching", "Penang", "1 Hour 40 Minutes", 230);

        // Johor Bahru
        // Direct flight is fastest, but more expensive than going through Kuala Lumpur.
        graph.addEdge("Johor Bahru", "Singapore", "0 Hour 50 Minutes", 260);
        graph.addEdge("Singapore", "Johor Bahru", "0 Hour 50 Minutes", 260);

        graph.addEdge("Johor Bahru", "Kuching", "1 Hour 25 Minutes", 190);
        graph.addEdge("Kuching", "Johor Bahru", "1 Hour 25 Minutes", 190);

        // Kuching
        graph.addEdge("Kuching", "Kota Kinabalu", "1 Hour 20 Minutes", 200);
        graph.addEdge("Kota Kinabalu", "Kuching", "1 Hour 20 Minutes", 200);

        graph.addEdge("Kuching", "Singapore", "1 Hour 30 Minutes", 250);
        graph.addEdge("Singapore", "Kuching", "1 Hour 30 Minutes", 250);

        // Kota Kinabalu
        graph.addEdge("Kota Kinabalu", "Singapore", "2 Hours 25 Minutes", 290);
        graph.addEdge("Singapore", "Kota Kinabalu", "2 Hours 25 Minutes", 290);
    }

}