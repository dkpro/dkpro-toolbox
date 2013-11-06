package dkpro.toolbox.corpus.analyzed;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
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
    implements Corpus
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
    
    public List<Sentence> getSentenceList()
            throws CorpusException
    {
        List<Sentence> sentences = new ArrayList<Sentence>();
        for (Sentence sentence : underlyingCorpus.getSentences()) {
            sentences.add(sentence);
        }
        return sentences;
    }
    
    public List<String> getTokenList()
            throws CorpusException 
    {
        return getTokenList(false);
    }
    
    public List<String> getTokenList(boolean toLowerCase)
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
    
    public List<TaggedToken> getTaggedTokenList()
            throws CorpusException
    {
        List<TaggedToken> tokens = new ArrayList<TaggedToken>();
        for (TaggedToken taggedToken : underlyingCorpus.getTaggedTokens()) {
            tokens.add(taggedToken);
        }
        return tokens;
    }
    
    // Contributed by Marian Waltereit
    public void getConcordance(String token)
            throws CorpusException
    {
        int counter = 0;
        int maxLengthBefore = 0;
        int maxLengthAfter = 0;
        int maxLength = 0;

        // collects max. 10 sentences containing the target token
        // also computes max sentence length, max length left and right of the target 
        List<Sentence> sentencesWithTarget = new ArrayList<Sentence>();
        for (Sentence s : this.getUnderlyingCorpus().getSentences()) {
            if (counter == 10) {
                break;
            }
            List<String> tokens = s.getTokens();
            for (int i = 0; counter != 10 && i < tokens.size(); i++) {
                if (token.equals(tokens.get(i))) {
                    counter++;
                    sentencesWithTarget.add(s);
                    if (maxLength < s.toString().length()) {
                        maxLength = s.toString().length();
                        maxLengthBefore = StringUtils.join(tokens.subList(0, i), ' ').length();
                        maxLengthAfter = StringUtils.join(tokens.subList(i + 1, tokens.size()), ' ').length();
                    }
                }
            }
        }
        
        // output sentences with padding
        for (Sentence s : sentencesWithTarget) {
            List<String> tokens = s.getTokens();
            for (int i = 0; i < tokens.size(); i++) {
                if (token.equals(tokens.get(i))) {
                    String beforeToken = StringUtils.join(tokens.subList(0, i), ' ');
                    String afterToken = StringUtils.join(tokens.subList(i + 1, tokens.size()), ' ');

                    int leftDiff = maxLengthBefore - beforeToken.length();
                    int rightDiff = maxLengthAfter - afterToken.length();
                    
                    String highLightedSentence = getHighlightedSentence(leftDiff, rightDiff, tokens, i);
                    
                    // trim sentence so that if fits in the console
                    int trimLeftSize = Math.min(50, leftDiff + beforeToken.length());
                    int trimRightSize = Math.min(50, rightDiff + afterToken.length());
                    String trimmedSentence = highLightedSentence.substring(
                            leftDiff + beforeToken.length() - trimLeftSize,
                            leftDiff + beforeToken.length() + token.length() + 2 + trimRightSize
                    );
                    System.out.println(trimmedSentence);
                }
            }
        }
    }
    
    private String getHighlightedSentence(int leftDiff, int rightDiff, List<String> tokens, int offset)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.leftPad("", leftDiff));
        for (int i = 0; i < tokens.size(); i++) {
            if (i == offset) {
                sb.append(" ");
                sb.append(tokens.get(i));
                sb.append(" ");
            } else {
                sb.append(tokens.get(i));
            }
            sb.append(" ");
        }
        sb.append(StringUtils.leftPad("", rightDiff));
        return sb.toString();
    }

    @Override
    public Iterable<Tag> getTags()
        throws CorpusException
    {
        return underlyingCorpus.getTags();
    }

    @Override
    public Iterable<Text> getTexts()
        throws CorpusException
    {
        return underlyingCorpus.getTexts();
    }

    @Override
    public String getLanguage()
    {
        return underlyingCorpus.getLanguage();
    }

    @Override
    public String getDescription()
    {
        return underlyingCorpus.getDescription();
    }

    @Override
    public String getName()
    {
        return underlyingCorpus.getName();
    }

    @Override
    public Iterable<String> getTokens()
        throws CorpusException
    {
        return underlyingCorpus.getTokens();
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens()
        throws CorpusException
    {
        return underlyingCorpus.getTaggedTokens();
    }

    @Override
    public Iterable<Sentence> getSentences()
        throws CorpusException
    {
        return underlyingCorpus.getSentences();
    }
}