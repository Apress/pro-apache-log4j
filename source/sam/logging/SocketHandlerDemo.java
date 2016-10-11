
package sam.logging;

import java.util.logging.*;
import java.io.IOException;

public class SocketHandlerDemo 
{
    private SocketHandler handler = null;
    private static Logger logger = Logger.getLogger("sam.logging");
    
    public SocketHandlerDemo(String host, int port) 
    {
        try {
            //instantiating a SocketHandler with the given host & port
            handler = new SocketHandler(host, port);
            //adding the handler to the logger
            logger.addHandler(handler);
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * This method logs the logging information
     */
    public void logMessage() 
    {
        logger.warning("SocketHandler is working...");
    }
    
    public static void main(String args[]) {
        //creating a SocketHandlerDemo with localhost and 2020 port
        SocketHandlerDemo demo = new SocketHandlerDemo("localhost", 2020);
        demo.logMessage();
    }
    
    
}

