package dkpro.toolbox.corpus.special;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;

public class WordCorpus
    implements Corpus
{
    public enum AvailableLanguage {
        en,
        en_basic
    }
    
    private AvailableLanguage language;
    private List<String> tokens;
    
    public WordCorpus(AvailableLanguage language)
            throws CorpusException
    {
        this.language = language;
        
        String file = "classpath:/corpus/words/" + language.name() + ".txt.gz";
        try {
            tokens = readWords(file);
        }
        catch (IOException e) {
            throw new CorpusException(e);
        }
    }
    
    private List<String> readWords(String path)
            throws IOException
    {     
        URL url = ResourceUtils.resolveLocation(path, this, null);
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(is), "UTF-8"));
        
        List<String> tokens = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            tokens.add(line);
        }
        return tokens;
    }
    
    @Override
    public Iterable<String> getTokens()
        throws CorpusException
    {
        return tokens;
    }
    
    @Override
    public Iterable<String> getTokens(int maxItems)
        throws CorpusException
    {   
        Set<String> tokens = new HashSet<String>();
        int counter = 0;
        for (String item : tokens) {
            if (counter < maxItems) {
                tokens.add(item);
            }
        }
        return tokens;
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens()
        throws CorpusException
    {
        throw new CorpusException("Unavailable for this corpus.");
    }

    @Override
    public Iterable<Sentence> getSentences()
        throws CorpusException
    {
        throw new CorpusException("Unavailable for this corpus.");
    }

    @Override
    public Iterable<Tag> getTags()
        throws CorpusException
    {
        throw new CorpusException("Unavailable for this corpus.");
    }

    @Override
    public Iterable<Text> getTexts()
        throws CorpusException
    {
        throw new CorpusException("Unavailable for this corpus.");
    }
    
    @Override
    public Iterable<TaggedToken> getTaggedTokens(int maxItems)
        throws CorpusException
    {
        throw new CorpusException("Unavailable for this corpus.");
    }

    @Override
    public Iterable<Sentence> getSentences(int maxItems)
        throws CorpusException
    {
        throw new CorpusException("Unavailable for this corpus.");
    }

    @Override
    public Iterable<Tag> getTags(int maxItems)
        throws CorpusException
    {
        throw new CorpusException("Unavailable for this corpus.");
    }

    @Override
    public Tagset getTagset()
        throws CorpusException
    {
        throw new CorpusException("Unavailable for this corpus.");
    }
    
    @Override
    public String getLanguage()
    {
        return language.name();
    }

    @Override
    public String getName()
    {
        return "words";
    }

    @Override
    public String getDescription()
    {
        return "word lists of several languages";
    }
}
