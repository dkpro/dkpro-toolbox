package dkpro.toolbox.core.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import dkpro.toolbox.core.NGram;

public class NGramUtilTest
{

    @Test
    public void ngramUtilTest() {
//        String[] items = new String[]{"D", "N", "N", "J", "N", "V", "A", "D", "N", "P", "N", "J", "N", "N", "V", ".", "D", "N", ".", "&", "O", "N", "V", "N", "."}
        String[] items = new String[]{"D", "N", "N", "J", "N"};
        
        int i=0;
        for (NGram<String> ngram : NGramUtils.getNGrams(Arrays.asList(items), 2)) {
            System.out.println(ngram);
            if (i==2) {
                assertEquals("N", ngram.getPart(0));
                assertEquals("J", ngram.getPart(1));

            }
            i++;
        }
        assertEquals(4, i);
    }
}
