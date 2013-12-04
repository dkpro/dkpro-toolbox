package dkpro.toolbox.corpus.categorized;

import java.util.Map;

import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.TextCorpus;
import dkpro.toolbox.corpus.TextCorpusMetadata;

/**
 * Reads a categorized corpus from plain text files provided by the user.
 * 
 * @author zesch
 *
 */
public class UserProvidedCategorizedCorpus
    extends CategorizedCorpusBase
{
    private String language;
    private String name;
    private String description;
    
    public UserProvidedCategorizedCorpus(String language, String name, String description, Map<String, TextCorpusMetadata> categoryCorpusMap)
            throws Exception
    {
        this.language = language;
        this.name = name;
        this.description = description;
                
        for (String category : categoryCorpusMap.keySet()) {
            TextCorpusMetadata tcm = categoryCorpusMap.get(category);

            addCorpus(category, new TextCorpus(
                    tcm.getCorpusPath(), 
                    tcm.getTagset(), 
                    tcm.getLanguage(),
                    category,
                    tcm.getDescription(),
                    tcm.getPatterns()
            ));
        }
    }

    @Override
    public String getLanguage(String category)
        throws CorpusException
    {
        return language;
    }

    @Override
    public String getLanguage()
    {
        return language;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }
}