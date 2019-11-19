package FileOperations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderReader {

    public static List<File> getAllFiles(String folderPath) {


        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        List<File> textFiles = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                textFiles.add(file);
            }
        }


        return textFiles;
    }

}