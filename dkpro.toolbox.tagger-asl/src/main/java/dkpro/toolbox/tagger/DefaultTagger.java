package dkpro.toolbox.tagger;

import java.util.ArrayList;
import java.util.List;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

/**
 * Always tags with the given String or "NN" if nothing is provided.
 * 
 * @author zesch
 *
 */
public class DefaultTagger
    extends ToolboxTagger_ImplBase
{
    private String defaultTag = "NN";

    public DefaultTagger(Tagset tagset, String defaultTag)
    {
        super(tagset);
        this.defaultTag = defaultTag;
    }
    
    @Override
    public List<TaggedToken> assignTags(String ... tokens)
            throws ToolboxException
    {
        List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();
        for (String token : tokens) {
            taggedTokens.add(new TaggedToken(token, new Tag(defaultTag, tagset)));
        }
        return taggedTokens;
    }
}