package dkpro.toolbox.tagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import dkpro.toolbox.core.TaggedToken;

public class RegularExpressionTaggerTest
{

    @Test
    public void testRegExpTagger() throws Exception {
        
        Map<String, String> patternMap = new HashMap<String, String>();
        patternMap.put("^-?[0-9]+(.[0-9]+)?$", "CD");   // cardinal numbers
        patternMap.put("(The|the|A|a|An|an)$", "AT");   // articles
        patternMap.put(".*able$", "JJ");                // adjectives
        patternMap.put(".*ness$", "NN");                // nouns formed from adjectives
        patternMap.put(".*ly$", "RB");                  // adverbs
        patternMap.put(".*s$", "NNS");                  // plural nouns
        patternMap.put(".*ing$", "VBG");                // gerunds
        patternMap.put(".*ed$", "VBD");                 // past tense verbs
        patternMap.put(".*", "NN");                     // nouns (default)
        
        RegularExpressionTagger tagger = new RegularExpressionTagger("en", patternMap);
        
        List<String> tokens = new ArrayList<String>();
        String[] parts = "The Fulton County Grand Jury said Friday an investigation of Atlanta's recent primary election produced `` no evidence '' that any irregularities took place .".split(" ");
        tokens.addAll(Arrays.asList(parts));
    
        List<TaggedToken> taggedTokens = tagger.assignTags(tokens);
        
        for (TaggedToken taggedToken : taggedTokens) {
            System.out.println(taggedToken);
        }
    }
}