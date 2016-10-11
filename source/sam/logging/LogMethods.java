package sam.logging;

import java.util.logging.*;
import java.io.IOException;
public class LogMethods 
{
    private static Logger logger = Logger.getLogger("sam.logging");
    public LogMethods() 
    {
        //first obtain the logmanager instance
        LogManager manager = LogManager.getLogManager();
        //remove all the associated handlers with this manager
        manager.reset();
        //create a new handler for the logger to use
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.FINEST);
        
        //setting the logger level and handler
        logger.setLevel(Level.FINEST);
        logger.addHandler(ch);
        
    }
    
    /**
     * This method demonstrates the basic logging methods
     */
    public void printBasicMethods() 
    {
        logger.log(Level.INFO, "THIS IS INFO LEVEL MESSAGE");
        
        //creating a log record on our own
        LogRecord record = new LogRecord(Level.SEVERE, "OUR OWN LOGRECORD OBJECT");
        
        //logging the log record object
        logger.log(record);
        
    }
    
    /**
     * This method demonstrates the precise logging methods
     */
    public void printPreciseMethods() 
    {
        logger.logp(Level.INFO, "LogMethods", "printPreciseMethods", "PRECISE METHODS..");
    }
    
    /**
     * This methods demonstrates the level based logging methods
     */
    public void printLevelMethods() 
    {
        
        logger.fine("THIS IS A FINE LEVEL MESSAGE");
        logger.finer("THIS IS A FINER LEVEL MESSAGE");
        logger.finest("THIS IS A FINEST LEVEL MESSAGE");
        logger.config("THIS IS CONFIG LEVEL MESSAGE");
    }
    
    /**
     *This method demonstrates the method level logging methods
     */
    public void printMethod() 
    {
        
        logger.entering("LogMethods", "printMethod");
        logger.exiting("LogMethods", "printMethod");
    }
    
    public static void main(String[] args) 
    {
        LogMethods lm = new LogMethods();
        lm.printBasicMethods();
        lm.printPreciseMethods();
        lm.printLevelMethods();
        lm.printMethod();
    }
}
