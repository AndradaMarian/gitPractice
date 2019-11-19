package Processors;

import FileOperations.FilePrinter;
import Model.Text;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileProcessor {

    TextProcessor textProcessor;
    private File file;
    private Path pathToFile;
    private FilePrinter filePrinter;


    public FileProcessor(TextProcessor textProcessor, File file,String arg) {
        this.textProcessor = textProcessor;
        this.file = file;
        pathToFile = file.toPath();
        Path pathToResultsFolder=new File(arg).toPath();
        filePrinter = new FilePrinter(pathToFile,pathToResultsFolder);
    }

    public void extractStatisticsFromText() {

        Text text = textProcessor.extractSentencesFromFile(file);
        Integer numberOfSentences = text.getTextSentences().size();
        Integer numberOfCorrectSentences = text.getNumberOfCorrectSentences();
        filePrinter.wasLinePrintSuccessful("\nTotal number of sentences," + numberOfSentences);
        filePrinter.wasLinePrintSuccessful("\nTotal number of correct sentences," + numberOfCorrectSentences+"\n");
        filePrinter.wasLinePrintSuccessful("\nWords occurrence");
        textProcessor.getWordsOccurence().forEach((x, y) -> {
            filePrinter.wasLinePrintSuccessful(x + "," + y);
        });
        filePrinter.wasLinePrintSuccessful("\nLetters occurrence");
        textProcessor.getLettersOccurence().forEach((x, y) -> {
            filePrinter.wasLinePrintSuccessful(x + "," + y);
        });

    }

    public void printFileProcessingResults() {
        wasPrintFilePropertiesSuccessful();
        extractStatisticsFromText();
        filePrinter.wasClosingFileSuccessful();
    }

    public boolean wasPrintFilePropertiesSuccessful() {
        String fileData = "";
        BasicFileAttributes basicFileAttributes = null;
        try {
            basicFileAttributes = Files.readAttributes(pathToFile, BasicFileAttributes.class);
            FileTime creationTime = basicFileAttributes.creationTime();
            FileTime modifiedTime = basicFileAttributes.lastModifiedTime();
            Date creationDate = new Date(creationTime.toMillis());
            Date modifiedDate = new Date(modifiedTime.toMillis());
            String pattern = "yyyy/MM/dd , HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            fileData += System.getProperty("os.name") + "\n" + System.getProperty("java.version") + "\n";
            fileData = fileData + pathToFile.getFileName() + "\n" + "Creation date" + "," + simpleDateFormat.format(creationDate) + "\nModified date" + "," + simpleDateFormat.format(modifiedDate);
            return filePrinter.wasLinePrintSuccessful(fileData);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

}
