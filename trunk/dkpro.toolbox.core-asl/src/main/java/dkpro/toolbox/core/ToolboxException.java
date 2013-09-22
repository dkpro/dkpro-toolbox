package dkpro.toolbox.core;

/**
 * Exception thrown by DKPro Toolbox components.
 * 
 * @author zesch
 *
 */
public class ToolboxException
    extends Exception
{

    static final long serialVersionUID = 1L;

    public ToolboxException()
    {
        super();
    }

    public ToolboxException(String txt)
    {
        super(txt);
    }

    public ToolboxException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ToolboxException(Throwable cause)
    {
        super(cause);
    }

}
