package dkpro.toolbox.tools.tagger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;
import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.TagLevel;
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
    private TagLevel tagLevel;
    
    public NGramTagger(TagLevel tagLevel, Iterable<Sentence> sentences, int ngramSize)
        throws CorpusException
    {
        this.tagLevel = tagLevel;
        this.ngramSize = ngramSize;
        
        LimitedQueue<String> context = initializeContext();
        
        this.cfd = new ConditionalFrequencyDistribution<String, String>();
        for (Sentence sentence : sentences) {
            for (TaggedToken taggedToken : sentence.getTaggedTokens()) {
                // construct key from context tags and actual word
                String tagKey = StringUtils.join(context, "+") + "+" + taggedToken.getToken();
                String tag = "";
                if (tagLevel.equals(TagLevel.original)) {
                    tag = taggedToken.getTag().getOriginalTag();
                }
                else if (tagLevel.equals(TagLevel.canonical)) {
                    tag = taggedToken.getTag().getCanonicalTag();
                }
                else if (tagLevel.equals(TagLevel.simplified)) {
                    tag = taggedToken.getTag().getSimplifiedTag();
                }
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
       
        List<TaggedToken> backoffTaggedTokens = null; 
        if (backoffTagger != null) {
            backoffTaggedTokens = new ArrayList<TaggedToken>(backoffTagger.tag(tokens));
        }
        
        List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();  
        for (int i=0; i<tokens.size(); i++) {
            String token = tokens.get(i);
            
            // get prediction
            String predictedString = getTag(context, token);
            
            Tag tag = null;
        
            // if unknown tag is predicted, fall back to backoff tagger
            if (predictedString.equals(UNKNOWN_TAG) && backoffTagger != null) {
                if (tagLevel.equals(TagLevel.original)) {
                    predictedString = backoffTaggedTokens.get(i).getTag().getOriginalTag();
                    tag = new Tag(predictedString, null, null);
                    context.add(tag.getOriginalTag());
                }
                else if (tagLevel.equals(TagLevel.canonical)) {
                    predictedString = backoffTaggedTokens.get(i).getTag().getCanonicalTag();
                    tag = new Tag(null, predictedString, null);
                    context.add(tag.getCanonicalTag());
                }
                else if (tagLevel.equals(TagLevel.simplified)) {
                    predictedString = backoffTaggedTokens.get(i).getTag().getSimplifiedTag();
                    tag = new Tag(null, null, predictedString);
                    context.add(tag.getSimplifiedTag());
                }
            }
            else {
                if (tagLevel.equals(TagLevel.original)) {
                    tag = new Tag(predictedString, null, null);
                    context.add(tag.getOriginalTag());
                }
                else if (tagLevel.equals(TagLevel.canonical)) {
                    tag = new Tag(null, predictedString, null);
                    context.add(tag.getCanonicalTag());
                }
                else if (tagLevel.equals(TagLevel.simplified)) {
                    tag = new Tag(null, null, predictedString);
                    context.add(tag.getSimplifiedTag());
                }
            }
            taggedTokens.add(new TaggedToken(token, tag));
        }
        
        return taggedTokens;
    }

    private String getTag(LimitedQueue<String> context, String token) {
        String key = getCfdKey(context, token);
        
        String tag = UNKNOWN_TAG;
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

    public void setTagLevel(TagLevel tagLevel)
    {
        this.tagLevel = tagLevel;
    }
}