package dkpro.toolbox.corpus.categorized;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;


public class CombinedIterable
    <T> implements Iterable<T>
{
    private Queue<Iterator<T>> iteratorQueue;
    private int maxItems;
    private int itemCounter;
    
    public CombinedIterable(List<Iterator<T>> iterators)
    {
        this(iterators, Integer.MAX_VALUE);
    }
    
    public CombinedIterable(List<Iterator<T>> iterators, int maxItems)
    {
        iteratorQueue = new ArrayDeque<Iterator<T>>(iterators);
        this.maxItems = maxItems;
        this.itemCounter = 0;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new CombinedIterator<Iterable<T>>();
    }
    
    private class CombinedIterator
        <A> implements Iterator<T>
    {
    
        @Override
        public boolean hasNext()
        {
            // only output a configurable max amount of items
            if (itemCounter == maxItems) {
                return false;
            }
            
            while (!iteratorQueue.isEmpty()) {
                if (iteratorQueue.peek().hasNext()) {
                    return true;
                }
                iteratorQueue.poll();
            }
            return false;
        }
    
        @Override
        public T next()
        {
            itemCounter++;
            
            Iterator<T> iterator = iteratorQueue.poll();
            T result = iterator.next();
            iteratorQueue.offer(iterator);
            return result;
        }
    
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}