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
    
    public void addLemma(String lemma, String senseId, String pos) {
        Set<String> ids;
        if (lemmaMap.containsKey(lemma)) {
            ids = lemmaMap.get(lemma);
        } 
        else {
            ids = new HashSet<String>();
        }
        ids.add(senseId + pos);
        lemmaMap.put(lemma, ids);
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