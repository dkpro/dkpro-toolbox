package dkpro.toolbox.corpus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
import dkpro.toolbox.corpus.util.merged.MergedSentenceIterable;
import dkpro.toolbox.corpus.util.merged.MergedTaggedTokenIterable;
import dkpro.toolbox.corpus.util.merged.MergedTagsIterable;
import dkpro.toolbox.corpus.util.merged.MergedTextIterable;
import dkpro.toolbox.corpus.util.merged.MergedTokenIterable;

public class MergedCorpus
    implements Corpus
{
    private Queue<Corpus> corpora;
    
    public MergedCorpus(Corpus ... corpora)
    {
        this.corpora = new LinkedList<Corpus>(Arrays.asList(corpora));
    }
    
    public MergedCorpus(List<Corpus> corpora)
    {
        this.corpora = new LinkedList<Corpus>(corpora);
    }

    @Override
    public Iterable<String> getTokens()
        throws CorpusException
    { 
        return new MergedTokenIterable(corpora);
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens()
        throws CorpusException
    {
        return new MergedTaggedTokenIterable(corpora);
    }

    @Override
    public Iterable<Sentence> getSentences()
        throws CorpusException
    {
        return new MergedSentenceIterable(corpora);
    }

    @Override
    public Iterable<Tag> getTags()
        throws CorpusException
    {
        return new MergedTagsIterable(corpora);

    }

    @Override
    public Iterable<Text> getTexts()
        throws CorpusException
    {
        return new MergedTextIterable(corpora);
    }

    @Override
    public String getLanguage()
    {
        Set<String> languages = new HashSet<String>();
        for (Corpus corpus : corpora) {
            languages.add(corpus.getLanguage());
        }
        return StringUtils.join(languages, "/");
    }

    @Override
    public String getName()
    {
        Set<String> names = new HashSet<String>();
        for (Corpus corpus : corpora) {
            names.add(corpus.getName());
        }
        return StringUtils.join(names, "/");
    }

    @Override
    public String getDescription()
    {
        Set<String> descriptions = new HashSet<String>();
        for (Corpus corpus : corpora) {
            descriptions.add(corpus.getLanguage());
        }
        return StringUtils.join(descriptions, "/");
    }
}