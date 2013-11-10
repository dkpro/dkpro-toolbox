package dkpro.toolbox.wordnet;

import java.util.List;

import de.tudarmstadt.ukp.dkpro.lexsemresource.LexicalSemanticResource;
import de.tudarmstadt.ukp.dkpro.lexsemresource.core.ResourceFactory;
import de.tudarmstadt.ukp.dkpro.lexsemresource.exception.ResourceLoaderException;
import dkpro.toolbox.core.ToolboxException;

public class PrincetonWordNet
    implements WordNet
{
    private LexicalSemanticResource wordnet;
    
    public PrincetonWordNet() 
            throws ToolboxException
    {
        try {
            ResourceFactory factory = new ResourceFactory("classpath:/config/resources.xml");
            wordnet = factory.get("wordnet", "en");
        }
        catch (ResourceLoaderException e) {
            throw new ToolboxException(e);
        }
    }

    @Override
    public List<Synset> getSynsets(String lemma)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getLemmas(String lemma)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Synset getSynset(String lemma, String tag, String senseId)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
