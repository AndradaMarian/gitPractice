import java.util.Objects;

public class Word {
    String wordText;

    public Word(String wordText) {

        this.wordText = wordText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(wordText.toUpperCase(), word.wordText.toUpperCase());
    }

    @Override
    public int hashCode() {

        return Objects.hash(wordText.toUpperCase());
    }

    @Override
    public String toString() {
        return
                wordText ;

    }
}
