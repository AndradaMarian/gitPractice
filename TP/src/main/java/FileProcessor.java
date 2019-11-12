import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FileProcessor {
    String fileData;

    private File file;


    public FileProcessor(File x) {

        file = x;

    }

    void printFileProperties(HashMap<Word, Integer> wordOccurences, HashMap<Character, Integer> letterOccurences,String errors) {

//        try {
//            BasicFileAttributes basicFileAttributes = Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class);
//            FileTime creationTime = basicFileAttributes.creationTime();
//            FileTime modifiedTime = basicFileAttributes.lastModifiedTime();
//            Date creationDate = new Date(creationTime.toMillis());
//            Date modifiedDate = new Date(modifiedTime.toMillis());
//            String pattern = "yyyy/MM/dd , HH:mm:ss";
//
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//            fileData = file.getName() + "," + simpleDateFormat.format(creationDate) + "," + simpleDateFormat.format(modifiedDate);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        PrintWriter writer = null;
//        try {
//            String antet = "FileName,Creation Date,Creation Time,Last Modified Date,Last Modified Time";
//            writer = new PrintWriter(file.getPath() + "Result.csv", "UTF-8");
//            writer.println(antet);
//            writer.println(fileData);
//
//            writer.println("Words ocurrences");
//            final String[] words = new String[1];
//            final String[] wordsOccurences = new String[1];
//            wordOccurences.forEach((x, y) -> {
//                if (words[0] != null) {
//                    wordsOccurences[0] = wordsOccurences[0] + y + ",";
//                    words[0] = words[0] + x.wordText + ",";
//                } else {
//                    wordsOccurences[0] = y + ",";
//                    words[0] = x.wordText + ",";
//                }
//
//            });
//
//            final String[] letters = new String[1];
//            final String[] lettersOccurences = new String[1];
//            letterOccurences.forEach((x, y) -> {
//                if (letters[0] != null) {
//                    lettersOccurences[0] = lettersOccurences[0] + y + ",";
//                    letters[0] = letters[0] + x + ",";
//                } else {
//                    lettersOccurences[0] = y + ",";
//                    letters[0] = x + ",";
//                }
//
//            });
//
//            writer.println(words[0]);
//            writer.println(wordsOccurences[0]);
//            writer.println("Letters ocurrences");
//            writer.println(letters[0]);
//            writer.println(lettersOccurences[0]);
//            writer.println(errors);
//            writer.close();
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }


    }
}
