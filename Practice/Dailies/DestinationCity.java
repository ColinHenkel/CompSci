import java.util.*;

public class DestinationCity {
    public static void main(String[] args) {
        // main method to test destCity
        List<List<String>> paths = new ArrayList<>();
        List<String> path1 = new ArrayList<>();
        path1.add("London");
        path1.add("New York");
        List<String> path2 = new ArrayList<>();
        path2.add("New York");
        path2.add("Lima");
        List<String> path3 = new ArrayList<>();
        path3.add("Lima");
        path3.add("Sao Paulo");
        paths.add(path1);
        paths.add(path2);
        paths.add(path3);
        System.out.println(destCity(paths));
    }

    public static String destCity(List<List<String>> paths) {
        // You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi
        // Return the destination city, that is, the city without any path outgoing to another city
        // It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city
        // paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
        // output = "Sao Paulo"
        // paths = [["B","C"],["D","B"],["C","A"]]
        // output = "A"
        // paths = [["A","Z"]]
        // output = "Z"
        // 2 <= paths.length <= 100
        // paths[i].length == 2
        // 1 <= cityAi.length, cityBi.length <= 10
        // cityAi != cityBi
        // All strings consist of lowercase and uppercase English letters and the space character
        // paths forms a consistent graph
        // There exists a city that is not connected to any other city

        // create a set of all the cities
        Set<String> cities = new HashSet<>();
        for(List<String> path : paths) {
            cities.add(path.get(0));
            cities.add(path.get(1));
        }
        // remove all the cities that are the first element of a path
        for(List<String> path : paths) {
            cities.remove(path.get(0));
        }
        // return the only city left in the set
        return cities.iterator().next();
    }
}
