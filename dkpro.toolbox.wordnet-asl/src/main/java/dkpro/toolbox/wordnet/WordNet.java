package dkpro.toolbox.wordnet;

import java.util.Set;

import dkpro.toolbox.core.ToolboxException;

public interface WordNet
{
    public enum WordNetPos {
        n,
        v,
        adj,
        adv
    }

    public boolean containsLemma(String lemma) throws ToolboxException;

    public Set<Synset> getSynsets() throws ToolboxException;
    public Set<Synset> getSynsets(String lemma) throws ToolboxException;
    public Set<Synset> getSynsets(String lemma, WordNetPos pos) throws ToolboxException;
    
    public Synset getSynset(String lemma, WordNetPos pos, String senseId) throws ToolboxException;

    public Set<String> getLemmas() throws ToolboxException;
    
    public Set<String> getAntonyms(Synset synset) throws ToolboxException;
        
    public Set<Synset> getHypernyms(Synset synset) throws ToolboxException;
    public Set<Synset> getHyponyms(Synset synset) throws ToolboxException;
    public Set<Synset> getHolonyms(Synset synset) throws ToolboxException;
    public Set<Synset> getMeronyms(Synset synset) throws ToolboxException;    
}