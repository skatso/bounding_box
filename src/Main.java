import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var inputGrid = FileReader.convertFileToGrid(System.in);
        var biggestBoxO = BoundingBox.determineLargestNonOverlappingBox(inputGrid);

        if(biggestBoxO.isEmpty()) {
            System.out.println("No non-overlapping boxes found in the grid.");
            System.exit(1);
        }

        Box biggestBox = biggestBoxO.get();
        System.out.println(biggestBox);
        System.exit(0);
    }
}