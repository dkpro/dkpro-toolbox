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

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasReader;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import dkpro.toolbox.core.Tag.Tagset;


/**
 * A corpus that is backed by a DKPro reader which is provided in the constructor.
 *
 * @author zesch
 *
 */
public class DkproCorpus
    extends CorpusBase
{

    protected CollectionReaderDescription serializedReader;
    protected Tagset tagset;
    protected String language;
    protected String name;
    protected String description;
    
    private CollectionReaderDescription sourceReader;

    public DkproCorpus(
            String language,
            Tagset tagset,
            String name,
            String description,
            CollectionReaderDescription reader
    )
            throws CorpusException
    {
        this.tagset = tagset;
        this.language = language;
        this.name = name;
        this.description = description;
        this.sourceReader = reader;
          
        // TODO should not serialize to target but into temp directory
        
            // preprocess text file (tokenize, sentence split, POS tag) and serialize
            try {
                AnalysisEngineDescription desc = AnalysisEngineFactory.createEngineDescription(
                        AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                        AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class),
                        AnalysisEngineFactory.createEngineDescription(
                                BinaryCasWriter.class,
                                BinaryCasWriter.PARAM_TARGET_LOCATION, "target/textcorpus/" + name
                        )
                );
                
                SimplePipeline.runPipeline(sourceReader, desc);
                
                // read serialized data
                serializedReader = CollectionReaderFactory.createReaderDescription(
                        BinaryCasReader.class,
                        BinaryCasReader.PARAM_SOURCE_LOCATION, "target/textcorpus/" + name,
                        BinaryCasReader.PARAM_PATTERNS, "*.bin"
                );
            }
            catch (ResourceInitializationException e) {
                throw new CorpusException(e);
            }
            catch (UIMAException e) {
                throw new CorpusException(e);
            }
            catch (IOException e) {
                throw new CorpusException(e);
            }
    }

    @Override
    protected CollectionReaderDescription getReader()
    {
        return serializedReader;
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