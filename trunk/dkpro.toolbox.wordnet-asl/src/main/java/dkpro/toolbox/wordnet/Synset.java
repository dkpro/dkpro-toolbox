package dkpro.toolbox.wordnet;

import java.util.ArrayList;
import java.util.List;

public class Synset
{

    private List<String> lemmas;
    private String definition;
    private String tag;
    private String[] examples;

    public Synset() {
        lemmas = new ArrayList<String>();
        definition = null;
        tag = null;
        examples = null;               
    }
    
    public Synset(String tag, List<String> lemmas, String definition, String... examples)
    {
        super();
        this.tag = tag;
        this.lemmas = lemmas;
        this.definition = definition;
        this.examples = examples;
    }

    public List<String> getLemmas()
    {
        return lemmas;
    }

    public void setLemmas(List<String> lemmas)
    {
        this.lemmas = lemmas;
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public String[] getExamples()
    {
        return examples;
    }

    public void setExamples(String... examples)
    {
        this.examples = examples;
    }
}