package dkpro.toolbox.wordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LemmaMap
{
    private Map<String, Set<String>> lemmaMap;
    
    public LemmaMap()
    {
        lemmaMap = new HashMap<String, Set<String>>(); 
    }
    
    public void addLemma(String lemma, String id) {
        Set<String> synsetIds;
        if (lemmaMap.containsKey(lemma)) {
            synsetIds = lemmaMap.get(lemma);
        } 
        else {
            synsetIds = new HashSet<String>();
        }
        synsetIds.add(id);
        lemmaMap.put(lemma, synsetIds);
    }
    
    public Set<String> getLemmas() {
        return lemmaMap.keySet();
    }

    public Map<String, Set<String>> getLemmaMap()
    {
        return lemmaMap;
    }

    public void setLemmaMap(Map<String, Set<String>> lemmaMap)
    {
        this.lemmaMap = lemmaMap;
    }
}