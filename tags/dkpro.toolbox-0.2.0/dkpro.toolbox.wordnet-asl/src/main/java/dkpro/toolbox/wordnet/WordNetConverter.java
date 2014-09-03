package dkpro.toolbox.wordnet;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import net.didion.jwnl.dictionary.Dictionary;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import de.tudarmstadt.ukp.dkpro.lexsemresource.Entity;
import de.tudarmstadt.ukp.dkpro.lexsemresource.LexicalSemanticResource.LexicalRelation;
import de.tudarmstadt.ukp.dkpro.lexsemresource.LexicalSemanticResource.SemanticRelation;
import de.tudarmstadt.ukp.dkpro.lexsemresource.core.ResourceFactory;
import de.tudarmstadt.ukp.dkpro.lexsemresource.wordnet.WordNetResource;
import de.tudarmstadt.ukp.dkpro.lexsemresource.wordnet.util.WordNetUtils;

public class WordNetConverter
{

    public static void main(String[] args) throws Exception
    {
        Gson gson = new Gson();
        File folder = new File("target/wordnet_json/");
        folder.mkdirs();
        
        WordNetResource wordnet = (WordNetResource) ResourceFactory.getInstance().get("wordnet", "en");
        
        Dictionary dict = wordnet.getDict();
        
        LemmaMap lemmaMap = new LemmaMap();
        SynsetMap synsetMap = new SynsetMap();

        for (Entity entity : wordnet.getEntities()) {
            Synset synset = new Synset();
            
            Set<String> antonyms = wordnet.getRelatedLexemes(entity.getFirstLexeme(), entity.getPos(), entity.getSense(entity.getFirstLexeme()), LexicalRelation.antonymy);
            synset.setAntonyms(antonyms);
            
            for (SemanticRelation relation : SemanticRelation.values()) {
                Set<String> items = new HashSet<String>();
                
                for (Entity relatedEntity : wordnet.getRelatedEntities(entity, relation))
                {
                    items.add(relatedEntity.getSense(relatedEntity.getFirstLexeme()));
                }
                
                switch (relation) {
                    case hypernymy:
                        synset.setHypernyms(items);
                        break;
                    case hyponymy:
                        synset.setHyponyms(items);
                        break;
                    case meronymy:
                        synset.setMeronyms(items);
                        break;
                    case holonymy:
                        synset.setHolonyms(items);
                        break;
                    default:
                        break;
                }
            }
            
            StringBuilder sb = new StringBuilder();
            for (net.didion.jwnl.data.Synset wnSynset : WordNetUtils.entityToSynsets(dict, entity, false)) {
                sb.append(wnSynset.getGloss());
                sb.append(" ");
            }
            synset.setDefinition(sb.toString());
            
            synset.setSenseId(entity.getSense(entity.getFirstLexeme()));
            synset.setLemmas(entity.getLexemes());
            synset.setPos(entity.getPos().name());
            
            for (String lemma : synset.getLemmas()) {
                lemmaMap.addLemma(lemma, synset.getSenseId(), synset.getPos());
            }
            synsetMap.addSynset(synset.getSenseId(), synset);
        }
        
        File lemmaFile = new File(folder, "lemmamap.json");
        FileUtils.writeStringToFile(lemmaFile, gson.toJson(lemmaMap));
        
        File synsetFile = new File(folder, "synsetmap.json");
        FileUtils.writeStringToFile(synsetFile, gson.toJson(synsetMap));
    }
}
