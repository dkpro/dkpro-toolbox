package dkpro.toolbox.corpus.util;

import static de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase.INCLUDE_PREFIX;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;
import de.tudarmstadt.ukp.dkpro.core.io.tei.TeiReader;

public class CorpusConversion {

	public static void main(String[] args)
		throws Exception
	{
	       String corpusPath = "classpath:/corpus/brown_tei/";
	       
	       CollectionReaderDescription brownReader = CollectionReaderFactory.createReaderDescription(
                    TeiReader.class,
                    TeiReader.PARAM_LANGUAGE, "en",
                    TeiReader.PARAM_SOURCE_LOCATION, corpusPath,
                    TeiReader.PARAM_PATTERNS, new String[] {
                    	INCLUDE_PREFIX + "*.xml", 
                    	INCLUDE_PREFIX + "*.xml.gz"
                    }
            );
	       
	       AnalysisEngineDescription writer = AnalysisEngineFactory.createEngineDescription(
                   BinaryCasWriter.class,
                   BinaryCasWriter.PARAM_TARGET_LOCATION, "target/brown/"          
           );
	       
	       SimplePipeline.runPipeline(brownReader, writer);
	}
}
