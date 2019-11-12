import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public List<File> getAllFiles(String folderPath){


        File folder = new File("C:\\Users\\andrada.marian\\Documents\\TP\\FilesToRead");
        File[] listOfFiles = folder.listFiles();
        List<File>textFiles=new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
              textFiles.add(file);
            }
        }


        return textFiles;
    }

}
