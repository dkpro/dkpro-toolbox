package dkpro.toolbox.core;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class NGram<T>
{

    private List<T> parts;

    public NGram(List<T> parts)
    {
        super();
        this.parts = parts;
    }
    
    public NGram(T ... parts)
    {
        super();
        this.parts = Arrays.asList(parts);
    }
    
    public T getPart(int position) {
        if (position < 0 || position > parts.size()) {
            return null;
        }
        else {
            return parts.get(position);
        }
    }
    
    public int size() {
        return parts.size();
    }

    @Override
    public String toString()
    {
        return "(" + StringUtils.join(parts, ",") + ")";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parts == null) ? 0 : parts.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NGram<T> other = (NGram<T>) obj;
        if (parts == null) {
            if (other.parts != null) {
                return false;
            }
        }
        else if (!parts.equals(other.parts)) {
            return false;
        }
        return true;
    }
}