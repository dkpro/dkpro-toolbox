package dkpro.toolbox.corpus.special;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase;
import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import de.tudarmstadt.ukp.dkpro.wsd.io.reader.SemCorXMLReader;
import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.corpus.CorpusBase;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.util.SenseAnnotatedSentenceIterable;

public class SemCor
    extends CorpusBase
{

    private CollectionReaderDescription reader;
    
    private Map<String, String> senseMap;

    public SemCor()
            throws CorpusException
    {
        try {
            reader = CollectionReaderFactory.createReaderDescription(
                    SemCorXMLReader.class,
                    SemCorXMLReader.PARAM_SOURCE_LOCATION, "classpath:/corpus/semcor/",
                    SemCorXMLReader.PARAM_PATTERNS,  new String[] {
                            ResourceCollectionReaderBase.INCLUDE_PREFIX + "*.xml" }
            );
        }
        catch (ResourceInitializationException e) {
            throw new CorpusException(e);
        }
        
        senseMap = new HashMap<String, String>();
        
        try {
            URL url = ResourceUtils.resolveLocation("classpath:/wordnet/index.sense");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
    
            String line = null;
            while((line = in.readLine()) != null) {
                String[] parts = line.split(" ");
                senseMap.put(parts[0], parts[2]);
            }
        }
        catch (IOException e) {
            throw new CorpusException(e);
        }
    }
    
    public Iterable<Sentence> getSenseAnnotatedSentences()
        throws CorpusException
    {
        return getSenseAnnotatedSentences(Integer.MAX_VALUE);
    }

    public Iterable<Sentence> getSenseAnnotatedSentences(int maxItems)
        throws CorpusException
    {
        return new SenseAnnotatedSentenceIterable(new JCasIterable(getReader()).iterator(), Tagset.brown, senseMap);
    }
    
    @Override
    public Tagset getTagset()
        throws CorpusException
    {
        return Tagset.brown;
    }

    @Override
    public String getLanguage()
    {
        return "en";
    }

    @Override
    public String getName()
    {
        return "SemCor 3.0";
    }

    @Override
    public String getDescription()
    {
        return "SemCor 3.0 - WordNet annotated Brown corpus";
    }

    @Override
    protected CollectionReaderDescription getReader()
    {
        return reader;
    }

}