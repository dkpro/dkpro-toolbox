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

import org.apache.uima.cas.Type;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.resources.MappingProvider;
import dkpro.toolbox.core.util.TagUtil;

public class Tag
{
    public enum Tagset {
        brown,
        penntreebank,
        stts
    }
    
    public enum TagLevel {
        original,
        canonical,
        simplified
    }

    private String originalTag;
    private String canonicalTag;
    private String simplifiedTag;
    
    public Tag(String aTag)
            throws ToolboxException
    {
        super();
        initialize(aTag, Tagset.penntreebank);
    }

    public Tag(String aTag, Tagset tagset)
            throws ToolboxException
    {
        super();
        initialize(aTag, tagset);
    }
    
    public Tag(String originalTag, String canonicalTag, String simplifiedTag) {
        this.originalTag = originalTag;
        this.canonicalTag = canonicalTag;
        this.simplifiedTag = simplifiedTag;
    }
    
    private void initialize(String aTag, Tagset tagset)
            throws ToolboxException
    {
        MappingProvider provider = null;
        try {
            if (tagset.equals(Tagset.brown)) {
                provider = TagUtil.getMappingProviderPTB();

            }
            else if (tagset.equals(Tagset.penntreebank)) {
                provider = TagUtil.getMappingProviderPTB();
 
            }
            else {
                throw new ToolboxException("Tagset currently not provided: " + tagset);
            }
            
            Type posType = provider.getTagType(aTag.toUpperCase());
            String simpleTag = getSimpleTag(posType.getShortName());

            this.originalTag = aTag;
            this.canonicalTag = posType.getShortName();
            this.simplifiedTag = simpleTag;
        }
        catch (ResourceInitializationException e) {
            throw new ToolboxException();
        }
    }
    
    private String getSimpleTag(String shortName) {
        if (shortName.equals("ADJ")) {
            return "J";
        }
        else if (shortName.equals("ADV")) {
            return "A";
        }
        else if (shortName.equals("ART")) {
            return "D";
        }
        else if (shortName.equals("CARD")) {
            return "1";
        }
        else if (shortName.equals("CONJ")) {
            return "&";
        }
        else if (shortName.startsWith("N")) {
            return "N";
        }
        else if (shortName.equals("PP")) {
            return "P";
        }
        else if (shortName.equals("PR")) {
            return "$";
        }
        else if (shortName.equals("PUNC")) {
            return ".";
        }
        else if (shortName.equals("V")) {
            return "V";
        }
        else {
            return "O";
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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((originalTag == null) ? 0 : originalTag.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Tag other = (Tag) obj;
        if (originalTag == null) {
            if (other.originalTag != null) {
                return false;
            }
        }
        else if (!originalTag.equals(other.originalTag)) {
            return false;
        }
        return true;
    }
}