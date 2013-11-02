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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Sentence {

	private final List<String> tokens;
    private final List<String> lemmas;
    private final List<Tag> tags;

	/**
	 * Initializes an empty sentence.
	 */
	public Sentence() {
		super();
		this.tokens = new ArrayList<String>();
        this.lemmas = new ArrayList<String>();
        this.tags   = new ArrayList<Tag>();
	}
	
	/**
	 * Initializes a sentence with the provided tokens.
	 * @param tokens A list of tokens.
	 */
	public Sentence(List<String> tokens) {
		super();
		this.tokens = tokens;
		this.lemmas = new ArrayList<String>();
        this.tags   = new ArrayList<Tag>();
	}

    /**
     * Initializes a sentence with the provided tokens and lemmas.
     * @param tokens A list of tokens.
     */
    public Sentence(List<String> tokens, List<String> lemmas) {
        super();
        this.tokens = tokens;
        this.lemmas = lemmas;
        this.tags   = new ArrayList<Tag>();
    }

    /**
     * Initializes a sentence with the provided tokens, lemmas, and tags.
     * @param tokens A list of tokens.
     */
    public Sentence(List<String> tokens, List<String> lemmas, List<Tag> tags) {
        super();
        this.tokens = tokens;
        this.lemmas = lemmas;
        this.tags   = tags;
    }

    @Override
	public String toString() {
        return StringUtils.join(getTokens(), ' ');
	}
	
    public String getFormattedString() {
        return "[" + StringUtils.join(getTokens(), ' ') + "]";
    }

    
    public void addToken(String token) {
        this.tokens.add(token);
    }
    
    public void addLemma(String lemma) {
        this.lemmas.add(lemma);
    }
    
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public List<String> getTokens() {
		return tokens;
	}

    public List<String> getLemmas()
    {
        return lemmas;
    }

    public List<Tag> getTags()
    {
        return tags;
    }
    
    public List<String> getSimplifiedTags()
    {
        List<String> tagStrings = new ArrayList<String>();
        for (Tag tag : tags) {
            tagStrings.add(tag.getSimplifiedTag());
        }
        return tagStrings;
    }
    
    public List<String> getCanoncialTags()
    {
        List<String> tagStrings = new ArrayList<String>();
        for (Tag tag : tags) {
            tagStrings.add(tag.getCanonicalTag());
        }
        return tagStrings;
    }
    
    public List<String> getOriginalTags()
    {
        List<String> tagStrings = new ArrayList<String>();
        for (Tag tag : tags) {
            tagStrings.add(tag.getOriginalTag());
        }
        return tagStrings;
    }
    
    public List<TaggedToken> getTaggedTokens() {
        List<TaggedToken> taggedToken = new ArrayList<TaggedToken>();
        for (int i=0; i<tokens.size(); i++) {
            if (tags.size() > i) {
                taggedToken.add(new TaggedToken(tokens.get(i), tags.get(i)));
            }
            else {
                taggedToken.add(new TaggedToken(tokens.get(i)));
            }
        }
        
        return taggedToken;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lemmas == null) ? 0 : lemmas.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
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
        Sentence other = (Sentence) obj;
        if (lemmas == null) {
            if (other.lemmas != null) {
                return false;
            }
        }
        else if (!lemmas.equals(other.lemmas)) {
            return false;
        }
        if (tags == null) {
            if (other.tags != null) {
                return false;
            }
        }
        else if (!tags.equals(other.tags)) {
            return false;
        }
        if (tokens == null) {
            if (other.tokens != null) {
                return false;
            }
        }
        else if (!tokens.equals(other.tokens)) {
            return false;
        }
        return true;
    }
}