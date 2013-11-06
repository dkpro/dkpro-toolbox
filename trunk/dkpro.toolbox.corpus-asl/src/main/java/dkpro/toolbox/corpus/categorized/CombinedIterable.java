package dkpro.toolbox.corpus.categorized;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;


public class CombinedIterable
    <T> implements Iterable<T>
{
    private Queue<Iterator<T>> iteratorQueue;
    public CombinedIterable(List<Iterator<T>> iterators)
    {
        iteratorQueue = new ArrayDeque<Iterator<T>>(iterators);
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