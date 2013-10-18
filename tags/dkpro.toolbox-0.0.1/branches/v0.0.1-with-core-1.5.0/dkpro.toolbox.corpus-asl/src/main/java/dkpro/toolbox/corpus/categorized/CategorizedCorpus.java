package dkpro.toolbox.corpus.categorized;

import java.util.Set;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;

public interface CategorizedCorpus
    extends Corpus
{
    
    public Iterable<String> getTokens(String category) throws CorpusException;
    
    public Iterable<TaggedToken> getTaggedTokens(String category) throws CorpusException;
    
    public Iterable<Sentence> getSentences(String category) throws CorpusException;
    
    public Iterable<Tag> getTags(String category) throws CorpusException;
    
    public Iterable<Text> getTexts(String category) throws CorpusException;

    public Set<String> getCategories();
}