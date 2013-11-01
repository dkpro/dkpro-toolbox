package dkpro.toolbox.core.util;

import java.util.ArrayList;
import java.util.List;

import de.tudarmstadt.ukp.dkpro.core.ngrams.util.NGramStringIterable;
import dkpro.toolbox.core.NGram;

public class NGramUtils
{

    public static List<NGram> getNGrams(Iterable<String> items, int size) {
        List<NGram> ngrams = new ArrayList<NGram>();
        for (String item : new NGramStringIterable(items, size, size)) {
            String[] parts = item.split(" ");
            ngrams.add(new NGram(parts));
        }
        return ngrams;
    }
}
