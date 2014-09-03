package dkpro.toolbox.tools.chart;

import org.jfree.ui.RefineryUtilities;

import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.util.CFD;
import dkpro.toolbox.core.util.FD;
import dkpro.toolbox.corpus.analyzed.AnalyzedCorpus;
import dkpro.toolbox.corpus.analyzed.CorpusManager;
import dkpro.toolbox.corpus.analyzed.CorpusManager.CorpusName;
import dkpro.toolbox.corpus.categorized.CategorizedCorpus;
import dkpro.toolbox.corpus.categorized.FirstNamesCorpus;

public class ChartUtil
{
    public static void main(String[] args)
        throws Exception
    {
        CategorizedCorpus corpus = new FirstNamesCorpus();
        CFD<String, String> cfd = new CFD<String, String>();
        for (String category : corpus.getCategories()) {
            for (String token : corpus.getTokens(category)) {
            	cfd.inc(category, token.substring(token.length()-1, token.length()));
            }
        }
        
        ChartUtil.getChart(cfd);
        
        
        AnalyzedCorpus corpus2 = CorpusManager.getCorpus(CorpusName.MobyDick);
        FD<String> fd = new FD<String>();
        for (TaggedToken taggedToken : corpus2.getTaggedTokens()) {
            fd.inc(taggedToken.getTag().getSimplifiedTag());
        }
        
        ChartUtil.getChart(fd);
        ChartUtil.getChart(fd, true);

    }

    public static void getChart(FD<String> fd) {
        getChart(fd, false);
    }
    
    public static void getChart(FD<String> fd, boolean cumulative) {
        FdChart chart = new FdChart("test", fd, cumulative);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
    
    public static void getChart(CFD<String, String> cfd) {
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
