/*******************************************************************************
 * Copyright 2013
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
package dkpro.toolbox.corpus.specialcorpora;

import org.junit.Test;

import dkpro.toolbox.corpus.special.ConllCorpus;

public class ConllCorpusTest
{

    @Test
    public void conllTest() throws Exception {
        ConllCorpus corpus = new ConllCorpus();
        
        int i=0;
        for (String token : corpus.getTokens()) {
            if (i<20) {
                System.out.println(token);
            }
            i++;
        }
        int j=0;
        for (String chunk : corpus.getChunks()) {
            if (j<20) {
                System.out.println(chunk);
            }
            j++;
        }
    }
}