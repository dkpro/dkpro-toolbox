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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.uima.fit.pipeline.JCasIterator;

import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.ToolboxException;

public abstract class CorpusIterableBase
    <T> implements Iterable<T>
{
    protected final JCasIterator jcasIterator;
    protected final Tagset tagset;
    protected final int maxItems;
    private int itemCounter;

    public CorpusIterableBase(JCasIterator jcasIterator, Tagset tagset) {
        this(jcasIterator, tagset, Integer.MAX_VALUE);
    }
    
    public CorpusIterableBase(JCasIterator jcasIterator, Tagset tagset, int maxItems) {
        this.jcasIterator = jcasIterator;
        this.tagset = tagset;
        this.maxItems = maxItems;
        this.itemCounter = 0;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new CorpusItemIterator<T>();
    }

    protected abstract void fillQueue(JCasIterator jcasIterator, Queue<T> items)
            throws ToolboxException;

    private class CorpusItemIterator
        <A> implements Iterator<T>
    {

        Queue<T> items;

        public CorpusItemIterator() {
            items = new LinkedList<T>();
        }

        @Override
        public boolean hasNext()
        {
            // only output a configurable max amount of items
            if (itemCounter == maxItems) {
                return false;
            }
            
            if (!items.isEmpty()) {
                return true;
            }
            else {
                try {
                    fillQueue(jcasIterator, items);
                }
                catch (ToolboxException e) {
                    e.printStackTrace();
                }
            }

            if (!items.isEmpty()) {
                return true;
            }

            return false;
        }

        @Override
        public T next()
        {
            itemCounter++;
            return items.poll();
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}