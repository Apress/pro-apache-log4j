package sam.logging;

import java.util.logging.*;
import sam.logging.child.ChildLogger;

public class LoggingMonitor 
{
    public static void main(String[] args) 
    {
        ParentLogger pLogger = new ParentLogger();
        ChildLogger cLogger = new ChildLogger();
        cLogger.aMethod();
        
    }
}
