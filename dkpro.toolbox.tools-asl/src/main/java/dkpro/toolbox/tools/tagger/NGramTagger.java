package dkpro.toolbox.tools.tagger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;
import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;
import dkpro.toolbox.corpus.CorpusException;
import dkpro.toolbox.tools.tagger.util.LimitedQueue;

public class NGramTagger
    extends ToolboxTaggerBase
{
    public static final String BOS = "<BOS>";

    private ConditionalFrequencyDistribution<String, String> cfd;
    
    private int ngramSize;
    
    public NGramTagger(Iterable<Sentence> sentences, int ngramSize)
        throws CorpusException
    {
        this.ngramSize = ngramSize;
        
        LimitedQueue<String> context = initializeContext();
        
        this.cfd = new ConditionalFrequencyDistribution<String, String>();
        for (Sentence sentence : sentences) {
            for (TaggedToken taggedToken : sentence.getTaggedTokens()) {
                // construct key from context tags and actual word
                String tagKey = StringUtils.join(context, "+") + "+" + taggedToken.getToken();
                String tag = taggedToken.getTag().getOriginalTag();
                cfd.inc(tagKey, tag);
                context.add(tag);
            }
        }
    }

    @Override
    public Collection<TaggedToken> tag(List<String> tokens)
        throws ToolboxException
    {
        LimitedQueue<String> context = initializeContext();
        
        List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();     
        for (String token : tokens) {
            String predictedString = getTag(context, token);
            Tag tag = new Tag(predictedString, "en");
            taggedTokens.add(new TaggedToken(token, tag));
            context.add(tag.getOriginalTag());
        }
        
        return taggedTokens;
    }

    private String getTag(LimitedQueue<String> context, String token) {
        String key = getCfdKey(context, token);
        
        String tag = "UNK";
        if (cfd.hasCondition(key)) {
            tag = cfd.getFrequencyDistribution(key).getMostFrequentSamples(1).get(0);
        }
        return tag;
    }
    
    private String getCfdKey(LimitedQueue<String> context, String token) {
        return StringUtils.join(context, "+") + "+" + token;
    }
    
    private LimitedQueue<String> initializeContext() {
        // get the left context, i.e. the joined tags
        LimitedQueue<String> context = new LimitedQueue<String>(ngramSize-1);    
        // initialize with BOS
        for (int i=0; i<ngramSize-1; i++) {
            context.add(BOS);
        }
        return context;
    }
}