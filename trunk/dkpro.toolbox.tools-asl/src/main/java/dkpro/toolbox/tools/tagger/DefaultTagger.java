package dkpro.toolbox.tools.tagger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public class DefaultTagger
    extends ToolboxTaggerBase
{

    private String defaultTag;
    
    public DefaultTagger(String defaultTag)
    {
        this.defaultTag = defaultTag;
    }

    @Override
    public Collection<TaggedToken> tag(List<String> tokens)
        throws ToolboxException
    {
        List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();
        
        for (String token : tokens) {
            taggedTokens.add(new TaggedToken(token, new Tag(defaultTag, "en")));
        }
        
        return taggedTokens;
    }
}