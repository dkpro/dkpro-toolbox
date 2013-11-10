package dkpro.toolbox.tagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag.TagLevel;
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
    public void evaluate(Sentence taggedSentence, TagLevel tagLevel)
        throws ToolboxException
    {
        List<Sentence> sentences = new ArrayList<Sentence>();
        sentences.add(taggedSentence);
        evaluateTags(sentences, tagLevel);
    }
    
    @Override
    public void evaluate(List<Sentence> taggedSentences, TagLevel tagLevel)
        throws ToolboxException
    {
        evaluateTags(taggedSentences, tagLevel);
    }
    
    private void evaluateTags(List<Sentence> taggedSentences, TagLevel tagLevel)
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
                if (tagLevel.equals(TagLevel.canonical)) {
                    assignedTag = assignedTags.get(i).getTag().getCanonicalTag();
                    goldTag = goldTags.get(i).getTag().getCanonicalTag();
                }
                else if (tagLevel.equals(TagLevel.simplified)) {
                    assignedTag = assignedTags.get(i).getTag().getSimplifiedTag();
                    goldTag = goldTags.get(i).getTag().getSimplifiedTag();                    
                }
                else {
                    assignedTag = assignedTags.get(i).getTag().getOriginalTag();
                    goldTag = goldTags.get(i).getTag().getOriginalTag();                    
                }
                
                if (goldTag.equals(assignedTag)) {
                    correct++;
//                    System.out.println("c: " + taggedSentence.getTokens().get(i) + " - " + goldTag + " - " + assignedTag);

                }
                else {
//                    System.out.println("w: " + taggedSentence.getTokens().get(i) + " - " + goldTag + " - " + assignedTag);
                }
            }
        }
        
        System.out.println("Accuracy: " + (double) correct / n);
    }
}
