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
package dkpro.toolbox.corpus;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;

public class TextCorpusTest
{

    @SuppressWarnings("unused")
    @Test
    public void textCorpusTest() throws Exception {
        Corpus corpus = new TextCorpus(
                "src/test/resources/test_corpora/text/",
                "en",
                "Moby Dick - Melville",
                "Moby Dick by Herman Melville 1851",
                "melville-moby_dick.txt"
        );
  
        int nrOfTexts = 0;
        for (Text text : corpus.getTexts()) {
            nrOfTexts++;
        }
        assertEquals(1, nrOfTexts);
        
        int nrOfSentences = 0;
        for (Sentence sentence : corpus.getSentences()) {
            nrOfSentences++;
        }
        assertEquals(10187, nrOfSentences);

        int nrOfTokens = 0;
        for (String token : corpus.getTokens()) {
            nrOfTokens++;
        }
        assertEquals(254996, nrOfTokens);

        int nrOfTags = 0;
        for (Tag tag : corpus.getTags()) {
            // System.out.println(tag);
            nrOfTags++;
        }
        assertEquals(254996, nrOfTags);
        
        int nrOfTaggedTokens = 0;
        for (TaggedToken tt : corpus.getTaggedTokens()) {
            // System.out.println(tt);
            nrOfTaggedTokens++;
        }
        assertEquals(254996, nrOfTaggedTokens);
    }
}