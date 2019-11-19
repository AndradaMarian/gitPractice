package Processors;

import Model.Sentence;
import Model.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TextProcessor {
    private static final String sentenceDelimiter = "(?<=\\.|\\?|!)";
    SentenceProcessor sentenceProcessor;


    public TextProcessor(SentenceProcessor sentenceProcessor) {

        this.sentenceProcessor = sentenceProcessor;
    }

    public Text extractSentencesFromFile(File file) {
        Text text = new Text();
        try {

            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(sentenceDelimiter);
            while (scanner.hasNext()) {
                String currentSentenceText = scanner.next();
                Sentence currentSentence = sentenceProcessor.extractSentenceFromString(currentSentenceText);
                text.addSentence(currentSentence);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        text.setNumberOfCorrectSentences(countCorrectSentences(text));
        return text;
    }

    public Integer countCorrectSentences(Text text) {
        Integer numberOfCorrectSentences = 0;
        List<Sentence> sentences = text.getTextSentences();
        for (int i = 0; i < sentences.size() - 1; i++) {
            if (sentences.get(i).isCorrect()) {
                if (areSentencesSeparated(sentences.get(i), sentences.get(i + 1))) {
                    numberOfCorrectSentences++;
                }
            }
        }
        if (sentences.size() >= 1) {
            if (sentences.get(sentences.size() - 1).isCorrect()) {
                numberOfCorrectSentences++;
            }
        }
        return numberOfCorrectSentences;
    }

    public boolean areSentencesSeparated(Sentence sentenceOne, Sentence sentenceTwo) {
        if (sentenceOne.getLastPostfix().endsWith(" ") || sentenceOne.getLastPostfix().endsWith("\n")) {
            return true;
        }
        return sentenceTwo.getFirstPrefix().startsWith(" ") || sentenceTwo.getFirstPrefix().startsWith("\r\n") || sentenceTwo.getFirstPrefix().startsWith("\n");
    }


    public Map<String, Integer> getWordsOccurence() {

        return sentenceProcessor.getWordsOccurence();
    }

    public Map<Character, Integer> getLettersOccurence() {
        return sentenceProcessor.getLettersOccurence();
    }
}
