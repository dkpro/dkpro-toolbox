package dkpro.toolbox.corpus;

import dkpro.toolbox.core.Tag.Tagset;

public class TextCorpusMetadata
{
    private String language;
    private String description;
    private Tagset tagset;
    private String corpusPath;
    private String[] patterns;
    
    public TextCorpusMetadata(String language, String description, Tagset tagset,
            String corpusPath, String ... patterns)
    {
        super();
        this.language = language;
        this.description = description;
        this.tagset = tagset;
        this.corpusPath = corpusPath;
        this.patterns = patterns;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Tagset getTagset()
    {
        return tagset;
    }

    public void setTagset(Tagset tagset)
    {
        this.tagset = tagset;
    }

    public String getCorpusPath()
    {
        return corpusPath;
    }

    public void setCorpusPath(String corpusPath)
    {
        this.corpusPath = corpusPath;
    }

    public String[] getPatterns()
    {
        return patterns;
    }

    public void setPatterns(String ... patterns)
    {
        this.patterns = patterns;
    }
}