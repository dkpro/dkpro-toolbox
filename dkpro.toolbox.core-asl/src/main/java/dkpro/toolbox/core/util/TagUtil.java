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

import java.io.IOException;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.resources.MappingProvider;
import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import dkpro.toolbox.core.ToolboxException;


public class TagUtil
{

    private static final String pathBase = "classpath:/tagmapping";

    private static MappingProvider posMappingProviderBrown = null;
    private static MappingProvider posMappingProviderPTB = null;
    private static MappingProvider posMappingProviderSTTS = null;


    public static MappingProvider getMappingProviderBrown() throws ToolboxException {
        if (posMappingProviderBrown == null) {
            posMappingProviderBrown = initialize(pathBase + "/en-brown-pos.map");           
        }

        return posMappingProviderBrown;
    }
    
    public static MappingProvider getMappingProviderPTB() throws ToolboxException {
        if (posMappingProviderPTB == null) {
            posMappingProviderPTB = initialize(pathBase + "/en-ptb-pos.map");  
        }

        return posMappingProviderPTB;
    }
    
    public static MappingProvider getMappingProviderSTTS() throws ToolboxException {
        if (posMappingProviderSTTS == null) {
            posMappingProviderSTTS = initialize(pathBase + "/de-stts-pos.map");           
        }

        return posMappingProviderSTTS;
    }
    
    private static MappingProvider initialize(String path)
            throws ToolboxException 
    {
        try {
            String resolvedPath = ResourceUtils.resolveLocation(path).getFile();
            
            MappingProvider provider = new MappingProvider();
            provider.setDefault(MappingProvider.LOCATION, resolvedPath);
            provider.setDefault(MappingProvider.BASE_TYPE, POS.class.getName());
            provider.setDefault("pos.tagset", "default");

            AnalysisEngine engine = createEngine(OpenNlpPosTagger.class);
            provider.configure(engine.newCAS());
            
            if (provider.getResource() == null) {
                throw new ToolboxException("No resource found at: " + resolvedPath);
            }
            
            return provider;
        }
        catch (IOException e) {
            throw new ToolboxException(e);
        }
        catch (ResourceInitializationException e) {
            throw new ToolboxException(e);
        }
    }
}
