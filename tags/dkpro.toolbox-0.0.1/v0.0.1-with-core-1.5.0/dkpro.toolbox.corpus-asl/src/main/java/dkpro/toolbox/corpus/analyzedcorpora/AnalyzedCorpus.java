package dkpro.toolbox.corpus.analyzedcorpora;

import java.util.ArrayList;
import java.util.List;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.TaggedToken;
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
    
    public List<TaggedToken> getTaggedTokens()
            throws CorpusException
    {
        List<TaggedToken> tokens = new ArrayList<TaggedToken>();
        for (TaggedToken taggedToken : underlyingCorpus.getTaggedTokens()) {
            tokens.add(taggedToken);
        }
        return tokens;
    }

    public String getName()
            throws CorpusException
    {
        return underlyingCorpus.getName();
    }
    
    // TODO implement as real concordancer (with alignment on the target word and fixed left and right context)
    public void getConcordance(String token)
            throws CorpusException
    {
        int counter = 0;
        
        for (Sentence s : this.getUnderlyingCorpus().getSentences()) {
            List<String> tokens = s.getTokens();
            for (int i=0; i<tokens.size(); i++) {
                if (token.equals(tokens.get(i))) {
                    counter++;
                    System.out.println(getHighlightedSentence(tokens, i));
                    
                    if (counter == 10) {
                        return;
                    }
                }
            }
        }
    }
    
    private String getHighlightedSentence(List<String> tokens, int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<tokens.size(); i++) {
            if (i == offset) {
                sb.append(tokens.get(i).toUpperCase());
            }
            else {
                sb.append(tokens.get(i));
            }
            sb.append(" ");
        }
        return sb.toString();
    }
}