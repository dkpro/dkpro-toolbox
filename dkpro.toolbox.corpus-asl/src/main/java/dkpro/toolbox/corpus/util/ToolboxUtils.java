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
package dkpro.toolbox.corpus.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import dkpro.toolbox.core.Sentence;
import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.ToolboxException;

public class ToolboxUtils
{

    public static Sentence uimaSentence2ToolboxSentence(
            JCas jcas,
            Tagset tagset,
            de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence uimaSentence
    )
        throws ToolboxException
    {
        List<String> tokens = JCasUtil.toText(JCasUtil.selectCovered(jcas, Token.class, uimaSentence));

        List<String> lemmas = new ArrayList<String>();
        for (Lemma lemma : JCasUtil.selectCovered(jcas, Lemma.class, uimaSentence)) {
            lemmas.add(lemma.getValue());
        }
        List<Tag> tagList = new ArrayList<Tag>();
        for (POS pos : JCasUtil.selectCovered(jcas, POS.class, uimaSentence)) {
            tagList.add(new Tag(pos.getPosValue(), tagset));
        }

        return new Sentence(tokens, lemmas, tagList);
    }

    public static Tag uimaPos2ToolboxTag(
            JCas jcas,
            Tagset tagset,
            POS pos
    )
        throws ToolboxException
    {
        return new Tag(pos.getPosValue(), tagset);
    }
    
    public static Resource[] getResources(String path) throws IOException {  
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return resolver.getResources(path);
    }
}