package Model;

public class Word {
    private String wordPrefix;
    private String wordPostfix;
    private String wordContent;


    public Word(String wordContent) {
        this.wordContent = wordContent;
    }


    public Word(String wordPrefix, String wordPostfix, String wordContent) {
        this.wordPrefix = wordPrefix;
        this.wordPostfix = wordPostfix;
        this.wordContent = wordContent;
    }


    public String getWordContent() {
        return wordContent;
    }

    public void setWordContent(String wordContent) {
        this.wordContent = wordContent;
    }

    public String getWordPrefix() {
        return wordPrefix;
    }

    public void setWordPrefix(String wordPrefix) {
        this.wordPrefix = wordPrefix;
    }

    public String getWordPostfix() {
        return wordPostfix;
    }

    public void setWordPostfix(String wordPostfix) {
        this.wordPostfix = wordPostfix;
    }


}
