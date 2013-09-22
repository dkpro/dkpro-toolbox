package dkpro.toolbox.corpus.analyzedtext;

import java.util.HashMap;
import java.util.Map;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;

public class TextManager
{
    public enum TextName {
        MobyDick
    }
    
    private static Map<TextName, AnalyzedText> texts = null;
    private static Map<TextName, Sentence> sentences = null;
    
    private static void initialize()
        throws CorpusException
    {
        texts = new HashMap<TextName, AnalyzedText>();
        sentences = new HashMap<TextName, Sentence>();
     
        // load texts
        System.out.println("Loading Moby Dick.");
        texts.put(TextName.MobyDick, new AnalyzedText(new SerializedCorpus("src/main/resources/corpus/mobydick/")));
    }
    
    public static AnalyzedText getText(TextName name)
            throws CorpusException
    {
        if (texts == null) {
            initialize();
        }
        
        return texts.get(name);
    }

    public static Sentence getSentence(TextName name)
            throws CorpusException
    {
        if (sentences == null) {
            initialize();
        }
        
        return sentences.get(name);
    }
}