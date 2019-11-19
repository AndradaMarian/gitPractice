package FileOperations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class FilePrinter {
    private static final String pathToResultsFolder = "C:\\Users\\andrada.marian\\Documents\\gitPractice\\gitPractice\\secondTextProcessor\\Results\\";
    PrintWriter writer = null;
    Path pathToFile;

    public FilePrinter(Path pathToPrintFile,Path pathToResultsFolder) {
        try {
            String resultFileName = pathToPrintFile.getFileName().toString().replace(".txt", "Statistics.csv");
            pathToFile = pathToPrintFile;
            writer = new PrintWriter(pathToResultsFolder+"\\" + resultFileName, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean wasLinePrintSuccessful(String line) {
        writer.print(line + "\n");
        return true;
    }

    public boolean wasClosingFileSuccessful() {
        writer.close();

        return true;
    }


}
