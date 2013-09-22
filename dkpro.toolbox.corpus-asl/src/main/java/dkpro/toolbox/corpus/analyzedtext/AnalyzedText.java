package dkpro.toolbox.corpus.analyzedtext;

import dkpro.toolbox.corpus.Corpus;

/**
 * A text is a special object that is backed by a corpus, but provides additional analysis methods.
 * 
 * Inspired by NLTK.
 * @author zesch
 *
 */
public class AnalyzedText
{
    private Corpus corpus;

    public AnalyzedText(Corpus corpus)
    {
        this.corpus = corpus;
    }

    public Corpus getCorpus()
    {
        return corpus;
    }
}