package dkpro.toolbox.tagger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;
import dkpro.toolbox.core.util.CFD;
import dkpro.toolbox.core.util.FD;
import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;

public class LookupTagger
    extends ToolboxTaggerBase
{

    private CFD<String, String> wordTagCfd;
    private Tagset tagset;
    
    public LookupTagger(Corpus corpus, int useTopN)
        throws CorpusException
    {
        this.tagset = corpus.getTagset();
        
        FD<String> tokenFd = new FD<String>();
        for (String token : corpus.getTokens()) {
            tokenFd.inc(token);
        }
        Set<String> topN = new HashSet<String>(tokenFd.getMostFrequentSamples(useTopN));
        
        this.wordTagCfd = new CFD<String, String>();
        for (TaggedToken taggedToken : corpus.getTaggedTokens()) {
            if (topN.contains(taggedToken.getToken())) {
                wordTagCfd.inc(taggedToken.getToken(), taggedToken.getTag().getOriginalTag());
            }
        }
    }

    @Override
    public Collection<TaggedToken> tag(List<String> tokens)
        throws ToolboxException
    {
        List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();     
        for (String token : tokens) {
            taggedTokens.add(new TaggedToken(token, new Tag(getMostLikelyTag(token), tagset)));
        }
        
        return taggedTokens;
    }

    private String getMostLikelyTag(String token) {
        String tag = UNKNOWN_TAG;
        if (wordTagCfd.getConditions().contains(token)) {
            tag = wordTagCfd.getFrequencyDistribution(token).getMostFrequentSamples(1).get(0);
        }
        return tag;
    }
}