package dkpro.toolbox.tagger;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag.TagLevel;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.corpus.analyzed.AnalyzedCorpus;
import dkpro.toolbox.corpus.analyzed.CorpusManager;
import dkpro.toolbox.corpus.analyzed.CorpusManager.CorpusName;
import dkpro.toolbox.tagger.util.ConfusionMatrix;

public class NGramTaggerTest
{
    @Test
    public void ngramTaggerTest()
        throws Exception
    {
        AnalyzedCorpus corpus = CorpusManager.getCorpus(CorpusName.MobyDick);
        Sentence sentence = CorpusManager.getSentence(CorpusName.MobyDick);      
        
        ToolboxTagger ngramTagger = new NGramTagger(
                TagLevel.original,
                corpus.getSentences(),
                1
        );
        
        List<String> predicted = new ArrayList<String>();
        for (TaggedToken taggedToken : ngramTagger.tag(sentence.getTokens())) {
            System.out.println(taggedToken);
            predicted.add(taggedToken.getTag().getOriginalTag());
        }

        ConfusionMatrix.print(sentence.getOriginalTags(), predicted);
    }
}
