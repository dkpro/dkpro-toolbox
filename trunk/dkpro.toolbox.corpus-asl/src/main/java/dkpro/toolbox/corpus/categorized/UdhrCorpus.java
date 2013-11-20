package dkpro.toolbox.corpus.categorized;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;
import dkpro.toolbox.corpus.util.ToolboxUtils;

public class UdhrCorpus
    extends CategorizedCorpusBase
{

    public UdhrCorpus()
            throws CorpusException
    {
        Collection<File> files;
        try {
            files = ToolboxUtils.getAllFiles("classpath*:/corpus/udhr/", new String[]{"bin"});
        }
        catch (IOException e) {
            throw new CorpusException(e);
        }

        for (File file : files) {
            String[] parts = file.getName().split("-");
            addCorpus(parts[0], new SerializedCorpus("classpath:/corpus/udhr/", file.getName()));
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