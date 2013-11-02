package dkpro.toolbox.tools.tagger;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;

public class RegExpTaggerTest
{

    @Test
    public void regExpTaggerTest()
            throws Exception
    {
        Map<String, Tag> patterns = new HashMap<String, Tag>();
        patterns.put(".*ing$",  new Tag("VBG", "en"));  // gerunds
        patterns.put(".*ed$",   new Tag("VBD", "en"));  // simple past
        patterns.put(".*es$",   new Tag("VBZ", "en"));  // 3rd singular present
        patterns.put(".*ould$", new Tag("MD",  "en"));  // modal verbs
        patterns.put(".*s$",    new Tag("NNS", "en"));  // plural nouns
        patterns.put(".*$",     new Tag("NN",  "en"));  // nouns (default)

        
        RegExpTagger tagger = new RegExpTagger(patterns);
        for (TaggedToken taggedToken : tagger.tag("The only Conservative councillor representing Cambridge resigned from the city council .", "en")) {
            System.out.println(taggedToken);
        }
    }
}
