package dkpro.toolbox.tools.tagger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public class RegExpTagger
    extends ToolboxTaggerBase
{
    private Map<String, Tag> patternMap;
    
    public RegExpTagger(Map<String, Tag> patternMap)
    {
        this.patternMap = patternMap;
    }

    @Override
    public Collection<TaggedToken> tag(List<String> tokens)
        throws ToolboxException
    {
        List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();
        
        for (String token : tokens) {
            boolean matched = false;
            for (String pattern : patternMap.keySet()) {
                if (!matched && token.matches(pattern)) {
                    taggedTokens.add(new TaggedToken(token, patternMap.get(pattern)));
                    matched = true;
                }
            }
            
            // catch case that no default rule was assigned
            if (!matched) {
                taggedTokens.add(new TaggedToken(token, new Tag("O", Tagset.brown)));
                matched = true;
            }
        }
        
        return taggedTokens;
    }
}