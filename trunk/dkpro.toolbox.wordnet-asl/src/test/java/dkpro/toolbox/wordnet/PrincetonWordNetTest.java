package dkpro.toolbox.wordnet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

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

//    @Test
//    public void testGetSemanticRelations()
//        throws LexicalSemanticResourceException
//    {
//
//        wordnet.setIsCaseSensitive(false);
//        Set<String> expectedHyponyms = new HashSet<String>();
//        expectedHyponyms.add("cladogram#13912424|---n");
//        expectedHyponyms.add("stemma#13912540|---n");
//
//        Set<Entity> hyponyms = wordnet.getRelatedEntities(new Entity("tree", PoS.n, "13912260"),
//                SemanticRelation.hyponymy);
//
//        for (Entity hyponym : hyponyms) {
//            assertTrue(hyponym.toString(), expectedHyponyms.contains(hyponym.toString()));
//        }
//
//        Set<String> expectedCohyponyms = new HashSet<String>();
//        expectedHyponyms.add("cladogram#13912424|---n");
//        expectedHyponyms.add("stemma#13912540|---n");
//
//        Set<Entity> cohyponyms = wordnet.getRelatedEntities(
//                new Entity("stemma", PoS.n, "13912540"), SemanticRelation.hyponymy);
//
//        for (Entity cohyponym : cohyponyms) {
//            assertTrue(cohyponym.toString(), expectedCohyponyms.contains(cohyponym.toString()));
//        }
//
//        wordnet.setIsCaseSensitive(true);
//        expectedHyponyms = new HashSet<String>();
//        expectedHyponyms.add("cladogram#13912424|---n");
//        expectedHyponyms.add("stemma#13912540|---n");
//
//        hyponyms = wordnet.getRelatedEntities(new Entity("tree", PoS.n, "13912260"),
//                SemanticRelation.hyponymy);
//
//        for (Entity hyponym : hyponyms) {
//            assertTrue(hyponym.toString(), expectedHyponyms.contains(hyponym.toString()));
//        }
//
//        hyponyms = wordnet.getRelatedEntities(new Entity("Tree", PoS.n, "13912260"),
//                SemanticRelation.hyponymy);
//        assertEquals(hyponyms.size(), 0);
//
//    }
//
//    @Test
//    public void testGetGloss()
//        throws LexicalSemanticResourceException
//    {
//        wordnet.setIsCaseSensitive(false);
//        Entity e = new Entity("knocked-out", PoS.adj, "680634");
//        String gloss = "damaged; \"the gym has some of the most knocked-out equipment since Vic Tanny\"";
//        assertEquals(gloss, wordnet.getGloss(e));
//
//        Entity e2 = new Entity("laugh", PoS.n, "6778102");
//        String gloss2 = "a humorous anecdote or remark intended to provoke laughter; \"he told a very funny joke\"; "
//                + "\"he knows a million gags\"; \"thanks for the laugh\"; \"he laughed unpleasantly at his own jest\"; "
//                + "\"even a schoolboy's jape is supposed to have some ascertainable point\"";
//        assertEquals(gloss2, wordnet.getGloss(e2));
//
//        wordnet.setIsCaseSensitive(true);
//        e = new Entity("knocked-out", PoS.adj, "680634");
//        gloss = "damaged; \"the gym has some of the most knocked-out equipment since Vic Tanny\"";
//        assertEquals(gloss, wordnet.getGloss(e));
//
//        e = new Entity("Knocked-out", PoS.adj, "680634");
//        assertEquals("", wordnet.getGloss(e));
//
//        e2 = new Entity("laugh", PoS.n, "6778102");
//        gloss2 = "a humorous anecdote or remark intended to provoke laughter; \"he told a very funny joke\"; "
//                + "\"he knows a million gags\"; \"thanks for the laugh\"; \"he laughed unpleasantly at his own jest\"; "
//                + "\"even a schoolboy's jape is supposed to have some ascertainable point\"";
//        assertEquals(gloss2, wordnet.getGloss(e2));
//
//        e2 = new Entity("Laugh", PoS.n, "6778102");
//        assertEquals("", wordnet.getGloss(e2));
//    }
//
//    @Test
//    public void testGetPseudoGloss()
//        throws LexicalSemanticResourceException
//    {
//        Set<LexicalRelation> lexRels = new HashSet<LexicalRelation>();
//        lexRels.add(LexicalRelation.antonymy);
//        lexRels.add(LexicalRelation.synonymy);
//
//        Map<SemanticRelation, Integer> semRelMap = new HashMap<SemanticRelation, Integer>();
//        semRelMap.put(SemanticRelation.holonymy, 2);
//        semRelMap.put(SemanticRelation.meronymy, 2);
//        semRelMap.put(SemanticRelation.hypernymy, 2);
//        semRelMap.put(SemanticRelation.hyponymy, 3);
//
//        Entity e = new Entity("warm", PoS.adj, "2530861");
//        String gloss = new String("cool warm cordial hearty"); // zhu
//        String pgloss = wordnet.getPseudoGloss(e, lexRels, semRelMap);
//        assertEquals(gloss, pgloss);
//        // System.exit(1); //zhu
//    }
//
//    // this tests some error I found when trying to find entities returned as related to the string
//    // "pick_up"
//    @Test
//    public void testPickUp()
//        throws LexicalSemanticResourceException
//    {
//        Set<Entity> entities = wordnet.getEntity("pick-up");
//        for (Entity e : entities) {
//            System.out.println(e);
//            for (String lexeme : e.getLexemes()) {
//                System.out.println(lexeme);
//                System.out.println(e.getPos());
//                System.out.println(e.getSense(lexeme));
//                wordnet.getRelatedLexemes(lexeme, e.getPos(), e.getSense(lexeme),
//                        LexicalRelation.synonymy);
//            }
//        }
//    }
//
//    @Test
//    @Ignore("There seems to be a bug in the WordNet API causing containsEntity() return false "
//            + "on some entities- See Bug 161")
//    public void testGetEntities()
//        throws LexicalSemanticResourceException
//    {
//        wordnet.setIsCaseSensitive(false);
//        int i = 0;
//        for (Entity entity : wordnet.getEntities()) {
//            Set<String> testLexemes = entity.getLexemes();
//            StringBuilder sb = new StringBuilder();
//            for (String t : testLexemes) {
//                sb.append(t + " " + entity.getSense(t) + " ");
//            }
//            sb.append(entity.getPos());
//            assertTrue(entity.toString(), wordnet.containsEntity(entity));
//            i++;
//        }
//        assertEquals(117659, i);
//
//        wordnet.setIsCaseSensitive(true);
//        i = 0;
//        for (Entity entity : wordnet.getEntities()) {
//            Set<String> testLexemes = entity.getLexemes();
//            StringBuilder sb = new StringBuilder();
//            for (String t : testLexemes) {
//                sb.append(t + " " + entity.getSense(t) + " ");
//            }
//            sb.append(entity.getPos());
//            ++i;
//            assertTrue(entity.toString(), wordnet.containsEntity(entity));
//
//        }
//        assertEquals(117659, i);
//    }
//
//    // TODO - readd if implemented more efficiently
//    // @Ignore
//    @Test
//    public void testGetNumberOfEntities()
//        throws LexicalSemanticResourceException
//    {
//        assertEquals(117659, wordnet.getNumberOfEntities());
//    }
//
//    @Test
//    public void testHyponymMap()
//        throws Exception
//    {
//        wordnet.setIsCaseSensitive(false);
//        EntityGraph eg = EntityGraphManager.getEntityGraph(wordnet, EntityGraphType.JGraphT);
//        eg.getIntrinsicInformationContent(wordnet.getEntity("tree").iterator().next());
//    }
//    
//    @Test
//    public void testGetMostFrequentEntity()
//        throws Exception
//    {
//        wordnet.setIsCaseSensitive(false);
//        System.out.println(wordnet.getMostFrequentEntity("car"));
//        System.out.println(wordnet.getMostFrequentEntity("bat"));
//        System.out.println(wordnet.getMostFrequentEntity("bank"));
//    }
}
