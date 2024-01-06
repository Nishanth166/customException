import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}

public class FileReadWriteCustomException {

    private static final Logger LOGGER = Logger.getLogger(FileReadWriteCustomException.class.getName());

    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        try {
            readFileAndWriteToFile(inputFilePath, outputFilePath);
        } catch (CustomException e) {
            LOGGER.log(Level.SEVERE, "Custom File Operation Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void readFileAndWriteToFile(String inputFilePath, String outputFilePath) throws CustomException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found: " + e.getMessage());
            throw new CustomException("Error reading file: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO Exception: " + e.getMessage());
            throw new CustomException("Error writing to file: " + e.getMessage());
        }
    }
}

