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

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase;
import de.tudarmstadt.ukp.dkpro.core.api.resources.DkproContext;
import de.tudarmstadt.ukp.dkpro.core.io.imscwb.ImsCwbReader;
import dkpro.toolbox.core.Tag.Tagset;

/**
 * A wrapper for the WaCky large-scale Web corpora. It searches a DKPRO_HOME
 * workspace.
 *
 * Language editions are assumed to be gzipped and folders shold be
 * named according to the enum {@link WackyLanguageEdition}.
 *
 * @author zesch
 *
 */
public class WackyCorpus
    extends CorpusBase
{
    private static final String DESCRIPTION = "The Wacky corpus.";

    public enum WackyLanguageEdition {
        // FIXME are those really the right tagsets for the corpora and isn't there a better method to provide this?
        DEWAC("de", "stts"),
        UKWAC("en", "ptb");

        private String language;
        private String tagSet;

        private WackyLanguageEdition(String aLanguage, String aTagSet)
		{
        	language = aLanguage;
        	tagSet = aTagSet;
		}

        public String getTagSet()
		{
			return tagSet;
		}

        public String getLanguage()
		{
			return language;
		}
    }

    private static final String WORKSPACE = "wacky";

    private WackyLanguageEdition edition;
    private CollectionReaderDescription reader;

    public WackyCorpus(WackyLanguageEdition languageEdition) throws Exception
    {
        String wackyPath = DkproContext.getContext().getWorkspace(WORKSPACE).getAbsolutePath() + "/"
            + languageEdition.name();
        initialize(wackyPath, languageEdition);
    }

    public WackyCorpus(String wackyPath, WackyLanguageEdition languageEdition) throws Exception
    {
        initialize(wackyPath, languageEdition);
    }

    private void initialize(String wackyPath, WackyLanguageEdition languageEdition) throws Exception {
        reader = CollectionReaderFactory.createReaderDescription(
                ImsCwbReader.class,
                ImsCwbReader.PARAM_SOURCE_LOCATION, wackyPath,
                ImsCwbReader.PARAM_LANGUAGE, languageEdition.name(),
                ImsCwbReader.PARAM_ENCODING, "ISO-8859-15",
                ImsCwbReader.PARAM_POS_TAG_SET, languageEdition.getTagSet(),
                ImsCwbReader.PARAM_PATTERNS, new String[] {
                    ResourceCollectionReaderBase.INCLUDE_PREFIX + "*.txt.gz"
                }
        );

        edition = languageEdition;
    }

    @Override
    public String getLanguage()
    {
    	return edition.getLanguage();
    }

    @Override
    public String getName()
    {
        return this.edition.toString();
    }

    @Override
    protected CollectionReaderDescription getReader()
    {
        return reader;
    }
    
    @Override
    public Tagset getTagset()
    {
        return Tagset.penntreebank;
    }

    @Override
    public String getDescription()
    {
        return DESCRIPTION;
    }
}