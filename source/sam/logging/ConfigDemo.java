/**
 *File: ConfigDemo.java
 */
package sam.logging;

import java.util.logging.Logger;
import java.util.logging.SocketHandler;

public class ConfigDemo
{
    private static Logger logger = Logger.getLogger("sam.logging");

    public ConfigDemo()
    {
        try
        {
          logger.addHandler(new SocketHandler());
        }catch(Exception e)
        {
            e.printStackTrace();

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
        ConfigDemo demo = new ConfigDemo();
        demo.logMessage();
    }
}
