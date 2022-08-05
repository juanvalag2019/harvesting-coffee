package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MountainReader {

    private String fileName;
    private static final String SEPARATOR = " ";

    public MountainReader(String fileName) {
        this.fileName = "/" + fileName;
    }

    public int[][] readMountainMap() {
        int[][] mountainMap = null;
        InputStream inputFile = MountainReader.class.getResourceAsStream(this.fileName);
        try (BufferedReader buffRead = new BufferedReader(new InputStreamReader(inputFile))) {
            String dimsLine = buffRead.readLine();
            if (dimsLine != null) {
                String[] mapDimsTxt = dimsLine.split(SEPARATOR);
                if (mapDimsTxt.length == 2) {
                    int[] mapDims = { Integer.parseInt(mapDimsTxt[0]), Integer.parseInt(mapDimsTxt[1]) };
                    mountainMap = new int[mapDims[0]][mapDims[1]];
                    String rowLine;
                    int currentLine = 0;
                    while ((rowLine = buffRead.readLine()) != null) {
                        String[] treeRow = rowLine.split(SEPARATOR);
                        mountainMap[currentLine] = Arrays.stream(treeRow)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        currentLine++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            e.printStackTrace();
        }
        return mountainMap;
    }
}
