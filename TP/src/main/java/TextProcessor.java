import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextProcessor {

    HashMap<Word, Integer> wordOccurences = new HashMap<>();
    HashMap<Character, Integer> letterOccurences = new HashMap<>();
    List<File> files;
    Integer correctSenteces = 0;
    Integer wrongSenteces = 0;
    String errors="";
    List<String>sentences;

    public TextProcessor(List<File> files) {
        this.files = files;
        sentences=new ArrayList<>();
    }

    public void processAll() {
        files.forEach(x -> {
            processOneFile(x);
            FileProcessor fileProcessor = new FileProcessor(x);
            fileProcessor.printFileProperties(wordOccurences, letterOccurences,errors);
            wordOccurences = new HashMap<>();
            letterOccurences = new HashMap<>();
            errors="";


        });
    }
    
    public void processOneFile(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            String remainder = "";
            String processingLine = "";
            int lineNumber=0;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                processingLine = remainder + " " + line;
                String currentSentence;

                while (!(currentSentence = getSentence(processingLine)).equals(processingLine)) {
                    if (verifyCorrectness(currentSentence,lineNumber)) {
                        correctSenteces++;
                    } else {

                        wrongSenteces++;
                    }
                    processingLine = processingLine.substring(currentSentence.length());
                    countWords(currentSentence);
                    countLetters(currentSentence);

                }
                remainder = processingLine;
            }
            if (!processingLine.isEmpty()) {
                if (verifyCorrectness(processingLine,lineNumber)) {
                    correctSenteces++;
                } else {
                    wrongSenteces++;
                }

                countWords(processingLine);
                countLetters(processingLine);
            }
            System.out.println(correctSenteces);
        } catch (FileNotFoundException e) {
            System.out.println("The file " + file + " does not exist.");
        } catch (IOException e) {
            System.out.println("There are problems with the file " + file);
        }

    }

    private void countLetters(String currentSentence) {
        for (char x : currentSentence.toUpperCase().toCharArray()) {
            if (Character.isLetter(x)) {
                if (letterOccurences.containsKey(x)) {
                    letterOccurences.put(x, letterOccurences.get(x) + 1);
                } else {
                    letterOccurences.put(x, 1);
                }
            }
        }
    }

    private void countWords(String currentSentence) {
        String[] strings = currentSentence.split(" |,|\\.|  ");

        for (String string : strings) {
            if (!string.isEmpty() && string.chars().allMatch(Character::isLetter)) {
                Word word = new Word(string);
                if (wordOccurences.containsKey(word)) {
                    wordOccurences.put(word, wordOccurences.get(word) + 1);
                } else {
                    wordOccurences.put(word, 1);
                }
            }
        }

    }

    private boolean verifyCorrectness(String currentSentence, int lineNumber) {
        if (currentSentence.charAt(0)!=' ') {
            errors+="You need a space after dot at line "+lineNumber+"\n";
            return false;
        }
        if (Character.isLowerCase(currentSentence.charAt(1))) {
            errors+="Each sentence starts with capital letter (line  "+lineNumber+")\n";
            return false;
        }
        if (currentSentence.charAt(currentSentence.length() - 1) != '.') {
            errors+="Each sentence ends with dot (line "+lineNumber+")\n";
            return false;
        }
        for(int i=0;i<currentSentence.length()-1; i++) {
            if(Character.isDigit(currentSentence.charAt(i))){
                errors+="Numbers are not allowed (line "+lineNumber+")\n";
                return false;
            }
            if((currentSentence.charAt(i)==',')&&(currentSentence.charAt(i+1)!=' ')){
                errors+="You need a space after each comma (line "+lineNumber+")\n";
                return false;
            }

        }
        return true;
    }

    public String getSentence(String line) {
        String[] strings;

        if (line.contains(".")) {
            strings = line.split("\\.");
            if (!strings[0].isEmpty()) {
                return line.substring(0, strings[0].length() + 1);
            }
        }
        return line;


    }
}
