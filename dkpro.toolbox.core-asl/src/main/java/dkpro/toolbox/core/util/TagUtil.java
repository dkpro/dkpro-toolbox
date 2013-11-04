/*******************************************************************************
 * Copyright 2013
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package dkpro.toolbox.core.util;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.resources.MappingProvider;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;


public class TagUtil
{

    private static MappingProvider posMappingProvider = null;

    public static MappingProvider getMappingProviderBrown() throws ResourceInitializationException {
        if (posMappingProvider == null) {

            posMappingProvider = new MappingProvider();
            posMappingProvider.setDefault(MappingProvider.LOCATION, "classpath:/tagmapping/en-brown.map");
            posMappingProvider.setDefault(MappingProvider.BASE_TYPE, POS.class.getName());
            posMappingProvider.setDefault("pos.tagset", "default");

            AnalysisEngine engine = createEngine(OpenNlpPosTagger.class);
            posMappingProvider.configure(engine.newCAS());
        }

        return posMappingProvider;
    }
    
    public static MappingProvider getMappingProviderPTB(String language) throws ResourceInitializationException {
        if (posMappingProvider == null) {

            posMappingProvider = new MappingProvider();
            posMappingProvider.setDefault(MappingProvider.LOCATION, "classpath:/de/tudarmstadt/ukp/dkpro/" +
                    "core/api/lexmorph/tagset/" + language + "-default-pos.map");
            posMappingProvider.setDefault(MappingProvider.BASE_TYPE, POS.class.getName());
            posMappingProvider.setDefault("pos.tagset", "default");

            AnalysisEngine engine = createEngine(OpenNlpPosTagger.class);
            posMappingProvider.configure(engine.newCAS());
        }

        return posMappingProvider;
    }
}
