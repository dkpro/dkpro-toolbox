package dkpro.toolbox.core.util;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;

public class FreqDist<T>
    extends FrequencyDistribution<T>
{
    private static final long serialVersionUID = 1L;

    public FreqDist()
    {
        super();
    }

    public FreqDist(Iterable<T> iterable)
    {
        super(iterable);
    }
    
    public FreqDist(FrequencyDistribution<T> freqDist)
    {   
        super();
        for (T key : freqDist.getKeys()) {
            this.addSample(key, freqDist.getCount(key));
        } 
    }
}