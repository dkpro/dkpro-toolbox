package dkpro.toolbox.corpus.special;

import java.util.HashSet;
import java.util.Set;

import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.special.StopwordsCorpus.StopwordsLanguage;

public class Stopwords
{
    public static Set<String> get(StopwordsLanguage language)
            throws CorpusException
    {
        Set<String> stopwords = new HashSet<String>();
        for (String stopword : new StopwordsCorpus(language).getTokens()) {
            stopwords.add(stopword);
        }
        return stopwords;
    }
}
