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
package dkpro.toolbox.tutorial;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
import dkpro.toolbox.corpus.BrownTeiCorpus;
import dkpro.toolbox.corpus.Corpus;

public class CorpusUsage
{

    public static void main(String[] args) throws Exception
    {
        Corpus brownCorpus = new BrownTeiCorpus();
        
        for (Text t : brownCorpus.getTexts()) {
            System.out.println(t);
        }

        for (Sentence s : brownCorpus.getSentences()) {
        	System.out.println(s);
        }
        
        for (String token : brownCorpus.getTokens()) {
            System.out.println(token);
        }

        for (Tag pos : brownCorpus.getTags()) {
            System.out.println(pos);
        }
        
        for (TaggedToken tt : brownCorpus.getTaggedTokens()) {
            System.out.println(tt);
        }

    }

}