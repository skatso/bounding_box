import java.util.*;

public class BoundingBox {
    public static Optional<Box> determineLargestNonOverlappingBox(char[][] grid) {
        //Iterate through the grid once, creating Box objects with proper boundaries
        var boxList = createBoxesFromGrid(grid);

        //Determine if boxes have overlap
        var validBoxHeap = removeBoxesWithOverlapAndSortByTotalArea(boxList);
        return Optional.ofNullable(validBoxHeap.peek());
    }

    private static PriorityQueue<Box> removeBoxesWithOverlapAndSortByTotalArea(List<Box> boxList) {
        var priorityQueue = new PriorityQueue<>(new BoxComparator());
        Box box1, box2;
        for(int i = 0; i < boxList.size(); i++) {
            box1 = boxList.get(i);
            for(int j = i + 1; j < boxList.size(); j++) {
                box2 = boxList.get(j);
                if(box1.overlaps(box2)) {
                    box1.hasOverlap = true;
                    box2.hasOverlap = true;
                }
            }
        }

        for(Box box: boxList) {
            if(!box.hasOverlap) {
                priorityQueue.add(box);
            }
        }
        return priorityQueue;
    }

    private static List<Box> createBoxesFromGrid(char[][] grid) {
        var boxList = new ArrayList<Box>();
        var visited = new boolean[grid.length][grid[0].length];
        Box box;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(!visited[i][j] && grid[i][j] == '*') {
                    box = new Box(i, j);
                    computeBoundaries(grid, visited, i, j, box);
                    boxList.add(box);
                }
            }
        }
        return boxList;
    }

    private static void computeBoundaries(char[][] grid, boolean[][] visited, int i, int j, Box box) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }

        if(visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        char current = grid[i][j];

        if(current == '-') {
            return;
        }

        //Update boundaries
        box.left = Math.min(j, box.left);
        box.right = Math.max(j, box.right);
        box.top = Math.min(i, box.top);
        box.bottom = Math.max(i, box.bottom);

        //Percolate
        computeBoundaries(grid, visited, i - 1, j, box);
        computeBoundaries(grid, visited, i + 1, j, box);
        computeBoundaries(grid, visited, i, j - 1, box);
        computeBoundaries(grid, visited, i, j + 1, box);
    }

    private static class BoxComparator implements Comparator<Box> {
        @Override
        public int compare(Box b1, Box b2) {
            return Integer.compare(b2.computeArea(), b1.computeArea());
        }
    }
}
