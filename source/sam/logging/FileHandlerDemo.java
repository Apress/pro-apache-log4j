package sam.logging;

import java.util.logging.*;
import java.io.IOException;

public class FileHandlerDemo 
{
    private FileHandler handler = null;
    private static Logger logger = Logger.getLogger("sam.logging");
    
    public FileHandlerDemo(String pattern) 
    {
        try 
        {
            //creating a file handler object with 1000 bytes limit for
            //each file and with count 2
            handler = new FileHandler(pattern,1000, 2);
            //adding the handler to the logger
            logger.addHandler(handler);
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    /**
     * This method logs the message with the FileHandler
     */
    public void logMessage() 
    {
        LogRecord record = new LogRecord(Level.INFO, "Logged in a file..22.");
        logger.log(record);
        
    }
    
    public static void main(String[] args) 
    {
        FileHandlerDemo demo = new FileHandlerDemo("%h/log%g.out");
        demo.logMessage();
        
        
    }
}
