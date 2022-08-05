package model;

import java.util.ArrayList;
import java.util.List;

public class MountainGraph {

    private int rowsNum;
    private int colsNum;
    private int treesNum;
    private CoffeeTree[] treesGraph;
    private List<Integer> pathRoots;
    private List<RobotPath> foundPaths;
    private int maxPathLength;
    private List<Integer> longestPaths;
    private RobotPath steepesPath;

    public MountainGraph(int[][] mountainMap) {
        rowsNum = mountainMap.length;
        colsNum = mountainMap[0].length;
        treesNum = rowsNum * colsNum;
        treesGraph = new CoffeeTree[treesNum];
        pathRoots = new ArrayList<>();
        foundPaths = new ArrayList<>();
        longestPaths = new ArrayList<>();
        maxPathLength = 0;
        initializeTreesGraph(mountainMap);
    }

    private void initializeTreesGraph(int[][] mountainMap) {
        int nextTreeIdx = 0;
        while (nextTreeIdx < treesNum) {
            int[] nextTreePos = getMapPosFromIdx(nextTreeIdx);
            int nextTreeElev = mountainMap[nextTreePos[0]][nextTreePos[1]];
            if (nextTreeElev != -1) {
                CoffeeTree nextTree = new CoffeeTree(nextTreeElev);
                treesGraph[nextTreeIdx] = nextTree;
                addAdjacentTrees(nextTree, nextTreePos[0], nextTreePos[1], mountainMap);
            }
            nextTreeIdx++;
        }
    }

    private void addAdjacentTrees(CoffeeTree tree, int treeRow, int treeCol, int[][] mountainMap) {
        boolean isPathRoot = true;
        int nextRow = treeRow - 1;
        if (nextRow >= 0) {
            int nortTreeElev = mountainMap[nextRow][treeCol];
            if (nortTreeElev != -1) {
                if (tree.isAdjacentTreeAbove(nortTreeElev)) {
                    tree.addAdjacentTree(getTreeIdxFromMapPos(nextRow, treeCol));
                } else if (tree.isAdjacentTreeBelow(nortTreeElev)) {
                    isPathRoot = false;
                }
            }
        }
        nextRow = treeRow + 1;
        if (nextRow < rowsNum) {
            int southTreeElev = mountainMap[nextRow][treeCol];
            if (southTreeElev != -1) {
                if (tree.isAdjacentTreeAbove(southTreeElev)) {
                    tree.addAdjacentTree(getTreeIdxFromMapPos(nextRow, treeCol));
                } else if (tree.isAdjacentTreeBelow(southTreeElev)) {
                    isPathRoot = false;
                }
            }
        }
        int nextCol = treeCol - 1;
        if (nextCol >= 0) {
            int westTreeElev = mountainMap[treeRow][nextCol];
            if (westTreeElev != -1) {
                if (tree.isAdjacentTreeAbove(westTreeElev)) {
                    tree.addAdjacentTree(getTreeIdxFromMapPos(treeRow, nextCol));
                } else if (tree.isAdjacentTreeBelow(westTreeElev)) {
                    isPathRoot = false;
                }
            }
        }
        nextCol = treeCol + 1;
        if (nextCol < colsNum) {
            int eastTreeElev = mountainMap[treeRow][nextCol];
            if (eastTreeElev != -1) {
                if (tree.isAdjacentTreeAbove(eastTreeElev)) {
                    tree.addAdjacentTree(getTreeIdxFromMapPos(treeRow, nextCol));
                } else if (tree.isAdjacentTreeBelow(eastTreeElev)) {
                    isPathRoot = false;
                }
            }
        }
        if (isPathRoot) {
            pathRoots.add(getTreeIdxFromMapPos(treeRow, treeCol));
        }
    }

    public int[] getMapPosFromIdx(int idx) {
        int row = idx / colsNum;
        int col = idx % colsNum;
        return new int[] { row, col };
    }

    public int getTreeIdxFromMapPos(int rowIdx, int colIdx) {
        return rowIdx * colsNum + colIdx;
    }

    public CoffeeTree[] getTreesGraph() {
        return treesGraph;
    }

    public void searchPaths() {
        for (Integer currentRoot : pathRoots) {
            CoffeeTree currentRootTree = treesGraph[currentRoot];
            List<Integer> pathRoot = new ArrayList<>();
            pathRoot.add(currentRootTree.getElevation());
            searchPathsInAdjacentTrees(currentRootTree.getAdjacentTrees(), pathRoot);
        }
        getSteepestPath();
    }

    private void searchPathsInAdjacentTrees(List<Integer> adjacents, List<Integer> currentTrees) {
        for (Integer adjTree : adjacents) {
            CoffeeTree currentTree = treesGraph[adjTree];
            currentTrees.add(currentTree.getElevation());
            if (currentTree.getAdjacentTreesNum() == 0) {
                RobotPath newPath = new RobotPath(new ArrayList<>(currentTrees));
                foundPaths.add(newPath);
                int currentPathLength = currentTrees.size();
                if (currentPathLength > maxPathLength) {
                    maxPathLength = currentPathLength;
                    longestPaths.clear();
                }
                if (currentPathLength == maxPathLength) {
                    longestPaths.add(foundPaths.size() - 1);
                }
            }
            if (currentTree.getAdjacentTreesNum() > 0) {
                searchPathsInAdjacentTrees(currentTree.getAdjacentTrees(), currentTrees);
            }
            currentTrees.remove(currentTrees.size() - 1);
        }
    }

    public int getNumOfPaths() {
        return foundPaths.size();
    }

    public RobotPath getSteepestPath() {
        int maxPathElev = 0;
        for (int pathIdx : longestPaths) {
            RobotPath currentPath = foundPaths.get(pathIdx);
            if (currentPath.getElevation() > maxPathElev) {
                steepesPath = currentPath;
            }
        }
        return steepesPath;
    }

    public void printResults() {
        System.out.println("Steepest path:");
        System.out.println(steepesPath);
        System.out.println("steepness: " + steepesPath.getElevation());
        System.out.println("length: " + maxPathLength);
    }
}
