package dkpro.toolbox.corpus.categorized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.collection.CollectionReaderDescription;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.DkproCorpus;

public abstract class CategorizedCorpusBase
    implements CategorizedCorpus
{
    
    protected Map<String, Corpus> categoryCorpusMap = new HashMap<String, Corpus>();

    public void addCorpus(String category, Corpus corpus)
    {  
        categoryCorpusMap.put(category, corpus);
    }
    
    public void addCorpus(String category, Tagset tagset, String language, String name, String description, CollectionReaderDescription reader)
        throws CorpusException
    {
        Corpus corpus = new DkproCorpus(language, tagset, name, description, reader);
        addCorpus(category, corpus);
    }
    
    @Override
    public Iterable<String> getTokens()
        throws CorpusException
    {
        List<Iterator<String>> iterators = new ArrayList<Iterator<String>>();
        for (String category : this.getCategories()) {
            iterators.add(this.getTokens(category).iterator());
        }
        return new CombinedIterable<String>(iterators);
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens()
        throws CorpusException
    {
        List<Iterator<TaggedToken>> iterators = new ArrayList<Iterator<TaggedToken>>();
        for (String category : this.getCategories()) {
            iterators.add(this.getTaggedTokens(category).iterator());
        }
        return new CombinedIterable<TaggedToken>(iterators);
    }

    @Override
    public Iterable<Sentence> getSentences()
        throws CorpusException
    {
        List<Iterator<Sentence>> iterators = new ArrayList<Iterator<Sentence>>();
        for (String category : this.getCategories()) {
            iterators.add(this.getSentences(category).iterator());
        }
        return new CombinedIterable<Sentence>(iterators);
    }

    @Override
    public Iterable<Tag> getTags()
        throws CorpusException
    {
        List<Iterator<Tag>> iterators = new ArrayList<Iterator<Tag>>();
        for (String category : this.getCategories()) {
            iterators.add(this.getTags(category).iterator());
        }
        return new CombinedIterable<Tag>(iterators);
    }

    @Override
    public Iterable<Text> getTexts()
        throws CorpusException
    {
        List<Iterator<Text>> iterators = new ArrayList<Iterator<Text>>();
        for (String category : this.getCategories()) {
            iterators.add(this.getTexts(category).iterator());
        }
        return new CombinedIterable<Text>(iterators);
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
    
    @Override
    public Tagset getTagset()
    {
        Set<Tagset> tagsets = new HashSet<Tagset>();
        for (Corpus corpus : categoryCorpusMap.values()) {
            tagsets.add(corpus.getTagset());
        }
        if (tagsets.size() > 1) {
            System.out.println("Warning: Corpora with different tagsets in collection. Returning first one.");
        }
        return tagsets.iterator().next();
    }
}
