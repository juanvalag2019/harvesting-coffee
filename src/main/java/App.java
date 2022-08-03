import input.MountainReader;

public class App {
    public static void main(String[] args) throws Exception {
        MountainReader reader = new MountainReader("test.txt");
        int[][] map = reader.readMountainMap();
        System.out.println(map.length);
        System.out.println(map[0][2]);
    }
}