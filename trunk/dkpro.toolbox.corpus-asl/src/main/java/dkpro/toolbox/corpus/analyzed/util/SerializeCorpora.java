package dkpro.toolbox.corpus.analyzed.util;

import dkpro.toolbox.corpus.TextCorpus;
import dkpro.toolbox.corpus.analyzed.AnalyzedCorpus;

/**
 * Creates the serialized corpora used by the toolbox to fill the {@link AnalyzedCorpus} objects.
 * 
 * @author zesch
 *
 */
public class SerializeCorpora
{
 
    public static void main(String[] args)
        throws Exception
    {
//        new TextCorpus(
//                "src/test/resources/test_corpora/text/",
//                "en",
//                "Moby Dick - Melville",
//                "Moby Dick by Herman Melville 1851",
//                "melville-moby_dick.txt"
//        );
//        
//        new TextCorpus(
//                "src/test/resources/test_corpora/text/",
//                "en", 
//                "Sense-and-Sensibility",
//                "Sense and Sensibility by Jane Austen 1811",
//                "austen-sense.txt"
//        );
//
//        new TextCorpus(
//                "src/test/resources/test_corpora/text/",
//                "en", 
//                "Man-who-was-thursday",
//                "The Man Who Was Thursday by G . K . Chesterton 1908",
//                "chesterton-thursday.txt"
//        );
//        
//        new TextCorpus(
//                "src/test/resources/test_corpora/text/inaugural",
//                "en", 
//                "Inaugural",
//                "US presidential inaugural addresses 1789-2009",
//                "*.txt"
//        );
//        
//        new TextCorpus(
//                "src/test/resources/test_corpora/text/",
//                "en", 
//                "Genesis",
//                "The book of Genesis",
//                "genesis-english-kjv.txt"
//        );
//
//        new TextCorpus(
//                "src/test/resources/test_corpora/text/",
//                "en", 
//                "personals",
//                "The Personals Corpus",
//                "singles.txt"
//        );
//        
//        new DkproCorpus(
//                "en", 
//                "chat",
//                "NPS Chat Corpus",
//                CollectionReaderFactory.createReaderDescription(
//                        XmlReaderText.class,
//                        XmlReaderText.PARAM_SOURCE_LOCATION, "src/test/resources/test_corpora/xml/nps_chat",
//                        XmlReaderText.PARAM_LANGUAGE, "en",
//                        XmlReaderText.PARAM_PATTERNS, "*.xml"
//                )
//        );
//        
//        new TextCorpus(
//                "src/test/resources/test_corpora/text/names/",
//                "en", 
//                "names",
//                "First names corpus",
//                "*.txt"
//        );
        
        new TextCorpus(
              "src/test/resources/test_corpora/text/udhr/",
              "mixed", 
              "udhr",
              "Universal Declaration of Human Rights",
              "*"
        );
    }
}
