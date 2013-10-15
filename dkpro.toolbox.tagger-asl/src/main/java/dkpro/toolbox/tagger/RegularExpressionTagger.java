package dkpro.toolbox.tagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public class RegularExpressionTagger
    implements ToolboxTagger
{
    private Map<String, String> patternMap;
    private String languageCode;

    public RegularExpressionTagger(String languageCode, Map<String, String> patternMap)
    {
        this.patternMap = patternMap;
        this.languageCode = languageCode;
    }
    
    @Override
    public List<TaggedToken> assignTags(Sentence s)
            throws ToolboxException
    {
        return assignTags(s.getTokens());
    }

    @Override
    public List<TaggedToken> assignTags(List<String> tokens)
            throws ToolboxException
    {
        List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();
        for (String token : tokens) {
            taggedTokens.add(new TaggedToken(token, new Tag("NN", languageCode)));
        }
        return taggedTokens;
    }
}