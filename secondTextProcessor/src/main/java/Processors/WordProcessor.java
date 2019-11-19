package Processors;

import Model.Word;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class WordProcessor {
    private static final String prefixes = "[(~{ \r\n\"";
    private static final String notPrefixesRegex = "[^\\[\\(\\~\\{\\ ]";
    private static final String prefixesOrPostfixesRegex = "[\\[\\(\\~\\{\\/\\-\\)\\}\\]\\:\\;\\,\\.\\?\\!\\ ]";
    private static final String notPostfixesRegex = "[^\\/\\-\\)\\}\\]\\:\\;\\,\\.\\?\\!\\ ]";
    private static final String postfixes = "/-)}]:;,.?! \"";
    private static final String correctPrefixRegex="((\r\n){0,1} {0,1}\\~{0,1}\\{{0,1}\\[{0,1}\\({0,1}\\\"{0,1}){0,1}";
    private static final String correctPostfixRegexOne="[\\:|\\;|\\?|!|\\.|\\-|\\/|\\,]( ){0,1}";
    private static final String correctPostfixRegexTwo="(\\){0,1}\\]{0,1}\\}{0,1}\\\"{0,1} {0,1}){0,1}";
    private static final String onlyLettersRegex = "[a-zA-Z]+";
    private static final String onlyDigitsRegex = "[0-9]+";
    Map<Character, Integer> lettersOccurrency;

    public WordProcessor() {
        this.lettersOccurrency = new HashMap<>();
    }


    public boolean isWordCorrect(Word word) {
        String prefix = word.getWordPrefix();
        String postfix = word.getWordPostfix();
        String content = word.getWordContent();
        if (content.length() < 1) {
            return false;
        }
        if (!isPrefixCorrect(prefix)) {
            return false;
        }
        if (!isPostfixCorrect(postfix)) {
            return false;
        }
        return isContentCorrect(content);
    }

    public boolean isContentCorrect(String content) {
        return (content.matches(onlyLettersRegex)) || (content.matches(onlyDigitsRegex)) || content.equals(" ");
    }

    public boolean isPostfixCorrect(String postfix) {

        return (postfix.matches(correctPostfixRegexOne) || postfix.matches(correctPostfixRegexTwo));
    }

    public boolean isPrefixCorrect(String prefix) {

        return prefix.matches(correctPrefixRegex);
    }

    public void increaseLetterOccurrence(char character) {
        character = Character.toLowerCase(character);
        if (Character.isLetter(character)) {
            if (lettersOccurrency.containsKey(character)) {
                lettersOccurrency.put(character, lettersOccurrency.get(character) + 1);

            } else {
                lettersOccurrency.put(character, 1);
            }
        }
    }

    public void countWordLetters(Word word) {
        String wordText = word.getWordContent();
        for (char character : wordText.toCharArray()) {
            increaseLetterOccurrence(character);
        }
    }

    public Word createWordFromString(String wordText) {
        Word word = null;
        if (wordText.isBlank() || wordText.length() == 1) {
            word = new Word("", "", wordText);

        } else {
            String prefix;
            String postfix;
            prefix = extractPrefixFromWordText(wordText);
            postfix = extractPostfixFromWordText(wordText);
            String content = extractWordContentFromWordText(wordText);
            word = new Word(prefix, postfix, content);
            countWordLetters(word);
        }
        return word;
    }

    public String extractWordContentFromWordText(String wordText) {
        String prefix;
        String postfix;
        prefix = extractPrefixFromWordText(wordText);
        postfix = extractPostfixFromWordText(wordText);
        String wordContent;
        wordText = StringUtils.replaceOnce(wordText, prefix, "");
        wordContent = wordText.replace(postfix, "");
        return wordContent;
    }

    public String extractPrefixFromWordText(String wordText) {
        String prefix = "";
        for (Character character : wordText.toCharArray()) {
            if (prefixes.contains(character.toString())) {
                prefix += (character.toString());
            } else {
                break;
            }
        }
        return prefix;

    }

    public String extractPostfixFromWordText(String wordText) {
        StringBuilder reverseText = new StringBuilder(wordText).reverse();
        StringBuilder postfix = new StringBuilder();
        for (Character character : reverseText.toString().toCharArray()) {
            if (postfixes.contains(character.toString())) {
                postfix.append(character.toString());
            } else {
                break;
            }
        }
        return postfix.reverse().toString();
    }

    public Map<Character, Integer> getLettersOccurence() {
        return lettersOccurrency;
    }
}
