package game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class TopScore extends Score {

    private static final String FILE_PATH = "src/main/TopScore/TopScore.txt";
    private static int oldTop;
    private static int newTop;

    public static void getGameScore(){
        newTop = Score.getCurrentScore();
    }

    public static void updateTopScore(){
        getGameScore();
        getScoreFromFile();

        if (newTop > oldTop){
            FileHandler.writeToFile(FILE_PATH, String.valueOf(newTop));
        }
    }

    public static int getScoreFromFile() {

        FileHandler.readFile(FILE_PATH);
        oldTop = Integer.parseInt(FileHandler.getFirstLine());

        return oldTop;
    }

    private static class FileHandler {

        private static String firstLine;

        private static void readFile(String filePath) {
            File file = new File(filePath);

            try {
                // Check if file exists
                if (!file.exists()) {

                    // If not, create it
                    if (file.createNewFile()) {
                        System.out.println("File created");

                        // Writes "0" to new file
                        FileWriter writer = new FileWriter(file);
                        writer.write("0");
                        writer.close();

                    } else {
                        System.out.println("Failed to create the file.");
                        return;
                    }

                } else {
                    System.out.println("File exists");
                }

                // Read the file
                List<String> lines = Files.readAllLines(file.toPath());
                firstLine = lines.getFirst();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        private static void writeToFile(String filePath, String textToWrite) {
            try (FileWriter writer = new FileWriter(filePath, false)) {
                writer.write(textToWrite);
                writer.close();
                System.out.println("Successfully wrote to file");
            } catch (IOException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        }

        public static String getFirstLine(){
            return firstLine;
        }

    }



}
