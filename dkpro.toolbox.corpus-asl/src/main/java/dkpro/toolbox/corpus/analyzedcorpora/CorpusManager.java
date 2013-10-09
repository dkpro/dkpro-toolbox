package dkpro.toolbox.corpus.analyzedcorpora;

import java.util.HashMap;
import java.util.Map;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;

public class CorpusManager
{
    public enum CorpusName {
        MobyDick,
        SenseAndSensibility,
        BookOfGenesis,
        InauguralAddresses,
        ChatCorpus,
        WallStreetJournal,
        PersonalsCorpus,
        TheManWhoWasThursday,
        FirstNames
    }
    
    private static Map<CorpusName, AnalyzedCorpus> corpora = null;
    private static Map<CorpusName, Sentence> sentences = null;
    
    private static void initialize()
    {
        corpora = new HashMap<CorpusName, AnalyzedCorpus>();
        sentences = new HashMap<CorpusName, Sentence>();     
    }
    
    private static void load(CorpusName name)
            throws CorpusException
    {
        switch (name) {
            case MobyDick : corpora.put(CorpusName.MobyDick, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/mobydick/")));
            case SenseAndSensibility : corpora.put(CorpusName.SenseAndSensibility, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/sense_sensibility/")));
            case TheManWhoWasThursday: corpora.put(CorpusName.TheManWhoWasThursday, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/thursday/")));
            case InauguralAddresses: corpora.put(CorpusName.InauguralAddresses, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/inaugural/")));
            case BookOfGenesis: corpora.put(CorpusName.BookOfGenesis, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/genesis/")));
            case PersonalsCorpus: corpora.put(CorpusName.PersonalsCorpus, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/personals/")));
            case ChatCorpus: corpora.put(CorpusName.ChatCorpus, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/chat/")));
            case FirstNames: corpora.put(CorpusName.FirstNames, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/names/")));
        }
    }
    
    public static AnalyzedCorpus getCorpus(CorpusName name)
            throws CorpusException
    {
        if (corpora == null) {
            initialize();
        }
        
        if (!corpora.containsKey(name)) {
            load(name);
        }
        
        return corpora.get(name);
    }

    public static Sentence getSentence(CorpusName name)
            throws CorpusException
    {
        if (sentences == null) {
            initialize();
        }

        if (!corpora.containsKey(name)) {
            load(name);
        }
        
        return sentences.get(name);
    }
}