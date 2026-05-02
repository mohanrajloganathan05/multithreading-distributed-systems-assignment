import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SingleThread {

    public static int countLines(String filename) throws IOException {
        int count = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        while (reader.readLine() != null) {
            count++;
        }
        
        reader.close();
        return count;
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        String[] files = {
            "file_1.txt",
            "file_2.txt",
            "file_3.txt",
            "file_4.txt",
            "file_5.txt"
        };

        try {
            for (String file : files) {
                int lines = countLines(file);
                System.out.println(file + ": " + lines + " lines");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        double timeInSeconds = (endTime - startTime) / 1000.0;

        System.out.println("\nTotal Time (Single Thread): " 
                            + timeInSeconds + " seconds");
    }
}