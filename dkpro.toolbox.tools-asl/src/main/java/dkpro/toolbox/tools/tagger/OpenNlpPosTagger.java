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
package dkpro.toolbox.tools.tagger;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.TaggedToken;
import dkpro.toolbox.core.ToolboxException;

public class OpenNlpPosTagger
    extends ToolboxTaggerBase
{

    private final AnalysisEngine segmenter;
    private final AnalysisEngine tagger;
    
    private String language;


    public OpenNlpPosTagger(String language)
        throws Exception
   {
        this.language = language;
        segmenter = createEngine(BreakIteratorSegmenter.class);
        tagger = createEngine(de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger.class);
    }

    @Override
    public Collection<TaggedToken> tag(String text)
        throws ToolboxException
    {
        List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();

        JCas jcas;
        try {
            jcas = tagger.newJCas();
            jcas.setDocumentLanguage(language);
            jcas.setDocumentText(text);
            segmenter.process(jcas);
            tagger.process(jcas);

            for (Token t : select(jcas, Token.class)) {
                String token = t.getCoveredText();
                Tag tag = new Tag(
                        t.getPos().getPosValue(),
                        language
                );
                taggedTokens.add(new TaggedToken(token, tag));
            }
        }
        catch (ResourceInitializationException e) {
            throw new ToolboxException(e);
        }
        catch (AnalysisEngineProcessException e) {
            throw new ToolboxException(e);
        }

        return taggedTokens;
    }

    @Override
    public Collection<TaggedToken> tag(List<String> tokens)
        throws ToolboxException
    {
        return tag(StringUtils.join(tokens, " "));
    }
}