package dkpro.toolbox.tools;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RegExpTokenizerTest
{

    @Test
    public void testRegExpTokenizer() {
        String pattern = "\\s";
        RegExpTokenizer tokenizer = new RegExpTokenizer(pattern);
        
        int i=0;
        for (String token : tokenizer.tokenize("'This is an counter-example, with some punctuation.'")) {
            System.out.println(token);
            i++;
        }
        assertEquals(7, i);
    }
}
