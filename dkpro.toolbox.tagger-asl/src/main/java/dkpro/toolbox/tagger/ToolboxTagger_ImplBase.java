package dkpro.toolbox.tagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public abstract class ToolboxTagger_ImplBase
    implements ToolboxTagger
{
    protected String languageCode;

    public ToolboxTagger_ImplBase(String languageCode)
    {
        this.languageCode = languageCode;
    }
    
    @Override
    public List<Sentence> assignTags(Sentence ... sentences)
            throws ToolboxException
    {
        for (Sentence s : sentences) {
            assignTags(s.getTokens().toArray(new String[0]));
        }
        return Arrays.asList(sentences);
    }

    @Override
    public List<TaggedToken> assignTags(List<String> tokens)
            throws ToolboxException
    {
        return assignTags(tokens.toArray(new String[0]));
    }
    
    @Override
    public abstract List<TaggedToken> assignTags(String ... tokens)
            throws ToolboxException;

    @Override
    public double getAccuracy(Sentence ... goldSentences)
        throws ToolboxException
    {

        int correct = 0;
        int size = 0;
        
        for (Sentence sentence : goldSentences) {
            correct += getNumberOfCorrectlyTagged(sentence.getTaggedTokens().toArray(new TaggedToken[0]));
            size += sentence.getTokens().size();
        }
        
        return computeAccuracy(correct, size);  
    }

    @Override
    public double getAccuracy(List<TaggedToken> goldTokens)
        throws ToolboxException
    {
        return getAccuracy(goldTokens.toArray(new TaggedToken[0]));
    }
    
    @Override
    public double getAccuracy(TaggedToken ... goldTokens)
        throws ToolboxException
    {
        int size = goldTokens.length;
        int correct = getNumberOfCorrectlyTagged(goldTokens);
        
        return computeAccuracy(correct, size);
    }
    
    private int getNumberOfCorrectlyTagged(TaggedToken ... goldTokens)
            throws ToolboxException
    {
        int correct = 0;
        
        List<String> tokens = new ArrayList<String>();
        for (TaggedToken goldToken : goldTokens) {
            tokens.add(goldToken.getToken());
        }

        List<TaggedToken> assignedTags = assignTags(tokens);
        
        for (int i=0; i<goldTokens.length; i++) {
            Tag gold = goldTokens[i].getTag();
            Tag assigned = assignedTags.get(i).getTag();
            if (gold.getCanonicalTag().equals(assigned.getCanonicalTag())) {
                correct++;
            }
        }
        
        return correct;
    }
    
    private double computeAccuracy(int correct, int size) {
        if (size > 0) {
            return (double) correct / size;
        }
        else {
            return 0.0;
        }
    }
}