package dkpro.toolbox.corpus.raw;

import java.util.HashMap;
import java.util.Map;

import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;

public class TextManager
{
    public enum TextName {
        MobyDick,
        SenseAndSensibility,
        BookOfGenesis,
        InauguralAddresses,
        ChatText,
        WallStreetJournal,
        Personals,
        TheManWhoWasThursday
    }
    
    private static Map<TextName, RawText> texts = null;
    
    private static void initialize()
    {
        texts = new HashMap<TextName, RawText>();
    }
    
    private static void load(TextName name)
            throws CorpusException
    {
        switch (name) {
            case MobyDick : texts.put(TextName.MobyDick, new CorpusBackedRawText(new SerializedCorpus("classpath:/corpus/mobydick/")));
            case SenseAndSensibility : texts.put(TextName.SenseAndSensibility, new CorpusBackedRawText(new SerializedCorpus("classpath:/corpus/sense_sensibility/")));
            case TheManWhoWasThursday: texts.put(TextName.TheManWhoWasThursday, new CorpusBackedRawText(new SerializedCorpus("classpath:/corpus/thursday/")));
            case InauguralAddresses: texts.put(TextName.InauguralAddresses, new CorpusBackedRawText(new SerializedCorpus("classpath:/corpus/inaugural/")));
            case BookOfGenesis: texts.put(TextName.BookOfGenesis, new CorpusBackedRawText(new SerializedCorpus("classpath:/corpus/genesis/")));
            case Personals: texts.put(TextName.Personals, new CorpusBackedRawText(new SerializedCorpus("classpath:/corpus/personals/")));
            case ChatText: texts.put(TextName.ChatText, new CorpusBackedRawText(new SerializedCorpus("classpath:/corpus/chat/")));
            default : throw new CorpusException("Unknown text: " + name); 
        }
    }
    
    public static RawText getText(TextName textName)
            throws CorpusException
    {
        if (texts == null) {
            initialize();
        }
        
        if (!texts.containsKey(textName)) {
            load(textName);
        }
        
        return texts.get(textName);
    }
}
