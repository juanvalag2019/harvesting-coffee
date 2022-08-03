package model;

import java.util.ArrayList;
import java.util.List;

public class CoffeeTree {

    private List<Integer> adjacentHarvestableTrees;

    private int elevation;

    public CoffeeTree(int elevation) {
        super();
        this.elevation = elevation;
        adjacentHarvestableTrees = new ArrayList<>();
    }

    public void addAdjacentTree(int tree) {
        adjacentHarvestableTrees.add(tree);
    }

    public int getElevation() {
        return elevation;
    }

    public List<Integer> getAdjacentTrees() {
        return adjacentHarvestableTrees;
    }

    @Override
    public String toString() {
        return "CoffeeTree [adjacentTrees=" + adjacentHarvestableTrees + ", elevation=" + elevation + "]";
    }

}
