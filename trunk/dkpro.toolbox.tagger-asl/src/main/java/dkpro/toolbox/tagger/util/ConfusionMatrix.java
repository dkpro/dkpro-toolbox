package dkpro.toolbox.tagger.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;

public class ConfusionMatrix
{
   
    public static <T> void print(List<T> goldValues, List<T> predictedValues) {
     
        if (goldValues.size() != predictedValues.size()) {
            throw new IllegalArgumentException("Gold and predicted need to be of equal size.");
        }
                
        ConditionalFrequencyDistribution<T, T> cfd = new ConditionalFrequencyDistribution<T, T>();
        
        for (int i=0; i<goldValues.size(); i++) {
            T goldValue = goldValues.get(i);
            T predictedValue = predictedValues.get(i);
            
            cfd.inc(goldValue, predictedValue);
        }
    
        Set<T> categories = new HashSet<T>();
        categories.addAll(goldValues);
        categories.addAll(predictedValues);
        
        System.out.print("\t");
        for (T category : categories) {
            System.out.print(category + "\t");
        }
        System.out.println();

        for (T outerCategory : categories) {
            System.out.print(outerCategory + "\t");
            for (T innerCategory : categories) {
                System.out.print(cfd.getCount(innerCategory, outerCategory) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}