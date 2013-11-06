package dkpro.toolbox.tools.tagger;

import org.junit.Test;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.corpus.analyzed.AnalyzedCorpus;
import dkpro.toolbox.corpus.analyzed.CorpusManager;
import dkpro.toolbox.corpus.analyzed.CorpusManager.CorpusName;

public class LookupTaggerTest
{
    @Test
    public void lookupTaggerTest()
        throws Exception
    {
        AnalyzedCorpus corpus = CorpusManager.getCorpus(CorpusName.MobyDick);
        Sentence sentence = CorpusManager.getSentence(CorpusName.MobyDick);      
        
        ToolboxTagger lookupTagger = new LookupTagger(corpus, 100);
        
        lookupTagger.evaluate(corpus.getSentenceList());
        
        for (TaggedToken taggedToken : lookupTagger.tag(sentence.getTokens())) {
            System.out.println(taggedToken);
        }
    }
}
