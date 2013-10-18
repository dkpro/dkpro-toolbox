package dkpro.toolbox.tools.chart;

import org.jfree.ui.RefineryUtilities;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;
import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.corpus.analyzedcorpora.AnalyzedCorpus;
import dkpro.toolbox.corpus.analyzedcorpora.CorpusManager;
import dkpro.toolbox.corpus.analyzedcorpora.CorpusManager.CorpusName;
import dkpro.toolbox.corpus.categorized.CategorizedCorpus;
import dkpro.toolbox.corpus.categorized.FirstNamesCorpus;

public class ChartUtil
{
    public static void main(String[] args)
        throws Exception
    {
        CategorizedCorpus corpus = new FirstNamesCorpus();
        ConditionalFrequencyDistribution<String, String> cfd = new ConditionalFrequencyDistribution<String, String>();
        for (String category : corpus.getCategories()) {
            for (String token : corpus.getTokens(category)) {
                cfd.addSample(category, token.substring(token.length()-1, token.length()));
            }
        }
        
        ChartUtil.getChart(cfd);
        
        
        AnalyzedCorpus corpus2 = CorpusManager.getCorpus(CorpusName.MobyDick);
        FrequencyDistribution<String> fd = new FrequencyDistribution<String>();
        for (TaggedToken taggedToken : corpus2.getTaggedTokens()) {
            fd.inc(taggedToken.getTag().getSimplifiedTag());
        }
        
        ChartUtil.getChart(fd);
        ChartUtil.getChart(fd, true);

    }

    public static void getChart(FrequencyDistribution<String> fd) {
        getChart(fd, false);
    }
    
    public static void getChart(FrequencyDistribution<String> fd, boolean cumulative) {
        FdChart chart = new FdChart("test", fd, cumulative);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
    
    public static void getChart(ConditionalFrequencyDistribution<String, String> cfd) {
        CfdChart chart = new CfdChart("test", cfd);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
    
    public static void safeAsPdf() {
        
    }
    
    public static void safeAsSvg() {
        
    }

}
