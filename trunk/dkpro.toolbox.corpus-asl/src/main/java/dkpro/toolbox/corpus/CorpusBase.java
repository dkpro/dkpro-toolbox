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

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
import dkpro.toolbox.corpus.util.SentenceIterable;
import dkpro.toolbox.corpus.util.TagIterable;
import dkpro.toolbox.corpus.util.TaggedTokenIterable;
import dkpro.toolbox.corpus.util.TextIterable;
import dkpro.toolbox.corpus.util.TokenIterable;

public abstract class CorpusBase
    implements Corpus
{

    @Override
    public abstract String getLanguage();

    @Override
    public abstract String getName();

    protected abstract CollectionReaderDescription getReader();

    @Override
    public Iterable<Sentence> getSentences()
        throws CorpusException
    {
        // reconfigure to re-initialize the reader
        try {
            CollectionReaderFactory.createReader(getReader()).reconfigure();
        }
        catch (ResourceConfigurationException e) {
            throw new CorpusException(e);
        }
        catch (ResourceInitializationException e) {
            throw new CorpusException(e);
        }
        return new SentenceIterable(new JCasIterable(getReader()).iterator(), getLanguage());
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens()
        throws CorpusException
    {
        // reconfigure to re-initialize the reader
//        getReader().reconfigure();
        return new TaggedTokenIterable(new JCasIterable(getReader()).iterator(), getLanguage());
    }

    @Override
    public Iterable<Tag> getTags()
        throws CorpusException
    {
        // reconfigure to re-initialize the reader
//        getReader().reconfigure();
        return new TagIterable(new JCasIterable(getReader()).iterator(), getLanguage());
    }

    @Override
    public Iterable<Text> getTexts()
        throws CorpusException
    {
        // reconfigure to re-initialize the reader
//        getReader().reconfigure();
        return new TextIterable(new JCasIterable(getReader()).iterator(), getLanguage());
    }

    @Override
    public Iterable<String> getTokens()
        throws CorpusException
    {
        // reconfigure to re-initialize the reader
//        getReader().reconfigure();
        return new TokenIterable(new JCasIterable(getReader()).iterator(), getLanguage());
    }
}