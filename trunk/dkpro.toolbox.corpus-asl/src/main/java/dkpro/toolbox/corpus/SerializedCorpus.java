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

import java.io.IOException;
import java.util.Properties;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasReader;
import dkpro.toolbox.core.Tag.Tagset;


/**
 * A corpus that has previously been serialized as a binary CAS.
 *
 * @author zesch
 *
 */
public class SerializedCorpus
    extends CorpusBase
{

    protected CollectionReaderDescription reader;
    private Tagset tagset;
    private String language;
    private String name;
    private String description;

    public SerializedCorpus(String corpusPath)
            throws CorpusException
    {
        this(corpusPath, "*.bin");
    }
    
    public SerializedCorpus(String corpusPath, String ... patterns)
            throws CorpusException
    {
        // read serialized data
        try {
            reader = CollectionReaderFactory.createReaderDescription(
                    BinaryCasReader.class,
                    BinaryCasReader.PARAM_SOURCE_LOCATION, corpusPath,
                    BinaryCasReader.PARAM_PATTERNS, patterns
            );
        }
        catch (ResourceInitializationException e) {
            throw new CorpusException(e);
        }
        
        Properties prop = new Properties();
        
        try {
            //load a properties file
            prop.load(ResourceUtils.resolveLocation(corpusPath + "/metadata.prop").openStream());
 
            name = prop.getProperty("name");
            language = prop.getProperty("language");
            description = prop.getProperty("description");
            tagset = Tagset.valueOf(prop.getProperty("tagset"));
        } catch (IOException ex) {
            ex.printStackTrace();
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
        return tagset;
    }

    @Override
    public String getLanguage()
    {
        return language;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }
}