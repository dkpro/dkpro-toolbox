package dkpro.toolbox.core;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class NGram
{

    private List<String> parts;

    public NGram(List<String> parts)
    {
        super();
        this.parts = parts;
    }
    
    public NGram(String ... parts)
    {
        super();
        this.parts = Arrays.asList(parts);
    }
    
    public String getPart(int position) {
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