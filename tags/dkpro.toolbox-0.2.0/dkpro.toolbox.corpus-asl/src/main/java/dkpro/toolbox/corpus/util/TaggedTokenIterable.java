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

import java.util.Queue;

import org.apache.uima.fit.pipeline.JCasIterator;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public class TaggedTokenIterable
    extends CorpusIterableBase<TaggedToken>
{

    public TaggedTokenIterable(JCasIterator jcasIterator, Tagset tagset)
    {
        super(jcasIterator, tagset);
    }
    
    public TaggedTokenIterable(JCasIterator jcasIterator, Tagset tagset, int maxItems)
    {
        super(jcasIterator, tagset, maxItems);
    }

    @Override
    protected void fillQueue(JCasIterator jcasIterator, Queue<TaggedToken> items)
        throws ToolboxException
    {
        if (jcasIterator.hasNext()) {
            JCas jcas = jcasIterator.next();
            for (Token token : JCasUtil.select(jcas, Token.class)) {
                POS pos = token.getPos();
                items.add(new TaggedToken(
                        token.getCoveredText(),
                        ToolboxUtils.uimaPos2ToolboxTag(jcas, tagset, pos)
                ));
            }
        }
    }
}