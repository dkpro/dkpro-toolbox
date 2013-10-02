package dkpro.toolbox.corpus.analyzedtext;

import java.util.HashMap;
import java.util.Map;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;

public class TextManager
{
    public enum TextName {
        MobyDick,
        SenseAndSensibility,
        BookOfGenesis,
        InauguralAddresses,
        ChatCorpus,
        MontyPython,
        WallStreetJournal,
        PersonalsCorpus,
        TheManWhoWasThursday
    }
    
    private static Map<TextName, AnalyzedText> texts = null;
    private static Map<TextName, Sentence> sentences = null;
    
    private static void initialize()
    {
        texts = new HashMap<TextName, AnalyzedText>();
        sentences = new HashMap<TextName, Sentence>();     
    }
    
    private static void load(TextName name)
            throws CorpusException
    {
        switch (name) {
            case MobyDick : texts.put(TextName.MobyDick, new AnalyzedText(new SerializedCorpus("classpath:/corpus/mobydick/")));
            case SenseAndSensibility : texts.put(TextName.SenseAndSensibility, new AnalyzedText(new SerializedCorpus("classpath:/corpus/sense_sensibility/")));
            case TheManWhoWasThursday: texts.put(TextName.TheManWhoWasThursday, new AnalyzedText(new SerializedCorpus("classpath:/corpus/thursday/")));
            case InauguralAddresses: texts.put(TextName.InauguralAddresses, new AnalyzedText(new SerializedCorpus("classpath:/corpus/inaugural/")));
        }
    }
    
    public static AnalyzedText getText(TextName name)
            throws CorpusException
    {
        if (texts == null) {
            initialize();
        }
        
        if (!texts.containsKey(name)) {
            load(name);
        }
        
        return texts.get(name);
    }

    public static Sentence getSentence(TextName name)
            throws CorpusException
    {
        if (sentences == null) {
            initialize();
        }

        if (!texts.containsKey(name)) {
            load(name);
        }
        
        return sentences.get(name);
    }
}