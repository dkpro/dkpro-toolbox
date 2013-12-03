package dkpro.toolbox.corpus.categorized;

import java.io.IOException;

import org.springframework.core.io.Resource;

import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;
import dkpro.toolbox.corpus.util.ToolboxUtils;

public class InauguralCorpus
    extends CategorizedCorpusBase
{

    public InauguralCorpus()
            throws CorpusException
    {
        Resource[] resources;
        try {
            resources = ToolboxUtils.getResources("classpath*:corpus/inaugural/**/*");
        }
        catch (IOException e) {
            throw new CorpusException(e);
        }

        for (Resource resource : resources) {
            try {
                String uri = resource.getURI().toString();
                if (uri.endsWith(".bin")) {
                    String[] parts = uri.split("/");
                    String parent = parts[parts.length-2];
                    addCorpus(parent, new SerializedCorpus(uri.substring(0, uri.length()-parts[parts.length-1].length())));
                }          
            }
            catch (IOException e) {
                throw new CorpusException(e);
            }
        }
    }

    @Override
    public String getLanguage()
    {
        return "en";
    }
    
    @Override
    public String getLanguage(String category)
        throws CorpusException
    {
        return getLanguage();
    }

    @Override
    public String getName()
    {
        return "InauguralAddresses";
    }

    @Override
    public String getDescription()
    {
        return "Presidential Inaugural Addresses";
    }
}