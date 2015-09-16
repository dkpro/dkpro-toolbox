/*******************************************************************************
 * Copyright 2011
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package dkpro.toolbox.corpus;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import dkpro.toolbox.corpus.analyzed.AnalyzedCorpus;
import dkpro.toolbox.corpus.analyzed.CorpusManager;
import dkpro.toolbox.corpus.analyzed.CorpusManager.CorpusName;

public class MergedCorpusTest
{
    @SuppressWarnings("unused")
    @Test
    public void mergedCorpusTest() throws Exception {
        AnalyzedCorpus corpus1 = CorpusManager.getCorpus(CorpusName.FirstNames);
        AnalyzedCorpus corpus2 = CorpusManager.getCorpus(CorpusName.MobyDick);

        MergedCorpus merged = new MergedCorpus(corpus1.getUnderlyingCorpus(), corpus2.getUnderlyingCorpus());

        int nrOfTokens = 0;
        for (String token : merged.getTokens()) {
//          System.out.println(token);
            nrOfTokens++;
        }
        assertEquals(262943, nrOfTokens);
    }

    @SuppressWarnings("unused")
    @Test
    public void mergedCorpusLimitedTest() throws Exception {
        AnalyzedCorpus corpus1 = CorpusManager.getCorpus(CorpusName.FirstNames);
        AnalyzedCorpus corpus2 = CorpusManager.getCorpus(CorpusName.MobyDick);

        MergedCorpus merged = new MergedCorpus(corpus1.getUnderlyingCorpus(), corpus2.getUnderlyingCorpus());

        int nrOfTokens = 0;
        for (String token : merged.getTokens(10)) {
//            System.out.println(token);
            nrOfTokens++;
        }
        assertEquals(10, nrOfTokens);
    }

    @Ignore
    @Test
    public void mergedCorpusRereadTest() throws Exception {
        AnalyzedCorpus corpus1 = CorpusManager.getCorpus(CorpusName.FirstNames);
        AnalyzedCorpus corpus2 = CorpusManager.getCorpus(CorpusName.MobyDick);

        MergedCorpus merged = new MergedCorpus(corpus1.getUnderlyingCorpus(), corpus2.getUnderlyingCorpus());

        int nrOfTokens = 0;
        for (String token : merged.getTokens(10)) {
            System.out.println(token);
            nrOfTokens++;
        }
        assertEquals(10, nrOfTokens);

        for (String token : merged.getTokens(10)) {
            System.out.println(token);
            nrOfTokens++;
        }
        assertEquals(20, nrOfTokens);

        for (String token : merged.getTokens(10)) {
            System.out.println(token);
            nrOfTokens++;
        }
        assertEquals(30, nrOfTokens);
    }
}
