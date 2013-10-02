package dkpro.toolbox.corpus.analyzedtext.util;

import dkpro.toolbox.corpus.TextCorpus;

public class SerializeTexts
{
    public static void main(String[] args)
        throws Exception
    {
        new TextCorpus(
                "src/test/resources/test_corpora/text/",
                "en",
                "Moby Dick - Melville",
                "Moby Dick by Herman Melville 1851",
                "melville-moby_dick.txt"
        );
        
        new TextCorpus(
                "src/test/resources/test_corpora/text/",
                "en", 
                "Sense-and-Sensibility",
                "Sense and Sensibility by Jane Austen 1811",
                "austen-sense.txt"
        );

        new TextCorpus(
                "src/test/resources/test_corpora/text/",
                "en", 
                "Man-who-was-thursday",
                "The Man Who Was Thursday by G . K . Chesterton 1908",
                "chesterton-thursday.txt"
        );
        
        new TextCorpus(
                "src/test/resources/test_corpora/text/inaugural",
                "en", 
                "Inaugural",
                "US presidential inaugural addresses 1789-2009",
                "*.txt"
        );
        
        new TextCorpus(
                "src/test/resources/test_corpora/text/",
                "en", 
                "Genesis",
                "The book of Genesis",
                "genesis-english-kjv.txt"
        );

        new TextCorpus(
                "src/test/resources/test_corpora/text/",
                "en", 
                "personals",
                "The Personals Corpus",
                "singles.txt"
        );
    }
}
