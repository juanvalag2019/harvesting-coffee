
import input.MountainReader;
import model.MountainGraph;

public class App {
    public static void main(String[] args) {
        MountainReader reader = new MountainReader("map.txt");
        int[][] map = reader.readMountainMap();
        MountainGraph mountainGraph = new MountainGraph(map);
        mountainGraph.searchPaths();
        mountainGraph.printResults();
    }
}