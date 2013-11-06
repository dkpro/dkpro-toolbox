package dkpro.toolbox.corpus.analyzedcorpora;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.analyzed.AnalyzedCorpus;
import dkpro.toolbox.corpus.analyzed.CorpusManager;
import dkpro.toolbox.corpus.analyzed.CorpusManager.CorpusName;

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
        assertEquals(4878, text.getSentences().size());
    }
    
    @Test
    public void getSentenceTest() throws CorpusException {
        Sentence sentence = CorpusManager.getSentence(CorpusName.MobyDick);
        
        assertEquals(10, sentence.getTokens().size());
    }
}
