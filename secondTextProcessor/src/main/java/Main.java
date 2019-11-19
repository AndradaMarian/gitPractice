import FileOperations.FolderReader;
import Processors.*;

public class Main {
    public static void main(String[] args) {
        String directoryName=args[0];
        FolderReader.getAllFiles(directoryName).forEach(file -> {

            WordProcessor wordProcessor = new WordProcessor();

            SentenceProcessor sentenceProcessor = new SentenceProcessor(wordProcessor);

            TextProcessor textProcessor = new TextProcessor(sentenceProcessor);
            FileProcessor fileProcessor = new FileProcessor(textProcessor, file,args[1]);
            fileProcessor.printFileProcessingResults();
        });


    }
}
