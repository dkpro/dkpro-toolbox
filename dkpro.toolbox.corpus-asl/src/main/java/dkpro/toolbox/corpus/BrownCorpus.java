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
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.io.tei.TeiReader;
import dkpro.toolbox.core.Tag.Tagset;


/**
 * Brown Corpus
 * @see "http://clu.uni.no/icame/brown/bcm.html"
 *
 */
public class BrownCorpus
    extends CorpusBase
{
    static final String LANGUAGE = "en";
    static final String NAME = "Brown";
    static final String DESCRIPTION = "The Brown corpus";

    CollectionReaderDescription reader;

    public BrownCorpus() throws CorpusException
    {
        String brownPath = "classpath:/corpus/brown_tei/";
        initialize(brownPath);
    }

    public BrownCorpus(String brownPath) throws CorpusException
    {
        initialize(brownPath);
    }

    private void initialize(String brownPath) throws CorpusException {
        try {
            reader = CollectionReaderFactory.createReaderDescription(
                    TeiReader.class,
                    TeiReader.PARAM_LANGUAGE, LANGUAGE,
                    TeiReader.PARAM_SOURCE_LOCATION, brownPath,
                    TeiReader.PARAM_PATTERNS, "*.xml.gz"
            );
        }
        catch (ResourceInitializationException e) {
            throw new CorpusException(e);
        }
    }

    @Override
    protected CollectionReaderDescription getReader()
    {
        return reader;
    }

    @Override
    public Tagset getTagset()
    {
        return Tagset.brown;
    }
    
    @Override
    public String getLanguage()
    {
        return LANGUAGE;
    }

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public String getDescription()
    {
        return DESCRIPTION;
    }
}