package dkpro.toolbox.tagger;

import java.util.List;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public interface ToolboxTagger
{

    public List<Sentence> assignTags(Sentence ... sentences) throws ToolboxException;
    public List<TaggedToken> assignTags(String ... tokens) throws ToolboxException;
    public List<TaggedToken> assignTags(List<String> tokens) throws ToolboxException;
    
    public double getAccuracy(Sentence ... taggedSentences) throws ToolboxException;
    public double getAccuracy(TaggedToken ... taggedTokens) throws ToolboxException;
    public double getAccuracy(List<TaggedToken> taggedTokens) throws ToolboxException;
}
