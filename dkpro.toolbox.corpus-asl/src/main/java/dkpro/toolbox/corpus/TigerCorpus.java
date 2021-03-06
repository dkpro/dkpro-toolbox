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

import de.tudarmstadt.ukp.dkpro.core.api.resources.DkproContext;
import de.tudarmstadt.ukp.dkpro.core.io.negra.NegraExportReader;
import dkpro.toolbox.core.Tag.Tagset;


/**
 * Tiger Corpus
 *
 * @author zesch
 *
 */
public class TigerCorpus
    extends CorpusBase
{

    static final String LANGUAGE = "de";
    static final String NAME = "Tiger";
    static final String DESCRIPTION = "The Tiger corpus. A German newspaper corpus.";


    CollectionReaderDescription reader;

    public TigerCorpus() throws Exception
    {
        String tigerFile = DkproContext.getContext().getWorkspace("toolbox_corpora").getAbsolutePath() +
        "/tiger_export/tiger_release_dec05.export";

        initialize(tigerFile);
    }

    public TigerCorpus(String tigerFile) throws Exception
    {
        initialize(tigerFile);
    }

    private void initialize(String tigerFile) throws Exception {
        reader = CollectionReaderFactory.createReaderDescription(
                NegraExportReader.class,
                NegraExportReader.PARAM_SOURCE_LOCATION, tigerFile,
                NegraExportReader.PARAM_ENCODING, "ISO-8859-15",
                NegraExportReader.PARAM_LANGUAGE, LANGUAGE
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
        return LANGUAGE;
    }
    
    @Override
    public Tagset getTagset()
    {
        return Tagset.stts;
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