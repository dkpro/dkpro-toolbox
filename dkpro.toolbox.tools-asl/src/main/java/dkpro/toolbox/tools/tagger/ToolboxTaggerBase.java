package dkpro.toolbox.tools.tagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public abstract class ToolboxTaggerBase
    implements ToolboxTagger
{
    protected ToolboxTagger backoffTagger;
    
    public void setBackoffTagger(ToolboxTagger tagger) {
        backoffTagger = tagger;
    }
    
    @Override
    public Collection<TaggedToken> tag(String text)
        throws ToolboxException
    {
        List<String> tokens = Arrays.asList(text.split(" "));
        return tag(tokens);
    }
    
    @Override
    public void evaluate(List<Sentence> taggedSentences)
        throws ToolboxException
    {
        evaluateTags(taggedSentences, false);
    }
    
    public void evaluateCanonical(List<Sentence> taggedSentences)
        throws ToolboxException
    {
        evaluateTags(taggedSentences, true);
    }
    
    private void evaluateTags(List<Sentence> taggedSentences, boolean useCanonical)
        throws ToolboxException
    {
        int correct = 0;
        int n = 0;
        for (Sentence taggedSentence : taggedSentences) {
            List<TaggedToken> assignedTags = new ArrayList<TaggedToken>(this.tag(taggedSentence.getTokens()));
            List<TaggedToken> goldTags = taggedSentence.getTaggedTokens();
            
            if (assignedTags.size() != goldTags.size()) {
                throw new ToolboxException("List of assigned tags and gold tags does not have the same size.");
            }
            
            for (int i=0; i<goldTags.size(); i++) {
                n++;
                
                String assignedTag;
                String goldTag;            
                if (useCanonical) {
                    assignedTag = assignedTags.get(i).getTag().getCanonicalTag();
                    goldTag = goldTags.get(i).getTag().getCanonicalTag();
                }
                else {
                    assignedTag = assignedTags.get(i).getTag().getOriginalTag();
                    goldTag = goldTags.get(i).getTag().getOriginalTag();                    
                }
                if (goldTag.equals(assignedTag)) {
                    correct++;
                }
            }
        }
        
        System.out.println("Accuracy: " + (double) correct / n);
    }
}
