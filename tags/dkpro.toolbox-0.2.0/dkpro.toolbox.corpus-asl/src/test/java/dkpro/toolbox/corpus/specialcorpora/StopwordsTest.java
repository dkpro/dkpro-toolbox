package dkpro.toolbox.corpus.specialcorpora;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.special.Stopwords;
import dkpro.toolbox.corpus.special.StopwordsCorpus;
import dkpro.toolbox.corpus.special.StopwordsCorpus.StopwordsLanguage;

public class StopwordsTest
{

    @Test
    public void stopwordCorporaTest()
        throws Exception
    {
        for (StopwordsLanguage language : StopwordsLanguage.values()) {
            Corpus corpus = new StopwordsCorpus(language);
            assertTrue(language.name(), corpus.getTokens().iterator().hasNext());
        }
    }
    
    @Test
    public void stopwordStaticTest()
        throws Exception
    {
        for (StopwordsLanguage language : StopwordsLanguage.values()) {
            assertTrue(language.name(), Stopwords.get(language).size() > 0);
        }
    }
}