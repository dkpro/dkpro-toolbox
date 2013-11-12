package dkpro.toolbox.wordnet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import dkpro.toolbox.core.ToolboxException;
import dkpro.toolbox.wordnet.WordNet.WordNetPos;

public class SynsetTest
{
    private static PrincetonWordNet wordnet;

    @BeforeClass
    public static void initializeWordNet()
    {
        try {
            wordnet = new PrincetonWordNet();
        }
        catch (ToolboxException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void synsetTest() throws Exception {

        Synset synset = wordnet.getSynset("knocked-out", WordNetPos.adj, "680634");   
        assertNotNull(synset);
        
        String gloss = "damaged; \"the gym has some of the most knocked-out equipment since Vic Tanny\" ";
        assertEquals(gloss, synset.getDefinition());
  
        Synset synset2 = wordnet.getSynset("laugh", WordNetPos.n, "6778102");   
        assertNotNull(synset2);
        
        String gloss2 = "a humorous anecdote or remark intended to provoke laughter; \"he told a very funny joke\"; "
                  + "\"he knows a million gags\"; \"thanks for the laugh\"; \"he laughed unpleasantly at his own jest\"; "
                  + "\"even a schoolboy's jape is supposed to have some ascertainable point\" ";
        assertEquals(gloss2, synset2.getDefinition());
    }
}
