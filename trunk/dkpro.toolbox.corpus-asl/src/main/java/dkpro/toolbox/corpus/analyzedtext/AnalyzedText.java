package dkpro.toolbox.corpus.analyzedtext;

import java.util.ArrayList;
import java.util.List;

import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;

/**
 * A text is a special object that is backed by a corpus, but provides additional analysis methods.
 * 
 * Inspired by NLTK.
 * @author zesch
 *
 */
public class AnalyzedText
{
    private Corpus underlyingCorpus;

    public AnalyzedText(Corpus corpus)
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
        List<String> tokens = new ArrayList<String>();
        for (String token : underlyingCorpus.getTokens()) {
            tokens.add(token);
        }
        return tokens;
    }

    public String getName()
            throws CorpusException
    {
        return underlyingCorpus.getName();
    }
}