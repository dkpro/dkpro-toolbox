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

public class TaggedToken
{

    private String token;
    private Tag tag;
    
    public TaggedToken(String token) {
        super();
        this.token = token;
        this.tag = null;
    }
    
    public TaggedToken(String token, Tag tag)
    {
        super();
        this.token = token;
        this.tag = tag;
    }

    public String getToken()
    {
        return token;
    }
    public void setToken(String token)
    {
        this.token = token;
    }
    public Tag getTag()
    {
        return tag;
    }
    public void setTag(Tag tag)
    {
        this.tag = tag;
    }
    
    @Override
    public String toString()
    {
    	return token + " (" + tag + ")"; 
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
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
        TaggedToken other = (TaggedToken) obj;
        if (tag == null) {
            if (other.tag != null) {
                return false;
            }
        }
        else if (!tag.equals(other.tag)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        }
        else if (!token.equals(other.token)) {
            return false;
        }
        return true;
    }
}
