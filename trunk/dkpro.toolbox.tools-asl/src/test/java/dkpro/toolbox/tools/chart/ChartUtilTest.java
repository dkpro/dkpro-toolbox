package dkpro.toolbox.tools.chart;

import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;
import dkpro.toolbox.corpus.categorized.CategorizedCorpus;
import dkpro.toolbox.corpus.categorized.FirstNamesCorpus;

public class ChartUtilTest
{
    
    @Test
    public void testPrint() throws Exception {
        
        CategorizedCorpus corpus = new FirstNamesCorpus();
        ConditionalFrequencyDistribution<String, String> cfd = new ConditionalFrequencyDistribution<String, String>();
        for (String category : corpus.getCategories()) {
            for (String token : corpus.getTokens(category)) {
                cfd.inc(category, token.substring(token.length()-1, token.length()));
            }
        }
        
        ChartUtil.getChart(cfd);
    }
}
