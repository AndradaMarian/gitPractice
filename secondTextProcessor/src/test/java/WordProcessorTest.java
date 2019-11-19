import Model.Word;
import Processors.WordProcessor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class WordProcessorTest {
    WordProcessor wordProcessor;
    private String word1, word2, word3, word4, word5, word6;
    private Word wordOne,wordTwo,wordThree,wordFour,wordFive,wordSix;

    @Before
    public void init() {

        wordProcessor = new WordProcessor();
        word1 = "((ana";
        word2 = "(ana)";
        word3 = "[(~{ \n\r\"ana   ";
        word4 = "      123/-)}]:;,.?! \"";
        word5 = "43434ana323545";
        word6 = " ";
        wordOne =wordProcessor.createWordFromString(word1);
        wordTwo =wordProcessor.createWordFromString(word2);
        wordThree =wordProcessor.createWordFromString(word3);
        wordFour =wordProcessor.createWordFromString(word4);
        wordFive =wordProcessor.createWordFromString(word5);
        wordSix =wordProcessor.createWordFromString(word6);

    }

    @Test
    public void testCreateWordFromText() {
        Word wordOne,wordTwo,wordThree,wordFour,wordFive,wordSix;
        wordOne =wordProcessor.createWordFromString(word1);
        wordTwo =wordProcessor.createWordFromString(word2);
        wordThree =wordProcessor.createWordFromString(word3);
        wordFour =wordProcessor.createWordFromString(word4);
        wordFive =wordProcessor.createWordFromString(word5);
        wordSix =wordProcessor.createWordFromString(word6);
        assertTrue(wordOne!=null);
        assertTrue(wordTwo!=null);
        assertTrue(wordThree!=null);
        assertTrue(wordFour!=null);
        assertTrue(wordFive!=null);
        assertTrue(wordSix!=null);


    }
    @Test
    public void testIsCorrectWord(){
        Word word=wordProcessor.createWordFromString("()");
        assertFalse(wordProcessor.isWordCorrect(wordOne));
        assertTrue(wordProcessor.isWordCorrect(wordTwo));
        assertFalse(wordProcessor.isWordCorrect(wordThree));
        assertFalse(wordProcessor.isWordCorrect(wordFour));
        assertFalse(wordProcessor.isWordCorrect(wordFive));
        assertTrue(wordProcessor.isWordCorrect(wordSix));
        assertFalse(wordProcessor.isWordCorrect(word));
        word=wordProcessor.createWordFromString("(f))");
        assertFalse(wordProcessor.isWordCorrect(word));
    }

    @Test
    public void testLetterOccurence(){
        WordProcessor wordProcessorLocal=new WordProcessor();
        wordProcessorLocal.createWordFromString(word2);
        assertTrue(wordProcessorLocal.getLettersOccurence().containsKey('a'));

    }

    @Test
    public void testextractPrefixFromWordText() {
        String prefix1 = wordProcessor.extractPrefixFromWordText(word1);
        assertTrue(prefix1.equals("(("));
        assertTrue(!wordProcessor.isPrefixCorrect(prefix1));
       String prefix2 = wordProcessor.extractPrefixFromWordText(word2);
        assertTrue(prefix2.equals("("));
        assertTrue(wordProcessor.isPrefixCorrect(prefix2));
        assertTrue(wordProcessor.extractPrefixFromWordText(word3).equals("[(~{ \n\r\""));
        assertTrue(wordProcessor.extractPrefixFromWordText(word4).equals("      "));
        assertTrue(wordProcessor.extractPrefixFromWordText(word5).equals(""));

    }

    @Test
    public void testextractPostfixFromWordText() {
        assertTrue(wordProcessor.extractPostfixFromWordText(word1).equals(""));
        assertTrue(wordProcessor.extractPostfixFromWordText(word2).equals(")"));
        assertTrue(wordProcessor.extractPostfixFromWordText(word3).equals("   "));
        assertTrue(wordProcessor.extractPostfixFromWordText(word4).equals("/-)}]:;,.?! \""));
        assertTrue(wordProcessor.extractPostfixFromWordText(word5).equals(""));


    }

    @Test
    public void testExtractWordContentFromWordText() {
        assertTrue(wordProcessor.extractWordContentFromWordText(word1).equals("ana"));
        assertTrue(wordProcessor.extractWordContentFromWordText(word2).equals("ana"));
        assertTrue(wordProcessor.extractWordContentFromWordText(word3).equals("ana"));
        assertTrue(wordProcessor.extractWordContentFromWordText(word4).equals("123"));
        assertTrue(wordProcessor.extractWordContentFromWordText(word5).equals("43434ana323545"));
        assertTrue(wordProcessor.extractWordContentFromWordText(word6).equals(""));

    }
}
