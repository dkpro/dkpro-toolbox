package dkpro.toolbox.corpus.categorized;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;

import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;
import dkpro.toolbox.corpus.util.ToolboxUtils;

public class UdhrCorpus
    extends CategorizedCorpusBase
{

    public UdhrCorpus()
            throws CorpusException
    {
        Resource[] resources;
        try {
            resources = ToolboxUtils.getResources("classpath*:/corpus/udhr/**/*");
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
        return StringUtils.join(getCategories(), ",");
    }
    
    @Override
    public String getLanguage(String category)
        throws CorpusException
    {
        return category;
    }

    @Override
    public String getName()
    {
        return "UHDR";
    }

    @Override
    public String getDescription()
    {
        return "Universal Declaration of Human Rights in various languages";
    }
}