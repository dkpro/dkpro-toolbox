package dkpro.toolbox.core.util;


public class FD<T>
    extends FreqDist<T>
{
    private static final long serialVersionUID = 1L;

    public FD()
    {
        super();
    }

    public FD(Iterable<T> iterable)
    {
        super(iterable);
    }
    
    public FD(FreqDist<T> freqDist)
    {   
        super();
        for (T key : freqDist.getKeys()) {
            this.addSample(key, freqDist.getCount(key));
        } 
    }
}