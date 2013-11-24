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

import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;

public interface Corpus {

	public Iterable<String> getTokens() throws CorpusException;
    public Iterable<String> getTokens(int maxItems) throws CorpusException;

	public Iterable<TaggedToken> getTaggedTokens() throws CorpusException;
    public Iterable<TaggedToken> getTaggedTokens(int maxItems) throws CorpusException;

	public Iterable<Sentence> getSentences() throws CorpusException;
    public Iterable<Sentence> getSentences(int maxItems) throws CorpusException;

    public Iterable<Tag> getTags() throws CorpusException;
    public Iterable<Tag> getTags(int maxItems) throws CorpusException;

    public Iterable<Text> getTexts() throws CorpusException;

    public Tagset getTagset() throws CorpusException;

    /**
     * @return The language code of the corpus language.
     */
    public String getLanguage();
    
    /**
     * @return The name of the corpus.
     */
    public String getName();
    
    /**
     * @return A short description of the corpus.
     */
    public String getDescription();
}