package dkpro.toolbox.wordnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import dkpro.toolbox.core.ToolboxException;

public class PrincetonWordNet
    implements WordNet
{
    private static LemmaMap lemmaMap;
    private static SynsetMap synsetMap;
    
    public PrincetonWordNet() 
            throws ToolboxException
    {
        try {
            Gson gson = new Gson();
            
            String lemmaString = gzipToString("classpath:/wordnet/lemmamap.json.gz");
            String synsetString = gzipToString("classpath:/wordnet/synsetmap.json.gz");
            lemmaMap = gson.fromJson(lemmaString, LemmaMap.class);
            synsetMap = gson.fromJson(synsetString, SynsetMap.class);
        }
        catch (JsonSyntaxException e) {
            throw new ToolboxException(e);
        }
        catch (IOException e) {
            throw new ToolboxException(e);
        }
    }

    @Override
    public boolean containsLemma(String lemma) {
        return lemmaMap.getLemmaMap().containsKey(lemma);
    }
    
    @Override
    public Set<Synset> getSynsets(String lemma)
    {
        if (lemmaMap.getLemmaMap().containsKey(lemma)) {
            Set<Synset> synsets = new HashSet<Synset>();
            for (String id : lemmaMap.getLemmaMap().get(lemma)) {
                synsets.add(synsetMap.getSynset(id));
            }
            return synsets;
        }
        else {
            return Collections.emptySet();
        }
    }

    @Override
    public Set<Synset> getSynsets(String lemma, WordNetPos pos)
    {
        if (lemmaMap.getLemmaMap().containsKey(lemma)) {
            Set<Synset> synsets = new HashSet<Synset>();
            for (String id : lemmaMap.getLemmaMap().get(lemma)) {
                Synset synset = synsetMap.getSynset(id);
                if (synset.getPos().equals(pos.name())) {
                    synsets.add(synset);
                }
            }
            return synsets;
        }
        else {
            return Collections.emptySet();
        }
    }

    @Override
    public Synset getSynset(String lemma, WordNetPos pos, String senseId)
        throws ToolboxException
    {
        if (lemmaMap.getLemmaMap().containsKey(lemma)) {
            for (String id : lemmaMap.getLemmaMap().get(lemma)) {
                Synset synset = synsetMap.getSynset(id);
                System.out.println(synset);
                if (synset.getPos().equals(pos.name()) && synset.getSenseId().equals(senseId)) {
                    return synset;
                }
            }
        }
        
        return null;
    }

    
    @Override
    public Set<String> getAntonyms(Synset synset)
        throws ToolboxException
    {     
        return synset.getAntonyms();
    }
    
    @Override
    public Set<String> getLemmas()
    {
        return lemmaMap.getLemmas();
    }
    
    private String gzipToString(String path)
            throws IOException
    {

        URL url = ResourceUtils.resolveLocation(path, this, null);
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(is)));
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}