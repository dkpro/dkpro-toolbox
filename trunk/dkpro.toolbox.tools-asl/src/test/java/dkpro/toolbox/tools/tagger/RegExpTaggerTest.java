package dkpro.toolbox.tools.tagger;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;

public class RegExpTaggerTest
{

    @Test
    public void regExpTaggerTest()
            throws Exception
    {
        Map<String, Tag> patterns = new HashMap<String, Tag>();
        patterns.put(".*ing$",  new Tag("VBG", Tagset.brown));  // gerunds
        patterns.put(".*ed$",   new Tag("VBD", Tagset.brown));  // simple past
        patterns.put(".*es$",   new Tag("VBZ", Tagset.brown));  // 3rd singular present
        patterns.put(".*ould$", new Tag("MD",  Tagset.brown));  // modal verbs
        patterns.put(".*s$",    new Tag("NNS", Tagset.brown));  // plural nouns
        patterns.put(".*$",     new Tag("NN",  Tagset.brown));  // nouns (default)

        
        RegExpTagger tagger = new RegExpTagger(patterns);
        for (TaggedToken taggedToken : tagger.tag("The only Conservative councillor representing Cambridge resigned from the city council .")) {
            System.out.println(taggedToken);
        }
    }
}
