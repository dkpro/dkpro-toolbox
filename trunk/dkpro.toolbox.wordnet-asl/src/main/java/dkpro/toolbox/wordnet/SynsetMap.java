package dkpro.toolbox.wordnet;

import java.util.HashMap;
import java.util.Map;

public class SynsetMap
{
    private Map<String, Synset> synsetMap;
    
    public SynsetMap()
    {
        synsetMap = new HashMap<String, Synset>();
    }

    public void addSynset(String id, Synset synset) {
        synsetMap.put(id, synset);
    }

    public Synset getSynset(String id) {
        if (synsetMap.containsKey(id)) {
            return synsetMap.get(id);
        }
        else {
            return null;
        }
    }
    
    public Map<String, Synset> getSynsetMap()
    {
        return synsetMap;
    }

    public void setSynsetMap(Map<String, Synset> synsetMap)
    {
        this.synsetMap = synsetMap;
    } 
}