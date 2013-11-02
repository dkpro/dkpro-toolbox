package dkpro.toolbox.tools.tagger;

import java.util.Collection;
import java.util.List;

import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public interface ToolboxTagger
{
    public Collection<TaggedToken> tag(String text, String language) throws ToolboxException;
    
    public Collection<TaggedToken> tag(List<String> tokens, String language) throws ToolboxException;
}
