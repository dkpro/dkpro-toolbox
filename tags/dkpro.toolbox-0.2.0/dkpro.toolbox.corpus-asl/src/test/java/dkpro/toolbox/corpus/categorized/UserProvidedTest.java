package dkpro.toolbox.corpus.categorized;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.corpus.TextCorpusMetadata;

public class UserProvidedTest
{

    @Test
    public void userProvidedTest() 
        throws Exception
    {
        Map<String, TextCorpusMetadata> corpusMap = new HashMap<String, TextCorpusMetadata>();
        corpusMap.put("nospam", new TextCorpusMetadata(
                "en",
                "nospam email",
                Tagset.penntreebank,
                "src/test/resources/test_corpora/email/nospam/",
                "*.txt")
        );
        corpusMap.put("spam", new TextCorpusMetadata(
                "en",
                "spam email",
                Tagset.penntreebank,
                "src/test/resources/test_corpora/email/spam/",
                "*.txt")
        );


        UserProvidedCategorizedCorpus corpus = new UserProvidedCategorizedCorpus(
                "en",
                "emails",
                "Spam and Nospam emails",
                corpusMap
        );
        
        int nrOfCategories = 0;
        int nrOfTokens = 0;
        for (String category : corpus.getCategories()) {
            System.out.println(category);
            for (String token : corpus.getTokens(category)) {
                System.out.println(token);
                nrOfTokens++;
            }
            nrOfCategories++;
        }
        assertEquals(2, nrOfCategories);
        assertEquals(37, nrOfTokens);
    }
}
