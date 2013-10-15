package dkpro.toolbox.tagger;

import java.util.List;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public interface ToolboxTagger
{

    public List<TaggedToken> assignTags(Sentence s) throws ToolboxException;
    
    public List<TaggedToken> assignTags(List<String> tokens) throws ToolboxException;

}
