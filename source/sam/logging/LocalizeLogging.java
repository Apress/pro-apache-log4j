package sam.logging;

import java.util.logging.*;
import java.io.*;
import java.util.ResourceBundle;

public class LocalizeLogging 
{
    private static Logger logger = Logger.getLogger("sam.logging");
    private String rbName = null;
    
    public LocalizeLogging(String rbName)
    {
        this.rbName = rbName;
        
    }
        
    public void logMessage() 
    {
        logger.logrb(Level.INFO, "LocalizeLogging", "logMessage", rbName, "success");
    
    }
    
    public static void main(String args[]) {
        //collect the name of the resource bundle
        String rbName = args[0];
        LocalizeLogging lLogging = new LocalizeLogging(rbName);
        lLogging.logMessage();
    }
    
}

