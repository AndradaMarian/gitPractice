package Processors;

public class Word {
    private String wordContent;
    private Integer occurency=1;

    public Word(String wordContent) {
        this.wordContent = wordContent;
    }

    public String getWordContent() {
        return wordContent;
    }

    public void setWordContent(String wordContent) {
        this.wordContent = wordContent;
    }

    public Integer getOccurency() {
        return occurency;
    }

    public void setOccurency(Integer occurency) {
        this.occurency = occurency;
    }
    public void increaseOccurency() {
        this.occurency = this.occurency+1;
    }

}
