package dkpro.toolbox.corpus.categorized;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CategorizedCorpusTest
{

    @SuppressWarnings("unused")
    @Test
    public void testCategorizedCorpus()
        throws Exception
    {
        CategorizedCorpus corpus = new FirstNamesCorpus();

        int nrOfTokens = 0;
        for (String category : corpus.getCategories()) {
            for (String token : corpus.getTokens(category)) {
                nrOfTokens++;
            }
        }
        assertEquals(7947, nrOfTokens);

        int nrOfLimitedTokens = 0;
        for (String token : corpus.getTokens(10)) {
//            System.out.println(token);
            nrOfLimitedTokens++;
        }
        assertEquals(10, nrOfLimitedTokens);

        nrOfTokens = 0;
        for (String token : corpus.getTokens()) {
            nrOfTokens++;
        }
        assertEquals(7947, nrOfTokens);

    }

    @Test
    public void udhrTest()
            throws Exception
    {
        CategorizedCorpus udhr = new UdhrCorpus();

        assertEquals(91, udhr.getCategories().size());

//        for (String category : udhr.getCategories()) {
//          for (String token : udhr.getTokens(category)) {
//              System.out.println(token);
//          }
//        }
    }

    @Test
    public void inauguralTest()
            throws Exception
    {
        CategorizedCorpus inaugural = new InauguralCorpus();

        assertEquals(56, inaugural.getCategories().size());

//        for (String category : inaugural.getCategories()) {
//          for (String token : inaugural.getTokens(category)) {
//              System.out.println(token);
//          }
//        }
    }
}
