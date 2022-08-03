package model;

public class MountainGraph {

    private int rowsNum;
    private int colsNum;
    private int treesNum;
    private CoffeeTree[] treesGraph;

    public MountainGraph(int[][] mountainMap) {
        rowsNum = mountainMap.length;
        colsNum = mountainMap[0].length;
        treesNum = rowsNum * colsNum;
        treesGraph = new CoffeeTree[treesNum];
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
        if (treeRow - 1 >= 0) {
            int nortTreeElev = mountainMap[treeRow - 1][treeCol];
            if (isAdjacentTreeValid(nortTreeElev, tree)) {
                tree.addAdjacentTree(getTreeIdxFromMapPos(treeRow - 1, treeCol));
            }
        }
        if (treeRow + 1 < rowsNum) {
            int southTreeElev = mountainMap[treeRow + 1][treeCol];
            if (isAdjacentTreeValid(southTreeElev, tree)) {
                tree.addAdjacentTree(getTreeIdxFromMapPos(treeRow + 1, treeCol));
            }
        }
        if (treeCol - 1 >= 0) {
            int westTreeElev = mountainMap[treeRow][treeCol - 1];
            if (isAdjacentTreeValid(westTreeElev, tree)) {
                tree.addAdjacentTree(getTreeIdxFromMapPos(treeRow, treeCol - 1));
            }
        }
        if (treeCol + 1 < colsNum) {
            int eastTreeElev = mountainMap[treeRow][treeCol + 1];
            if (isAdjacentTreeValid(eastTreeElev, tree)) {
                tree.addAdjacentTree(getTreeIdxFromMapPos(treeRow, treeCol + 1));
            }
        }
    }

    public boolean isAdjacentTreeValid(int nextTreeElev, CoffeeTree currentTree) {
        return nextTreeElev != -1 && nextTreeElev > currentTree.getElevation();
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
}
