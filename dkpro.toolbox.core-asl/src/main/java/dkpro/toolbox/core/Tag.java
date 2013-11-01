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
package dkpro.toolbox.core;

import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.resources.MappingProvider;
import dkpro.toolbox.core.util.TagUtil;

public class Tag
{

    private String originalTag;
    private String canonicalTag;
    private String simplifiedTag;

    public Tag(String aTag, String language)
            throws ToolboxException
    {
        super();
        initialize(aTag, language);
    }
    
    private void initialize(String aTag, String language)
            throws ToolboxException
    {
        MappingProvider provider;
        try {
            provider = TagUtil.getMappingProvider(language);
            this.originalTag = aTag;
            this.canonicalTag = provider.getTagType(aTag).getShortName();
            this.simplifiedTag = this.canonicalTag.substring(0, 1);
        }
        catch (ResourceInitializationException e) {
            throw new ToolboxException();
        }
    }
    
    public String getOriginalTag()
    {
        return originalTag;
    }

    public void setOriginalTag(String originalTag)
    {
        this.originalTag = originalTag;
    }

    public String getCanonicalTag()
    {
        return canonicalTag;
    }

    public void setCanonicalTag(String canonicalTag)
    {
        this.canonicalTag = canonicalTag;
    }

    public String getSimplifiedTag()
    {
        return simplifiedTag;
    }

    public void setSimplifiedTag(String simplifiedTag)
    {
        this.simplifiedTag = simplifiedTag;
    }

    @Override
    public String toString()
    {
        return this.originalTag + "/" + this.canonicalTag + "/" + this.simplifiedTag; 
    }
}