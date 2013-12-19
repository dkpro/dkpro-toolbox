package dkpro.toolbox.corpus.analyzed;

import java.util.HashMap;
import java.util.Map;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.corpus.SerializedCorpus;
import dkpro.toolbox.corpus.categorized.InauguralCorpus;

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
                addSentence(CorpusName.MobyDick);
                break;
            case SenseAndSensibility : corpora.put(CorpusName.SenseAndSensibility, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/sense_sensibility/")));
                addSentence(CorpusName.SenseAndSensibility);
                break;
            case TheManWhoWasThursday: corpora.put(CorpusName.TheManWhoWasThursday, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/thursday/")));
                addSentence(CorpusName.TheManWhoWasThursday);
                break;
            case InauguralAddresses: corpora.put(CorpusName.InauguralAddresses, new AnalyzedCorpus(new InauguralCorpus()));
                addSentence(CorpusName.InauguralAddresses);
                break;
            case BookOfGenesis: corpora.put(CorpusName.BookOfGenesis, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/genesis/")));
                addSentence(CorpusName.BookOfGenesis);
                break;
            case PersonalsCorpus: corpora.put(CorpusName.PersonalsCorpus, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/personals/")));
                addSentence(CorpusName.PersonalsCorpus);
                break;
            case ChatCorpus: corpora.put(CorpusName.ChatCorpus, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/chat/")));
                addSentence(CorpusName.ChatCorpus);
               break;
            case FirstNames: corpora.put(CorpusName.FirstNames, new AnalyzedCorpus(new SerializedCorpus("classpath:/corpus/names/")));
                addSentence(CorpusName.FirstNames);
                break;
            default : throw new CorpusException("Unknown corpus: " + name); 
        }
    }
    
    private static void addSentence(CorpusName name)
            throws CorpusException
    {
        sentences.put(name, corpora.get(name).getSentenceList().get(0));
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