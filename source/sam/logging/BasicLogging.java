package sam.logging;

import java.util.logging.*;
import java.io.*;
public class BasicLogging
{
    private static Logger logger = Logger.getLogger("MyLogger");
    private ConsoleHandler console = null;
    private FileHandler file = null;
    public BasicLogging()
    {
        //create a new handler to write to the console
        console = new ConsoleHandler();
        //create a new handler to write to a named file
        try
        {
            file = new FileHandler("basicLogging.out");
        }catch(IOException ioe)
        {
            logger.warning("Could not create a file...");
        }

        //add the handlers to the logger
        logger.addHandler(console);
        logger.addHandler(file);
    }

    public void logMessage()
    {
        //log a message
        logger.info("I am logging test message..");
    }

 public static void main(String args[])
 {
     BasicLogging demo = new BasicLogging();
     demo.logMessage();
 }

}