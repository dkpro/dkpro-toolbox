package dkpro.toolbox.corpus.analyzedtext;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.analyzedtext.TextManager.TextName;

public class TextManagerTest
{

    @Test
    public void textManagerTest() throws CorpusException {
        AnalyzedText text = TextManager.getText(TextName.MobyDick);
        
        assertEquals("mobydick-melville", text.getCorpus().getName());
    }
}
