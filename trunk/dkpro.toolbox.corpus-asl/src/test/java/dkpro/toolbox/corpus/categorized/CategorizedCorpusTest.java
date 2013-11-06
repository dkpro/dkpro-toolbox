package dkpro.toolbox.corpus.categorized;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CategorizedCorpusTest
{

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
        
        nrOfTokens = 0;
        for (String token : corpus.getTokens()) {
            nrOfTokens++;
        }
        assertEquals(7947, nrOfTokens);

    }
}
