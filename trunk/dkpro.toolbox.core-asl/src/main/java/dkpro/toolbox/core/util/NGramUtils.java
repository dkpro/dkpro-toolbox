package dkpro.toolbox.core.util;

import java.util.ArrayList;
import java.util.List;

import dkpro.toolbox.core.NGram;

public class NGramUtils
{
    public static <T> List<NGram<T>> getNGrams(Iterable<T> items, int size) {
        List<NGram<T>> ngrams = new ArrayList<NGram<T>>();
        for (NGram<T> ngram : new NGramIterable<T>(items, size, size)) {
            ngrams.add(ngram);
        }
        return ngrams;
    }
}