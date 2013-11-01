package dkpro.toolbox.tools;

import java.util.Arrays;
import java.util.List;

public class RegExpTokenizer
{
    private String pattern;
    
    public RegExpTokenizer(String pattern)
    {
        this.pattern = pattern;
    }
    
    public List<String> tokenize(String text) {
        return Arrays.asList(text.split(pattern));
    }
}