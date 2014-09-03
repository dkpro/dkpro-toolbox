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

    public void addSynset(String senseId, Synset synset) {
        synsetMap.put(senseId + synset.getPos(), synset);
    }

    public Synset getSynset(String combinedId) {
        if (synsetMap.containsKey(combinedId)) {
            return synsetMap.get(combinedId);
        }
        else {
            return null;
        }
    }
    
    public Synset getSynset(String senseId, String pos) {
        if (synsetMap.containsKey(senseId + pos)) {
            return synsetMap.get(senseId + pos);
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