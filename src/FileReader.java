import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static char[][] convertFileToGrid(InputStream inputStream) throws IOException {
        if(inputStream == null) {
            throw new IOException("The file could not be found.");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        if (lines.isEmpty()) {
            throw new IOException("File is empty or not formatted correctly.");
        }

        int numRows = lines.size();
        int numCols = lines.get(0).length();

        char[][] chars = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            chars[i] = lines.get(i).toCharArray();
        }

        return chars;
    }
}
