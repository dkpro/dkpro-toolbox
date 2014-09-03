package dkpro.toolbox.tools.chart;

import org.junit.Ignore;
import org.junit.Test;

import dkpro.toolbox.core.util.CFD;
import dkpro.toolbox.core.util.FD;
import dkpro.toolbox.corpus.analyzed.AnalyzedCorpus;
import dkpro.toolbox.corpus.analyzed.CorpusManager;
import dkpro.toolbox.corpus.analyzed.CorpusManager.CorpusName;
import dkpro.toolbox.corpus.categorized.CategorizedCorpus;
import dkpro.toolbox.corpus.categorized.FirstNamesCorpus;

public class ChartUtilTest
{
    
    @Ignore
    @Test
    public void testPrintCfd() throws Exception {
        
        CategorizedCorpus corpus = new FirstNamesCorpus();
        CFD<String, String> cfd = new CFD<String, String>();
        for (String category : corpus.getCategories()) {
            for (String token : corpus.getTokens(category)) {
                cfd.inc(category, token.substring(token.length()-1, token.length()));
            }
        }
        
        ChartUtil.getChart(cfd);
    }
    
    @Ignore
    @Test
    public void testPrintFd() throws Exception {
        
        AnalyzedCorpus corpus = CorpusManager.getCorpus(CorpusName.MobyDick);
        FD<String> fd = new FD<String>();
        for (String token : corpus.getTokens()) {
            fd.inc(token);
        }
        
        ChartUtil.getChart(fd);
    }
}