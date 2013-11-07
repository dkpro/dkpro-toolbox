package dkpro.toolbox.tools.tagger;

import org.junit.Test;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.corpus.analyzed.AnalyzedCorpus;
import dkpro.toolbox.corpus.analyzed.CorpusManager;
import dkpro.toolbox.corpus.analyzed.CorpusManager.CorpusName;

public class NGramTaggerTest
{
    @Test
    public void lookupTaggerTest()
        throws Exception
    {
        AnalyzedCorpus corpus = CorpusManager.getCorpus(CorpusName.MobyDick);
        Sentence sentence = CorpusManager.getSentence(CorpusName.MobyDick);      
        
        ToolboxTagger ngramTagger = new NGramTagger(
                Tagset.penntreebank,
                corpus.getSentences(),
                1
        );
                
        for (TaggedToken taggedToken : ngramTagger.tag(sentence.getTokens())) {
            System.out.println(taggedToken);
        }
    }
}
