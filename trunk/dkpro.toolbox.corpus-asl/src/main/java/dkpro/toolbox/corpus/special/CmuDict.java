package dkpro.toolbox.corpus.special;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import dkpro.toolbox.core.Pronunciation;
import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;

public class CmuDict
    implements Corpus
{
    private Map<String, List<Pronunciation>> dict;
    
    public CmuDict()
            throws CorpusException
    {
        
        dict = new HashMap<String, List<Pronunciation>>();
        
        try {
            URL url = ResourceUtils.resolveLocation("classpath:/corpus/cmudict/cmudict");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String line = null;
            while((line = in.readLine()) != null) {
                String[] parts = line.split(" ");
                
                String key = parts[0].toLowerCase();
                
                List<String> phonemes = new ArrayList<String>();
                for (int i=2; i<parts.length; i++) {
                    phonemes.add(parts[i]);
                }
                Pronunciation pronunciation = new Pronunciation(phonemes);
                
                List<Pronunciation> pronunciations;
                if (dict.containsKey(key)) {
                    pronunciations = dict.get(key);
                }
                else {
                    pronunciations = new ArrayList<Pronunciation>();
                }
                pronunciations.add(pronunciation);
                dict.put(key, pronunciations);
            }
        }
        catch (IOException e) {
            throw new CorpusException(e);
        }
    }
    
    public boolean containsPronunciation(String token) {
        return dict.containsKey(token);
    }
    
    public List<Pronunciation> getPronunciations(String token) {
        if (dict.containsKey(token)) {
            return dict.get(token);
        }
        else {
            return null;
        }
    }
    
    public Pronunciation getMostProbablyPronunciation(String token) {
        if (dict.containsKey(token)) {
            return dict.get(token).get(0);
        }
        else {
            return null;
        } 
    }

    @Override
    public Iterable<String> getTokens()
        throws CorpusException
    {
        return dict.keySet();
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
    public String getLanguage()
    {
        return "en";
    }

    @Override
    public String getName()
    {
        return "cmudict";
    }

    @Override
    public String getDescription()
    {
        return "CMU pronunciation dictionary";
    }

    @Override
    public Tagset getTagset()
    {
        throw new UnsupportedOperationException("CmuDict has no tagset.");
    }
}
