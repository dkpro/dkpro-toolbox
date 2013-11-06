package dkpro.toolbox.corpus.raw;

import org.apache.commons.lang.StringUtils;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;

/**
 * A raw text constructed from the corpus that backs this text.
 * For some corpora, it is easier to construct the raw text from the structured input provided by the corpus,
 * instead of trying to read the plain text itself.
 * 
 * @author zesch
 *
 */
public class CorpusBackedRawText
    implements RawText
{
    private Corpus corpus;
    private String text;
    
    public CorpusBackedRawText(Corpus corpus)
    {
        this.corpus = corpus;
        this.text = null;
    }

    @Override
    public String getText()
            throws CorpusException
    {
        if (text == null) {
            loadText();
        }
        
        return text;        
    }
    
    private void loadText() throws CorpusException {
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence : corpus.getSentences()) {
            String sentenceString = StringUtils.join(sentence.getTokens(), " ");
            sb.append(sentenceString);
            sb.append("\n");
            sb.append(" ");
        }
        this.text = sb.toString();
    }
}