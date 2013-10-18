package dkpro.toolbox.corpus.categorized;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;

public class UdhrCorpus
    extends CategorizedCorpusBase
{

    public UdhrCorpus()
            throws CorpusException
    {
        Collection<File> files;
        try {
            files = FileUtils.listFiles(
                    new File(ResourceUtils.resolveLocation("classpath:/corpus/udhr").getFile()),
                    new String[] { "bin" },
                    false);
        }
        catch (IOException e) {
            throw new CorpusException(e);
        }

        for (File file : files) {
            String[] parts = file.getName().split("-");
            addCorpus(parts[0], new SerializedCorpus("classpath:/corpus/udhr/", file.getName()));
        }
    }
}