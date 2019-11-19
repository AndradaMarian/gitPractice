import Model.Sentence;
import Model.Text;
import Processors.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TextProcessorTest {
    WordProcessor wordProcessor;
    SentenceProcessor sentenceProcessor;
    TextProcessor textProcessor;

    @Before
    public void init() {
        wordProcessor = new WordProcessor();
        sentenceProcessor = new SentenceProcessor(wordProcessor);
        textProcessor = new TextProcessor(sentenceProcessor);


    }

    @Test
    public void testAreSentencesSeparated() {
        String sentence1Text = "ana are mere.";
        Sentence sentence1 = sentenceProcessor.extractSentenceFromString(sentence1Text);

        String sentence2Text = " ana are mere.";
        Sentence sentence2 = sentenceProcessor.extractSentenceFromString(sentence2Text);
        assertTrue(textProcessor.areSentencesSeparated(sentence1, sentence2));
        assertFalse(textProcessor.areSentencesSeparated(sentence2, sentence1));
        String sentence3Text = "ana are mere. ";
        Sentence sentence3 = sentenceProcessor.extractSentenceFromString(sentence3Text);

        assertTrue(textProcessor.areSentencesSeparated(sentence3, sentence1));
    }

    @Test
    public void extractSentencesFromFile() {
        Text text= textProcessor.extractSentencesFromFile(new File("C:\\Users\\andrada.marian\\Documents\\gitPractice\\gitPractice\\secondTextProcessor\\src\\test\\java\\text.txt"));
        assertTrue(text.getNumberOfCorrectSentences()==2);
        assertTrue(textProcessor.countCorrectSentences(text)==2);
    }

}
