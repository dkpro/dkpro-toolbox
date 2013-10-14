package dkpro.toolbox.tools.chart;

import org.jfree.ui.RefineryUtilities;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;
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
                cfd.inc(category, token.substring(token.length()-1, token.length()));
            }
        }
        
        ChartUtil.getChart(cfd);
    }

    public static void getChart(ConditionalFrequencyDistribution<String, String> cfd) {
        FrequencyChart chart = new FrequencyChart("test", cfd);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
    
    public static void safeAsPdf() {
        
    }
    
    public static void safeAsSvg() {
        
    }

}
