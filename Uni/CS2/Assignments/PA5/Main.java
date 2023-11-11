import java.util.*;

        /* COP 3503C Assignment 5
This program is written by: Colin J Henkel */

public class Main {
    // main driver method
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // take in initial conditions
        int cityC = in.nextInt();
        int roadR = in.nextInt();
        int capital = in.nextInt();
        int treasureDist;

        // create and fill list of roads then create directed graph representing monster land
        int tempS, tempD, tempL;
        List<Road> roads = new ArrayList<>();
        for(int i = 0; i < roadR; i++) {
            tempS = in.nextInt();
            tempD = in.nextInt();
            tempL = in.nextInt();
            roads.add(new Road(tempS, tempD, tempL));
        }
        treasureDist = in.nextInt();
        in.close();
        Graph cityGraph = new Graph(roads, cityC);

        //run dijkstra's to find min distance
        int[] distances = dijkstra(cityGraph, capital);
        int cityTreasures = 0;
        int roadTreasures = 0;

        // find treasures
        for(int i = 0; i < cityC; i++) {
            if(distances[i] == treasureDist)
                cityTreasures++;
        }

        for(Road road : roads) {
            int distanceSource = distances[road.source - 1];
            int distanceDest = distances[road.dest - 1];
        
            if(distanceSource < treasureDist && distanceDest < treasureDist) {
                int srcTreasureDist = distanceSource + road.length;
                int destTreasureDist = distanceDest + road.length;
                int totalDist = distanceDest + distanceSource + road.length;
                if(srcTreasureDist >= treasureDist)
                    roadTreasures++;
                if(destTreasureDist >= treasureDist && totalDist > treasureDist * 2)
                    roadTreasures++;
            }
            if(road.source == capital && road.length > treasureDist)
                    roadTreasures++;
        }
        
        // print # of treasures in cities and on roads
        System.out.println("In cities: " + cityTreasures);
        System.out.println("On the road: " + roadTreasures);
    }

    // dijkstra method to find min distance from capital to all cities
    public static int[] dijkstra(Graph graph, int source) {
        int n = graph.adjList.size();
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);

        PriorityQueue<City> minHeap = new PriorityQueue<>(Comparator.comparingInt(city -> city.distance));
        minHeap.add(new City(source, 0));
        distances[source - 1] = 0;

        while(!minHeap.isEmpty()) {
            City currentCity = minHeap.poll();
            int cityNo = currentCity.cityNo;
            int distance = currentCity.distance;

            if(distance > distances[cityNo - 1])
                continue;
            
            for(Road road : graph.adjList.get(cityNo - 1)) {
                int nextCity = road.dest;
                int newDistance = distance + road.length;
                if(newDistance < distances[nextCity - 1]) {
                    distances[nextCity - 1] = newDistance;
                    minHeap.add(new City(nextCity, newDistance));
                }
            }
        }

        return distances;
    }
}

// class used to store Road data
class Road {
    int source, dest, length;

    public Road(int source, int dest, int length) {
        this.source = source;
        this.dest = dest;
        this.length = length;
    }
}

// class used to store City data
class City {
    int cityNo, distance;

    public City(int cityNo, int distance) {
        this.cityNo = cityNo;
        this.distance = distance;
    }
}

// class used to represent graph of roads and cities
class Graph {
    List<List<Road>> adjList = null;

    Graph(List<Road> roads, int n) {
        adjList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for(Road road : roads) {
            adjList.get(road.source - 1).add(road);
            adjList.get(road.dest - 1).add(road);
        }
    }
}
