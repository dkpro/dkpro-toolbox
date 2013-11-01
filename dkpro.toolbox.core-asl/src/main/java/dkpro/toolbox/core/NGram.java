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
}