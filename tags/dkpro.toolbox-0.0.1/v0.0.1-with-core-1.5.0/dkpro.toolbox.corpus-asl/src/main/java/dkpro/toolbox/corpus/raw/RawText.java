package dkpro.toolbox.corpus.raw;

import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;

/**
 * Provides a simpler interface (in comparison to {@link Corpus}) that represents a corpus as a string.
 * 
 * @author zesch
 *
 */
public interface RawText
{

    public String getText() throws CorpusException;
}
