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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.JCasIterable;

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
    implements CachableCorpus
{
    private boolean useCaching;
    
    // there are not used unless useCaching is explicitly enabled
    // caching might require a lot of RAM
    private List<String> tokens;
    private List<Sentence> sentences;
    private List<Tag> tags;
    private List<TaggedToken> taggedTokens;
    private List<Text> texts;

    @Override
    public abstract String getLanguage();

    @Override
    public abstract String getName();

    @Override
    public abstract String getDescription();

    protected abstract CollectionReaderDescription getReader();

    @Override
    public Iterable<Sentence> getSentences()
        throws CorpusException
    {
        if (useCaching) {
            if (sentences == null) {
                sentences = new ArrayList<Sentence>();
                for (Sentence sentence : new SentenceIterable(new JCasIterable(getReader()).iterator(), getLanguage())) {
                    sentences.add(sentence);
                }
            }
            return sentences;
        }
        else {
            return new SentenceIterable(new JCasIterable(getReader()).iterator(), getLanguage());
        }
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens()
        throws CorpusException
    {
        if (useCaching) {
            if (taggedTokens == null) {
                taggedTokens = new ArrayList<TaggedToken>();
                for (TaggedToken taggedToken : new TaggedTokenIterable(new JCasIterable(getReader()).iterator(), getLanguage())) {
                    taggedTokens.add(taggedToken);
                }
            }
            return taggedTokens;
        }
        else {
            return new TaggedTokenIterable(new JCasIterable(getReader()).iterator(), getLanguage());
        }
    }

    @Override
    public Iterable<Tag> getTags()
        throws CorpusException
    {
        if (useCaching) {
            if (tags == null) {
                tags = new ArrayList<Tag>();
                for (Tag tag : new TagIterable(new JCasIterable(getReader()).iterator(), getLanguage())) {
                    tags.add(tag);
                }
            }
            return tags;
        }
        else {
            return new TagIterable(new JCasIterable(getReader()).iterator(), getLanguage());
        }
    }

    @Override
    public Iterable<Text> getTexts()
        throws CorpusException
    {
        if (useCaching) {
            if (texts == null) {
                texts = new ArrayList<Text>();
                for (Text text : new TextIterable(new JCasIterable(getReader()).iterator(), getLanguage())) {
                    texts.add(text);
                }
            }
            return texts;
        }
        else {
            return new TextIterable(new JCasIterable(getReader()).iterator(), getLanguage());
        }
    }

    @Override
    public Iterable<String> getTokens()
        throws CorpusException
    {
        if (useCaching) {
            if (tokens == null) {
                tokens = new ArrayList<String>();
                for (String token : new TokenIterable(new JCasIterable(getReader()).iterator(), getLanguage())) {
                    tokens.add(token);
                }
            }
            return tokens;
        }
        else {
            return new TokenIterable(new JCasIterable(getReader()).iterator(), getLanguage());
        }
    }

    @Override
    public void setUseCaching(boolean useCaching)
    {
        this.useCaching = useCaching;
    }
}