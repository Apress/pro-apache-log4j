package sam.logging;

import java.util.logging.*;
import java.io.*;

public class StreamHandlerDemo 
{
    private StreamHandler handler = null;
    private OutputStream outStream = null;
    private static Logger logger = Logger.getLogger("sam.logging");
    public StreamHandlerDemo() 
    {
        //creating an outputstream as System.out
        outStream = System.out;
        
        //creating a stream handler
        handler = new StreamHandler(outStream, new SimpleFormatter());
        //setting the handler to the logger
        logger.addHandler(handler);
        
    }
    
    /**
     *This method demonstrates the stream handler logging capability
     */
    public void logMessage() 
    {
       logger.info("StreamHandler is working...");
    }
    
    
    public static void main(String[] args) 
    {
        StreamHandlerDemo demo = new StreamHandlerDemo();
        demo.logMessage();
        
    }
    
}

