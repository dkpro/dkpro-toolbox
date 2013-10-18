package dkpro.toolbox.corpus.analyzedcorpora;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.analyzedcorpora.CorpusManager.CorpusName;

public class CorpusManagerTest
{

    @Test
    public void textManagerTest() throws CorpusException {
        AnalyzedCorpus text = CorpusManager.getCorpus(CorpusName.MobyDick);
        
        assertEquals("mobydick-melville", text.getName());
    }

    @Test
    public void multipleFilesTest() throws CorpusException {
        AnalyzedCorpus text = CorpusManager.getCorpus(CorpusName.InauguralAddresses);
        
        assertEquals(145240, text.getTokens().size());
    }
}
