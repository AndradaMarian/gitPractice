package Model;

import Model.Sentence;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private List<Sentence> textSentences;

    private Integer numberOfCorrectSentences;

    public Text() {
        this.textSentences = new ArrayList<>();
        numberOfCorrectSentences = 0;
    }

    public boolean addSentence(Sentence sentence) {
        return textSentences.add(sentence);

    }


    public List<Sentence> getTextSentences() {
        return textSentences;
    }

    public Integer getNumberOfCorrectSentences() {
        return numberOfCorrectSentences;
    }

    public void setNumberOfCorrectSentences(Integer numberOfCorrectSentences) {
        this.numberOfCorrectSentences = numberOfCorrectSentences;
    }
}
