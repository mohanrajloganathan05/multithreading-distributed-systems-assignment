import java.io.FileWriter;
import java.io.IOException;

public class CreateBigFiles {

    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            String filename = "file_" + i + ".txt";

            try (FileWriter writer = new FileWriter(filename)) {

                for (int j = 0; j < 1_000_000; j++) { // 10 lakh lines
                    writer.write("This is line " + j + " in " + filename + "\n");
                }

                System.out.println(filename + " created");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}