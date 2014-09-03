package dkpro.toolbox.tagger;

import java.util.Collection;
import java.util.List;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag.TagLevel;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public interface ToolboxTagger
{
    public static final String UNKNOWN_TAG = "UNK";
    
    public Collection<TaggedToken> tag(String text) throws ToolboxException;
    public Collection<TaggedToken> tag(List<String> tokens) throws ToolboxException;

    public void evaluate(Sentence taggedSentence, TagLevel tagLevel) throws ToolboxException;
    public void evaluate(List<Sentence> taggedSentences, TagLevel tagLevel) throws ToolboxException;
}