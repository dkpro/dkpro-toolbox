package dkpro.toolbox.wordnet;

import java.util.List;

public interface WordNet
{

    public List<Synset> getSynsets(String lemma);
    
    public List<String> getLemmas(String lemma);
    
    public Synset getSynset(String lemma, String tag, String senseId);
//
//    # returns the synset [Synset('car.n.01')] corresponding to the word 'motorcar'.
//    print wn.synsets('motorcar') 
//
//    # returns all words ['car', 'auto', 'automobile', 'machine', 'motorcar'] that exist in the synset 'car.n.01'
//    print wn.synset('car.n.01').lemma_names 
//
//    # returns the definition 'a motor vehicle with four wheels; usually propelled by an internal combustion engine' of the synset 'car.n.01'
//    print wn.synset('car.n.01').definition 
//
//    # returns the examples ['he needs a car to get to work'] for the synset 'car.n.01'
//    print wn.synset('car.n.01').examples 
//
//    # returns all lemmas [Lemma('car.n.01.car'), Lemma('car.n.01.auto'), Lemma('car.n.01.automobile'), Lemma('car.n.01.machine'), Lemma('car.n.01.motorcar')] that exist in the synset 'car.n.01'
//    print wn.synset('car.n.01').lemmas 
//
//    # returns the lemma 'car.n.01.automobile'
//    print wn.lemma('car.n.01.automobile') 
//
//     # returns the synset 'car.n.01' corresponding to the lemma 'car.n.01.automobile'
//    print wn.lemma('car.n.01.automobile').synset
//
//    # returns the name of the lemma 'car.n.01.automobile'
//    print wn.lemma('car.n.01.automobile').name 
//
//    # returns the synsets [Synset('car.n.01'), Synset('car.n.02'), Synset('car.n.03'), Synset('car.n.04'), Synset('cable_car.n.01')] corresponding to the word 'car'. We note that word 'car' has more meanings than 'motorcar', hence more synsets are retrieved from WordNet.
//    print wn.synsets('car') 
//
//    # returns the lemmas [Lemma('car.n.01.car'), Lemma('car.n.02.car'), Lemma('car.n.03.car'), Lemma('car.n.04.car'), Lemma('cable_car.n.01.car')]. 'car.n.0x.car' here are different lemmas that belong to different synsets.
//    print wn.lemmas('car')
}
