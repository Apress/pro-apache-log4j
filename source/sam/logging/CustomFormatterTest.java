package sam.logging;

import java.util.logging.*;
import java.io.*;

public class CustomFormatterTest 
{
    private static Logger logger = Logger.getLogger("sam.logging");
    private String fileName = null;
    
    public CustomFormatterTest(String fileName) 
    {
        this.fileName = fileName;
        try 
        {
            FileHandler fh = new FileHandler(fileName);
            CustomFormatter formatter = new CustomFormatter();
            fh.setFormatter(formatter);
            
            logger.addHandler(fh);
        }catch(IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }
    
    /**
     * This method performs the logging activity
     */
    
    public void logMessage() 
    {
        logger.info("log this message");
        
    }
    
    public static void main(String args[]) {
        CustomFormatterTest test = new CustomFormatterTest(args[0]);
        test.logMessage();
        
    }
    
}