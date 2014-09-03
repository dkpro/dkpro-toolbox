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

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;

import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import dkpro.toolbox.core.Tag.Tagset;


/**
 * A corpus that does not come in a special format, but consists of text files.
 *
 * @author zesch
 *
 */
public class TextCorpus
    extends CorpusBase
{

    private DkproCorpus corpus;

    public TextCorpus(
            String corpusPath,
            Tagset tagset,
            String language,
            String name,
            String description,
            String ... patterns
    )
            throws Exception
    {   
        CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(
                TextReader.class,
                TextReader.PARAM_LANGUAGE, language,
                TextReader.PARAM_SOURCE_LOCATION, corpusPath,
                TextReader.PARAM_PATTERNS, patterns
        );
        
        corpus = new DkproCorpus(language, tagset, name, description, reader);
         
    }

    @Override
    protected CollectionReaderDescription getReader()
    {
        return corpus.getReader();
    }

    @Override
    public Tagset getTagset()
    {
        return corpus.getTagset();
    }
    
    @Override
    public String getLanguage()
    {
        return corpus.getLanguage();
    }

    @Override
    public String getName()
    {
        return corpus.getName();
    }

    @Override
    public String getDescription()
    {
        return corpus.getDescription();
    }
}