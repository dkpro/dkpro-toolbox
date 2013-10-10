package dkpro.toolbox.corpus.categorized;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.collection.CollectionReaderDescription;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.DkproCorpus;

public class CategorizedCorpusBase
    implements CategorizedCorpus
{
    
    protected Map<String, Corpus> categoryCorpusMap = new HashMap<String, Corpus>();

    public void addCorpus(String category, Corpus corpus)
    {  
        categoryCorpusMap.put(category, corpus);
    }
    
    public void addCorpus(String category, String language, String name, String description, CollectionReaderDescription reader)
        throws CorpusException
    {
        Corpus corpus = new DkproCorpus(language, name, description, reader);
        addCorpus(category, corpus);
    }
    
    @Override
    public Iterable<String> getTokens()
        throws CorpusException
    {
        // how to create an iterable from multiple underlying iterables
        return null;
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens()
        throws CorpusException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Sentence> getSentences()
        throws CorpusException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Tag> getTags()
        throws CorpusException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Text> getTexts()
        throws CorpusException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLanguage()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<String> getTokens(String category)
        throws CorpusException
    {
        checkCategory(category);
        
        return categoryCorpusMap.get(category).getTokens();
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens(String category)
        throws CorpusException
    {
        checkCategory(category);
        
        return categoryCorpusMap.get(category).getTaggedTokens();
    }

    @Override
    public Iterable<Sentence> getSentences(String category)
        throws CorpusException
    {
        checkCategory(category);
        
        return categoryCorpusMap.get(category).getSentences();
    }

    @Override
    public Iterable<Tag> getTags(String category)
        throws CorpusException
    {
        checkCategory(category);
        
        return categoryCorpusMap.get(category).getTags();
    }

    @Override
    public Iterable<Text> getTexts(String category)
        throws CorpusException
    {
        checkCategory(category);
        
        return categoryCorpusMap.get(category).getTexts();
    }

    @Override
    public Set<String> getCategories()
    {
        return categoryCorpusMap.keySet();
    }

    private void checkCategory(String category)
        throws CorpusException
    {
        if (!categoryCorpusMap.containsKey(category)) {
            throw new CorpusException("Unknown category " + category);
        }
    }
}
