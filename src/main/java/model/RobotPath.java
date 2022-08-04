package model;

import java.util.ArrayList;
import java.util.List;

public class RobotPath {
    private List<Integer> trees;

    public RobotPath() {
        trees = new ArrayList<>();
    }

    public RobotPath(List<Integer> currentTrees) {
        trees = currentTrees;
    }

    public void addTree(int tree) {
        trees.add(tree);
    }

    private int getRootElev() {
        return trees.get(0);
    }

    private int getFinalElev() {
        return trees.get(trees.size() - 1);
    }

    public int getElevation() {
        return getFinalElev() - getRootElev();
    }

    public void removeLast() {
        trees.remove(this.trees.size() - 1);
    }

    public void setRootElev(int rootElev) {
        trees.set(0, rootElev);
    }

    @Override
    public String toString() {
        return trees.toString().replace("[", "<").replace("]", ">").replace(", ", "-");
    }
}
