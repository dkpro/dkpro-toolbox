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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.tudarmstadt.ukp.dkpro.core.api.resources.DkproContext;
import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.Text;
import dkpro.toolbox.corpus.util.TazSentenceIterable;

// FIXME does not work at the moment
/**
 * A wrapper for the Taz newspaper corpus. It searches a DKPRO_HOME workspace.
 * The actual data file is assumed to be gzipped.
 * 
 * @author zesch
 * 
 */
public class TazCorpus
    implements Corpus
{
    
    private final static String NAME = "taz";
    private final static String DESCRIPTION = "The TAZ corpus";
    private final static String LANG_CODE = "de";
    private final static String CHARSET = "UTF-8";
//    private final static String CHARSET = "ISO-8859-15";
    
    private final File tazPath;
    
    public TazCorpus() throws IOException
    {
        this("taz_corpus");
    }

    /**
     * @param workspace
     *            The DKPRO_HOME workspace
     */
    public TazCorpus(String workspace) throws IOException
    {
        tazPath = new File(
                DkproContext.getContext().getWorkspace(workspace).getAbsolutePath() +
                "/taz_corpus.txt.gz"
        );
    }

    @Override
    public Iterable<Sentence> getSentences()
        throws CorpusException
    {
        try {
            return new TazSentenceIterable(tazPath, CHARSET);
        }
        catch (FileNotFoundException e) {
            throw new CorpusException(e);
        }
        catch (IOException e) {
            throw new CorpusException(e);
        }
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens()
        throws CorpusException
    {
        // FIXME add implementation
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Tag> getTags()
        throws CorpusException
    {
        // FIXME add implementation
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<String> getTokens()
        throws CorpusException
    {
        // FIXME add implementation
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Text> getTexts()
        throws CorpusException
    {
        // FIXME add implementation
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String getLanguage()
    {
        return LANG_CODE;
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

    @Override
    public Tagset getTagset()
    {
        return Tagset.stts;
    }

    @Override
    public Iterable<String> getTokens(int maxItems)
        throws CorpusException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<TaggedToken> getTaggedTokens(int maxItems)
        throws CorpusException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Sentence> getSentences(int maxItems)
        throws CorpusException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Tag> getTags(int maxItems)
        throws CorpusException
    {
        // TODO Auto-generated method stub
        return null;
    }
}