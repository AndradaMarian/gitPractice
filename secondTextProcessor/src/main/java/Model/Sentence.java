package Model;

import java.util.List;

public class Sentence {
    private List<Word> wordList;
    private boolean isCorrect;

    public Sentence(List<Word> wordList, boolean isCorrect) {
        this.wordList = wordList;
        this.isCorrect = isCorrect;
    }

    public String getLastPostfix() {
        if (wordList.size() >= 1) {
            if (wordList.get(wordList.size() - 1) != null) {
                return wordList.get(wordList.size() - 1).getWordPostfix();
            }
        }
        return "";

    }

    public String getFirstPrefix() {
        if (wordList.size() >= 1) {
            if (wordList.get(0) != null) {
                return wordList.get(0).getWordPrefix();
            }
        }
        return "";
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
