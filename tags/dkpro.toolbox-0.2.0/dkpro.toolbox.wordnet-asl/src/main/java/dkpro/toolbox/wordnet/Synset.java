package dkpro.toolbox.wordnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Synset
{
    private String senseId;
    private List<String> lemmas;
    private String definition;
    private String pos;
    private List<String> examples;
    private Set<String> hypernyms;
    private Set<String> hyponyms;
    private Set<String> holonyms;
    private Set<String> meronyms;
    private Set<String> antonyms;   // contains the actual string in contrast to the senseIds of the other relations

    public Synset() {
        senseId = null;
        definition = null;
        pos = null;
        examples = new ArrayList<String>();  
        lemmas = new ArrayList<String>();
        hypernyms = new HashSet<String>();
        hyponyms = new HashSet<String>();
        hyponyms = new HashSet<String>();
        meronyms = new HashSet<String>();
        antonyms = new HashSet<String>();
    }
    
    public Synset(String pos, Collection<String> lemmas)
    {
        super();
        this.pos = pos;
        setLemmas(lemmas);
    }

    public List<String> getLemmas()
    {
        return lemmas;
    }

    public void setLemmas(Collection<String> lemmas)
    {
        this.lemmas = new ArrayList<String>();
        this.lemmas.addAll(lemmas);
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public String getPos()
    {
        return pos;
    }

    public void setPos(String pos)
    {
        this.pos = pos;
    }

    public List<String> getExamples()
    {
        return examples;
    }

    public void setExamples(List<String> examples)
    {
        this.examples.addAll(examples);
    }
    
    public void setExamples(String ... examples)
    {
        this.examples.addAll(Arrays.asList(examples));
    }

    public String getSenseId()
    {
        return senseId;
    }

    public void setSenseId(String senseId)
    {
        this.senseId = senseId;
    }

    // TODO should either return synset directly or not be accessible at all
    // same is true for other relations
    public Set<String> getHypernyms()
    {
        return hypernyms;
    }

    public void setHypernyms(Set<String> hypernyms)
    {
        this.hypernyms = hypernyms;
    }

    public Set<String> getHyponyms()
    {
        return hyponyms;
    }

    public void setHyponyms(Set<String> hyponyms)
    {
        this.hyponyms = hyponyms;
    }

    public Set<String> getHolonyms()
    {
        return holonyms;
    }

    public void setHolonyms(Set<String> holonyms)
    {
        this.holonyms = holonyms;
    }

    public Set<String> getMeronyms()
    {
        return meronyms;
    }

    public void setMeronyms(Set<String> meronyms)
    {
        this.meronyms = meronyms;
    }

    public Set<String> getAntonyms()
    {
        return antonyms;
    }

    public void setAntonyms(Set<String> antonyms)
    {
        this.antonyms = antonyms;
    }

    @Override
    public String toString()
    {
        return "Synset [senseId=" + senseId + ", lemmas=" + lemmas + ", pos=" + pos + "]";
    }
}