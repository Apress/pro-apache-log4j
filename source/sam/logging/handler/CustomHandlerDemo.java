/**
 * CustomHandlerDemo.java
 */

package sam.logging.handler;
import java.util.logging.*;

public class CustomHandlerDemo
{
	private WindowHandler handler = null;
	private Logger logger = null;
	
	public CustomHandlerDemo()
	{
		handler = WindowHandler.getInstance();
		//obtaining a logger instance and setting the handler
		logger = Logger.getLogger("sam.logging.handler");
		logger.addHandler(handler);
	}
	/**
	 *This method publishes the log message
	 */
	public void logMessage()
	{
		logger.info("Hello from WindowHandler...");
	}
	
	
	public static void main(String args[])
	{
		//logging with the help of a logger
		CustomHandlerDemo demo = new CustomHandlerDemo();
		demo.logMessage();
		//using the handler.publish() to log
		WindowHandler h = WindowHandler.getInstance();
		LogRecord r = new LogRecord(Level.WARNING, "The Handler publish method...");
		h.publish(r);
	}
}

