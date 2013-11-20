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
package dkpro.toolbox.corpus.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.apache.uima.fit.pipeline.JCasIterator;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.wsd.type.Sense;
import de.tudarmstadt.ukp.dkpro.wsd.type.WSDResult;
import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.ToolboxException;

public class SenseAnnotatedSentenceIterable
    extends CorpusIterableBase<Sentence>
{

    public SenseAnnotatedSentenceIterable(JCasIterator jcasIterator, Tagset tagset)
    {
        super(jcasIterator, tagset);
    }
    
    public SenseAnnotatedSentenceIterable(JCasIterator jcasIterator, Tagset tagset, int maxItems)
    {
        super(jcasIterator, tagset, maxItems);
    }

    @Override
    protected void fillQueue(JCasIterator jcasIterator, Queue<Sentence> items)
        throws ToolboxException
    {
        if (jcasIterator.hasNext()) {
            JCas jcas = jcasIterator.next();
            for (de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence uimaSentence : JCasUtil.select(jcas, de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence.class)) {

                List<String> lemmas = new ArrayList<String>();
                for (Lemma lemma : JCasUtil.selectCovered(jcas, Lemma.class, uimaSentence)) {
                    lemmas.add(lemma.getValue());
                }
                List<Tag> tagList = new ArrayList<Tag>();
                for (POS pos : JCasUtil.selectCovered(jcas, POS.class, uimaSentence)) {
                    tagList.add(new Tag(pos.getPosValue(), tagset));
                }

                List<String> tokens = new ArrayList<String>();

                for (Token token : JCasUtil.selectCovered(jcas, Token.class, uimaSentence)) {                 
                    String tokenString = token.getCoveredText();
                    
                    List<WSDResult> wsdResults = JCasUtil.selectCovered(jcas, WSDResult.class, token);
                    if (wsdResults.size() > 0) {
                        FSArray senses = wsdResults.iterator().next().getSenses();
                        if (senses.size() > 0) {
                            // senseIds look like that: encounter%2:38:00::
                            // here the 00 part describes that this is the first sense (offset 00) 
                            // documented here: http://wordnet.princeton.edu/man/senseidx.5WN.html
                           
                            String senseId = ((Sense) senses.get(0)).getId();
                            String[] parts = senseId.split(":");
                            if (parts.length > 2) {
                                String lemma = parts[0].split("%")[0];
                                tokenString = lemma + "#" + parts[2];
                            }
                        }
                    }
                    
                    tokens.add(tokenString);
                }
                
                items.add(new Sentence(tokens, lemmas, tagList));
            }
        }
    }
}