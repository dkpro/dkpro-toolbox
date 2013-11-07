package dkpro.toolbox.tagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public class RegularExpressionTagger
    extends ToolboxTagger_ImplBase
{
    private Map<String, String> patternMap;

    public RegularExpressionTagger(Tagset tagset, Map<String, String> patternMap)
    {
        super(tagset);
        this.patternMap = patternMap;
    }
    
    @Override
    public List<TaggedToken> assignTags(String ... tokens)
            throws ToolboxException
    {
        List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();
        for (String token : tokens) {
            boolean match = false;
            for (String pattern : patternMap.keySet()) {
                if (!match && token.matches(pattern)) {
                    taggedTokens.add(new TaggedToken(token, new Tag(patternMap.get(pattern), tagset)));
                    match = true;
                }
            }
            if (!match) {
                taggedTokens.add(new TaggedToken(token, new Tag("NN", tagset)));
            }           
        }
        return taggedTokens;
    }
}