package dkpro.toolbox.core;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Pronunciation
{
    private List<String> phonemes;

    public Pronunciation(List<String> phonemes)
    {
        super();
        this.phonemes = phonemes;
    }

    public List<String> getPhonemes()
    {
        return phonemes;
    }

    public void setPhonemes(List<String> phonemes)
    {
        this.phonemes = phonemes;
    }

    @Override
    public String toString()
    {
        return StringUtils.join(phonemes, " ");
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((phonemes == null) ? 0 : phonemes.hashCode());
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
        Pronunciation other = (Pronunciation) obj;
        if (phonemes == null) {
            if (other.phonemes != null) {
                return false;
            }
        }
        else if (!phonemes.equals(other.phonemes)) {
            return false;
        }
        return true;
    }
}