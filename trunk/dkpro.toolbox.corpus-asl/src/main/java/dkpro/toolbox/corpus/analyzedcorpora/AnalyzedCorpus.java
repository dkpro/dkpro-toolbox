package dkpro.toolbox.corpus.analyzedcorpora;

import java.util.ArrayList;
import java.util.List;

import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;

/**
 * An {@link AnalyzedCorpus} is a special object that is backed by a {@link Corpus}, but provides additional analysis methods.
 * 
 * Inspired by NLTK.
 * @author zesch
 *
 */
public class AnalyzedCorpus
{
    private Corpus underlyingCorpus;

    public AnalyzedCorpus(Corpus corpus)
    {
        this.underlyingCorpus = corpus;
    }

    public Corpus getUnderlyingCorpus()
    {
        return underlyingCorpus;
    }
    
    public List<String> getTokens()
            throws CorpusException 
    {
        return getTokens(false);
    }
    
    public List<String> getTokens(boolean toLowerCase)
            throws CorpusException
    {
        List<String> tokens = new ArrayList<String>();
        for (String token : underlyingCorpus.getTokens()) {
            if (toLowerCase) {
                tokens.add(token.toLowerCase());
            }
            else {
                tokens.add(token);
            }
        }
        return tokens;
    }

    public String getName()
            throws CorpusException
    {
        return underlyingCorpus.getName();
    }
}