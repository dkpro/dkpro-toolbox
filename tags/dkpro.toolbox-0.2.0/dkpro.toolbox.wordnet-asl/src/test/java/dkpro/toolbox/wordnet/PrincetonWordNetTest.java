package dkpro.toolbox.wordnet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dkpro.toolbox.core.ToolboxException;
import dkpro.toolbox.wordnet.WordNet.WordNetPos;

public class PrincetonWordNetTest
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

    @AfterClass
    public static void finish()
    {
        wordnet = null;
    }
    
    @Test
    public void testContainsLemma() throws Exception
    {
        assertTrue(wordnet.containsLemma("tree"));
        assertTrue(wordnet.containsLemma("Tree"));
        assertTrue(wordnet.containsLemma("cold"));
        assertFalse(wordnet.containsLemma("ColD"));
        assertFalse(wordnet.containsLemma("grhphafah"));
        assertFalse(wordnet.containsLemma("Grhphafah"));
    }

    @Test
    public void testGetSynset()
        throws Exception
    {
        Set<String> expectedResults = new HashSet<String>();
        expectedResults.add("1145163");
        expectedResults.add("1616293");
        expectedResults.add("319111");
        expectedResults.add("1934205");
        expectedResults.add("13104059");
        expectedResults.add("13912260");

        Set<Synset> synsets = wordnet.getSynsets("tree");
        assertEquals(6, synsets.size());
        for (Synset synset : synsets) {
            System.out.println(synset);
            assertTrue(synset.getSenseId(), expectedResults.contains(synset.getSenseId()));
        }
    }
    
    @Test
    public void testGetSynsetPos()
        throws Exception
    {
        Set<String> expectedResults = new HashSet<String>();
        expectedResults.add("13104059");
        expectedResults.add("13912260");

        Set<Synset> synsets = wordnet.getSynsets("tree", WordNetPos.n);
        assertEquals(2, synsets.size());
        for (Synset synset : synsets) {
            System.out.println(synset);
            assertTrue(synset.getSenseId(), expectedResults.contains(synset.getSenseId()));
        }
    }
 
    @Test
    public void testGetLexicalRelations()
        throws Exception
    {

        Set<String> expectedAntonyms = new HashSet<String>();
        expectedAntonyms.add("cool");

        Synset warm = wordnet.getSynset("warm", WordNetPos.adj, "2530861");   
        assertNotNull(warm);
        
        Set<String> antonyms = wordnet.getAntonyms(warm);
        assertEquals(1, antonyms.size());
        for (String antonym : antonyms) {
            System.out.println(antonym);
            assertTrue(antonym, expectedAntonyms.contains(antonym));
        }
    }

    @Test
    public void testGetSemanticRelations()
        throws Exception
    {

        Set<String> expectedHyponyms = new HashSet<String>();
        expectedHyponyms.add("13912424");
        expectedHyponyms.add("13912540");

        Synset tree = wordnet.getSynset("tree", WordNetPos.n, "13912260");   

        for (Synset synset : wordnet.getHyponyms(tree)) {
            assertTrue(synset.getSenseId(), expectedHyponyms.contains(synset.getSenseId()));
        }
    }
}
