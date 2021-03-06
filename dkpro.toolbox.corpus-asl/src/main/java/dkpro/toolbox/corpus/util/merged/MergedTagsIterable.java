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
package dkpro.toolbox.corpus.util.merged;

import java.util.Queue;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.ToolboxException;
import dkpro.toolbox.corpus.Corpus;
import dkpro.toolbox.corpus.CorpusException;

public class MergedTagsIterable
    extends MergedCorpusIterableBase<Tag>
{

    public MergedTagsIterable(Queue<Corpus> corpora)
    {
        super(corpora);
    }
    
    public MergedTagsIterable(Queue<Corpus> corpora, int maxItems)
    {
        super(corpora, maxItems);
    }

    @Override
    protected void fillQueue(Queue<Tag> items)
        throws ToolboxException
    {
        if (!corpora.isEmpty()) {
            Corpus corpus = corpora.poll();

            try {
                for (Tag tag : corpus.getTags()) {
                    items.add(tag);
                }
            }
            catch (CorpusException e) {
                throw new ToolboxException(e);
            }
        }
    }
}