package dkpro.toolbox.tagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;

public class RegularExpressionTaggerTest
{

    @Test
    public void testRegExpTagger() throws Exception {
        
        Map<String, String> patternMap = new HashMap<String, String>();
        patternMap.put("^-?[0-9]+(.[0-9]+)?$", "CD");   // cardinal numbers
        patternMap.put("(The|the|A|a|An|an)$", "DT");   // articles
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
        
        // TODO add full test sentence
        List<TaggedToken> goldTokens = new ArrayList<TaggedToken>();
        goldTokens.add(new TaggedToken("The", new Tag("DT", "en")));
        goldTokens.add(new TaggedToken("Fulton", new Tag("NNP", "en")));
        goldTokens.add(new TaggedToken("County", new Tag("NNP", "en")));
        goldTokens.add(new TaggedToken("Grand", new Tag("NN", "en")));
        goldTokens.add(new TaggedToken("Jury", new Tag("NN", "en")));

        double accuracy = tagger.getAccuracy(goldTokens);
        System.out.println("Accuracy: " + accuracy);
    }
}