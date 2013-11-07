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

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import dkpro.toolbox.core.Tag;
import dkpro.toolbox.core.Tag.Tagset;
import dkpro.toolbox.core.TaggedToken;

public class OpenNlpTaggerTest
{

    @Test
    public void openNlpAnnotatorEnglishTest()
        throws Exception
    {
    	
        runTest("en", "This is a test .",
                new TaggedToken[] {
                    new TaggedToken(
                            "This",
                            new Tag("DT", Tagset.penntreebank)
                    ),
                    new TaggedToken(
                            "is",
                            new Tag("VBZ", Tagset.penntreebank)
                    ),
                    new TaggedToken(
                            "a",
                            new Tag("DT", Tagset.penntreebank)
                    ),
                    new TaggedToken(
                            "test",
                            new Tag("NN", Tagset.penntreebank)
                    ),
                    new TaggedToken(
                            ".",
                            new Tag("SENT", Tagset.penntreebank)
                    ),
                }
        );
    }
    
    private void runTest(String language, String testDocument, TaggedToken[] taggedTokens)
        throws Exception
    {
        OpenNlpPosTagger tagger = new OpenNlpPosTagger(language);
        
        if (taggedTokens != null) {
            checkTaggedTokens(taggedTokens, tagger.tag(testDocument));
        }
    }

    private void checkTaggedTokens(TaggedToken[] expected, Collection<TaggedToken> actual)
    {
        int i = 0;
        for (TaggedToken taggedToken : actual) {
            assertEquals("In position "+i, expected[i].getToken(), taggedToken.getToken());
            assertEquals("In position "+i, expected[i].getTag().getCanonicalTag(), taggedToken.getTag().getCanonicalTag());
            i++;
        }
    }
}