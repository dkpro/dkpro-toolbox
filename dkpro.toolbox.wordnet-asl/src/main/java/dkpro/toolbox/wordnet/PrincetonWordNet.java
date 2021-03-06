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
    private LemmaMap lemmaMap;
    private SynsetMap synsetMap;
    
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
    public Set<Synset> getSynsets()
        throws ToolboxException
    {
        return new HashSet<Synset>(synsetMap.getSynsetMap().values());
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
                if (synset != null && synset.getPos().equals(pos.name())) {
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
    public Synset getSynset(String senseId, WordNetPos pos)
        throws ToolboxException
    {
        return synsetMap.getSynset(senseId, pos.name());
    }

    @Override
    public Synset getSynset(String lemma, WordNetPos pos, String senseId)
        throws ToolboxException
    {
        if (lemmaMap.getLemmaMap().containsKey(lemma)) {
            for (String id : lemmaMap.getLemmaMap().get(lemma)) {
                Synset synset = synsetMap.getSynset(senseId, pos.name());
                if (synset != null && synset.getSenseId().equals(senseId)) {
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
        BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(is), "UTF-8"));
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    public Set<Synset> getHypernyms(Synset synset)
        throws ToolboxException
    {
        return getRelatedSynsets(synset.getHypernyms(), synset.getPos());
    }

    @Override
    public Set<Synset> getHyponyms(Synset synset)
        throws ToolboxException
    {
        return getRelatedSynsets(synset.getHyponyms(), synset.getPos());
    }

    @Override
    public Set<Synset> getHolonyms(Synset synset)
        throws ToolboxException
    {
        return getRelatedSynsets(synset.getHolonyms(), synset.getPos());
    }

    @Override
    public Set<Synset> getMeronyms(Synset synset)
        throws ToolboxException
    {
        return getRelatedSynsets(synset.getMeronyms(), synset.getPos());
    }
    
    private Set<Synset> getRelatedSynsets(Set<String> senseIds, String pos)
        throws ToolboxException
    {
        Set<Synset> synsets = new HashSet<Synset>();
        for (String senseId : senseIds) {
            synsets.add(synsetMap.getSynset(senseId, pos));
        }
        return synsets;
    }
}