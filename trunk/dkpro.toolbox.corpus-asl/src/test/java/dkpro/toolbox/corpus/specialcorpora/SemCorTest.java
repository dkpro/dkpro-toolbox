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

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.corpus.special.SemCor;

public class SemCorTest
{

    @Test
    public void semcorTest() throws Exception {
        SemCor semCor = new SemCor();
        
        int i=0;
        for (Sentence sentence : semCor.getSenseAnnotatedSentences()) {
            if (i==0) {
                System.out.println(sentence);
                break;
            }
        }
    }
}