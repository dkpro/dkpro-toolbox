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

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasReader;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;


/**
 * A corpus that does not come in a special format, but consists of text files.
 *
 * @author zesch
 *
 */
public class TextCorpus
    extends CorpusBase
{

    protected CollectionReaderDescription reader;
    private String language;
    private String name;

    public TextCorpus(String corpusPath, String language, String name, String ... patterns) throws Exception
    {
        this.language = language;
        this.name = name;
     
        CollectionReaderDescription sourceReader = CollectionReaderFactory.createReaderDescription(
                TextReader.class,
                TextReader.PARAM_LANGUAGE, language,
                TextReader.PARAM_SOURCE_LOCATION, corpusPath,
                TextReader.PARAM_PATTERNS, patterns
        );
           
        // preprocess text file (tokenize, sentence split, POS tag) and serialize
        AnalysisEngineDescription desc = AnalysisEngineFactory.createEngineDescription(
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class),
                AnalysisEngineFactory.createEngineDescription(
                        BinaryCasWriter.class,
                        BinaryCasWriter.PARAM_TARGET_LOCATION, "target/textcorpus/"
                )
        );
        SimplePipeline.runPipeline(sourceReader, desc);
                
        // read serialized data
        reader = CollectionReaderFactory.createReaderDescription(
                BinaryCasReader.class,
                BinaryCasReader.PARAM_SOURCE_LOCATION, "target/textcorpus/",
                BinaryCasReader.PARAM_PATTERNS, "*.bin"
        );
    }

    @Override
    protected CollectionReaderDescription getReader()
    {
        return reader;
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
}