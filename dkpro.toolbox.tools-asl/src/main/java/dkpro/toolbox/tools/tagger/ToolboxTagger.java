package dkpro.toolbox.tools.tagger;

import java.util.Collection;
import java.util.List;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public interface ToolboxTagger
{
    public Collection<TaggedToken> tag(String text) throws ToolboxException;
    
    public Collection<TaggedToken> tag(List<String> tokens) throws ToolboxException;

    public void evaluate(List<Sentence> taggedSentences) throws ToolboxException;
    public void evaluateCanonical(List<Sentence> taggedSentences) throws ToolboxException;
}
