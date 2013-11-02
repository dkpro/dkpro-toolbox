package dkpro.toolbox.tools.tagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public class RegExpTagger
    implements ToolboxTagger
{
    private Map<String, Tag> patternMap;
    
    public RegExpTagger(Map<String, Tag> patternMap)
    {
        this.patternMap = patternMap;
    }

    @Override
    public Collection<TaggedToken> tag(String text, String language)
        throws ToolboxException
    {
        List<String> tokens = Arrays.asList(text.split(" "));
        return tag(tokens, language);
    }

    @Override
    public Collection<TaggedToken> tag(List<String> tokens, String language)
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
        }
        
        return taggedTokens;
    }
}