import java.util.Arrays;

import input.MountainReader;
import model.MountainGraph;

public class App {
    public static void main(String[] args) {
        MountainReader reader = new MountainReader("test.txt");
        int[][] map = reader.readMountainMap();
        MountainGraph mountainGrap = new MountainGraph(map);
        mountainGrap.searchPaths();
        System.out.println(mountainGrap.getNumOfPaths());
    }
}