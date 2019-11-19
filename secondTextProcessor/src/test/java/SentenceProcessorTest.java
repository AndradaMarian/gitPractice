import Processors.SentenceProcessor;
import Model.Word;
import Processors.WordProcessor;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SentenceProcessorTest {
    private SentenceProcessor sentenceProcessor;
    private WordProcessor wordProcessor;
    private String sentence1;
    private String sentence2;


    @Before
    public void init() {
        wordProcessor = new WordProcessor();
        sentenceProcessor = new SentenceProcessor(wordProcessor);
        sentence2 = "(Tehnicile) si {oferirea de informatii} si [documentatii] propriilor) (angajati.";
        sentence1 = "Tehnicile si oferi-rea de informa/tii si documentatii propriilor angajati.";

    }

    @Test
    public void testextractSentenceFromString() {
        assertTrue((sentenceProcessor.extractSentenceFromString(sentence1)) != null);
        assertTrue((sentenceProcessor.extractSentenceFromString(sentence2)) != null);

    }
    @Test
    public void testisSentenceCorrect(){
        String sentence="ana are mere";
        List<Word>words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));
        sentence="(ana are )mere";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));
        sentence="(ana nu are) mere)";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));
        sentence="Ana (nu are-mere)";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertTrue(sentenceProcessor.isSentenceCorrect(words));
        sentence="Ana (nu are/mere)";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertTrue(sentenceProcessor.isSentenceCorrect(words));
        sentence="(Ana {nu} \"are\" [mere])";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));
        sentence="(ana {nu} \"are\" [mere] multe)";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));
        sentence="(ana nu are) mere) multe";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));
        sentence="(ana nu are} mere) multe";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));
        sentence="(ana nu are] mere) multe";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));
        sentence="(ana nu are\" mere) multe";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));
        sentence=" ana,nu are mere multe";
        words=sentenceProcessor.extractWordsFromSentenceText(sentence);
        assertFalse(sentenceProcessor.isSentenceCorrect(words));

    }

    @Test
    public void testextractWordsFromSentenceText() {
        assertTrue(sentenceProcessor.extractWordsFromSentenceText(sentence1).size() == 11);

    }

}
