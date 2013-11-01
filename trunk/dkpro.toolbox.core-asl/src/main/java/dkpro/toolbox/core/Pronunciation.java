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
}