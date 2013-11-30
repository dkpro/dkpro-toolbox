package dkpro.toolbox.core.util;

import java.util.Map;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;

public class CondFreqDist<C, V>
    extends ConditionalFrequencyDistribution<C, V>
{

    public CondFreqDist()
    {
        super();
    }

    public CondFreqDist(Map<C, Iterable<V>> samples)
    {
        super(samples);
    }

    public FreqDist<V> getFreqDist(C condition)
    {
        return new FreqDist<V>(super.getFrequencyDistribution(condition));
    }   
}