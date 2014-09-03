package dkpro.toolbox.core.util;

import java.util.Map;

public class CFD<C, V>
    extends CondFreqDist<C, V>
{

    public CFD()
    {
        super();
    }

    public CFD(Map<C, Iterable<V>> samples)
    {
        super(samples);
    }

    public FD<V> getFreqDist(C condition)
    {
        return new FD<V>(super.getFrequencyDistribution(condition));
    }   
}