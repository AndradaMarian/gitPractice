package Processors;

import Model.Sentence;
import Model.Word;

import java.util.*;

public class SentenceProcessor {
    private static final String wordDelimiter = "(?<= |-|/|')";
    private static final String startWordPrefix = "((\\r\\n){0,4} {0,1}\\~{0,1}\\\"{0,1}){0,1}";
    private WordProcessor wordProcessor;
    private Map<String, Integer> wordsOccurrences;

    public SentenceProcessor(WordProcessor wordProcessor) {
        this.wordProcessor = wordProcessor;
        wordsOccurrences = new HashMap<>();

    }

    public void increaseOccurrence(String wordContent) {
        if (wordProcessor.isContentCorrect(wordContent)) {
            wordContent = wordContent.toLowerCase();

            if (wordsOccurrences.containsKey(wordContent)) {
                wordsOccurrences.put(wordContent, wordsOccurrences.get(wordContent) + 1);
            } else {
                wordsOccurrences.put(wordContent, 1);
            }

        }
    }

    /*If the first word starts with space, it will be considered the content of the word in order to save it.
    If it is the case it will be added as a prefix to the next word
    * */
    public List extractWordsFromSentenceText(String sentenceText) {
        List<Word> words = new ArrayList<>();
        String[] splitSentence = sentenceText.split(wordDelimiter);
        String addPrefix = null;
        for (String wordText : splitSentence) {
            Word word = wordProcessor.createWordFromString(wordText);
            if (addPrefix != null) {
                word.setWordPrefix(" " + word.getWordPrefix());
                addPrefix = null;
                increaseOccurrence(word.getWordContent());
                words.add(word);
            } else {
                if (word.getWordContent().equals(" ")) {
                    addPrefix = " ";
                } else {
                    increaseOccurrence(word.getWordContent());
                    words.add(word);
                }
            }

        }
        return words;
    }

    public Sentence extractSentenceFromString(String sentenceText) {

        List<Word> sentenceWords = extractWordsFromSentenceText(sentenceText);
        boolean isSentenceCorrect = isSentenceCorrect(sentenceWords);
        return new Sentence(sentenceWords, isSentenceCorrect);
    }

    public boolean isSentenceCorrect(List<Word> words) {
        if (words.size() == 0) {
            return false;
        }
        Stack<String> parenthesisStack = new Stack<>();
        for (int i = 0; i < words.size() - 1; i++) {
            Word word = words.get(i);
            if (i == 0 && !isCorrectFirstWord(word)) {
                return false;
            }
            if (!wordProcessor.isWordCorrect(word)) {
                return false;
            }
            if (!areWordsSeparated(words.get(i), words.get(i + 1))) {
                return false;
            }
            if (!areParanthesisCorrect(parenthesisStack, word)) {
                return false;
            }

        }

        if (!areParanthesisCorrect(parenthesisStack, words.get(words.size() - 1))) {
            return false;
        }
        if (!parenthesisStack.empty()) {

            return false;
        }
        return wordProcessor.isWordCorrect(words.get(words.size() - 1));


    }

    private boolean isCorrectFirstWord(Word word) {
        String prefix = word.getWordPrefix();
        if (!prefix.isEmpty() && !prefix.matches(startWordPrefix)) {
            return false;
        }
        String content = word.getWordContent();
        if (!content.isEmpty()) {
            if (!Character.isLetter(content.charAt(0)) || !Character.isUpperCase(content.charAt(0))) {
                return false;
            }
        }
        return true;
    }

    private boolean areParanthesisCorrect(Stack<String> parenthesisStack, Word word) {
        if (word.getWordPrefix().contains("(")) {
            parenthesisStack.push("(");
        }
        if (word.getWordPostfix().contains(")")) {
            if (parenthesisStack.empty() || !parenthesisStack.pop().equals("(")) {
                return false;
            }
        }
        if (word.getWordPrefix().contains("[")) {
            parenthesisStack.push("[");
        }
        if (word.getWordPostfix().contains("]")) {
            if (parenthesisStack.empty() || !parenthesisStack.pop().equals("[")) {
                return false;
            }
        }
        if (word.getWordPrefix().contains("{")) {
            parenthesisStack.push("{");
        }
        if (word.getWordPostfix().contains("}")) {
            if (parenthesisStack.empty() || !parenthesisStack.pop().equals("{")) {
                return false;
            }
        }
        if (word.getWordPrefix().contains("\"")) {
            parenthesisStack.push("\"");
        }
        if (word.getWordPostfix().contains("\"")) {
            if (parenthesisStack.empty() || !parenthesisStack.pop().equals("\"")) {
                return false;
            }
        }

        return true;
    }

    public boolean areWordsSeparated(Word wordOne, Word wordTwo) {
        return wordOne.getWordPostfix().endsWith(" ")
                || wordOne.getWordPostfix().endsWith("-")
                || wordOne.getWordPostfix().endsWith("/") ||
                wordTwo.getWordPrefix().startsWith(" ");
    }

    public Map<String, Integer> getWordsOccurence() {
        return wordsOccurrences;
    }

    public Map<Character, Integer> getLettersOccurence() {
        return wordProcessor.getLettersOccurence();
    }
}
