package dkpro.toolbox.corpus.special;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.io.conll.Conll2000Reader;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.corpus.CorpusBase;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.util.ChunkIterable;

public class ConllCorpus
    extends CorpusBase
{

    private CollectionReaderDescription reader;
    
    public ConllCorpus()
            throws CorpusException
    {
        try {
            reader = CollectionReaderFactory.createReaderDescription(
                    Conll2000Reader.class, 
                    Conll2000Reader.PARAM_SOURCE_LOCATION, "src/main/resources/corpus/conll/", 
                    Conll2000Reader.PARAM_PATTERNS, "*.txt"

            );
        }
        catch (ResourceInitializationException e) {
            throw new CorpusException(e);
        }
    }
    
    public Iterable<String> getChunks()
        throws CorpusException
    {
        return getChunks(Integer.MAX_VALUE);
    }

    public Iterable<String> getChunks(int maxItems)
        throws CorpusException
    {
        return new ChunkIterable(new JCasIterable(getReader()).iterator(), getTagset(), maxItems);
    }
    
    @Override
    public Tagset getTagset()
        throws CorpusException
    {
        return Tagset.penntreebank;
    }

    @Override
    public String getLanguage()
    {
        return "en";
    }

    @Override
    public String getName()
    {
        return "CoNLL2000";
    }

    @Override
    public String getDescription()
    {
        return "CoNLL-2000 Chunk Corpus";
    }

    @Override
    protected CollectionReaderDescription getReader()
    {
        return reader;
    }

}