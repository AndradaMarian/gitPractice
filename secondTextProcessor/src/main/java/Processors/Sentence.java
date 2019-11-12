package Processors;

import java.util.List;

public class Sentence {
    private List<Word>wordList;
    private boolean isCorrect;

    public Sentence(List<Word> wordList, boolean isCorrect) {
        this.wordList = wordList;
        this.isCorrect = isCorrect;
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
