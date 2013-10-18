package dkpro.toolbox.corpus.categorized;

import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;

public class FirstNamesCorpus
    extends CategorizedCorpusBase
{

    public FirstNamesCorpus()
            throws CorpusException
    {
        addCorpus("male", new SerializedCorpus("classpath:/corpus/names", "male.txt.bin"));
        addCorpus("female", new SerializedCorpus("classpath:/corpus/names", "female.txt.bin"));
    }
}