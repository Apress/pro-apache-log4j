package sam.logging;

import java.util.logging.*;

public class ConsoleHandlerDemo 
{
	private Logger logger = null;
	private ConsoleHandler handler = null;
	
	public ConsoleHandlerDemo()
	{
		//obtaining a logger reference
		logger = Logger.getLogger("sam.logging");
		
		//creating a new ConsoleHandler object
		handler = new ConsoleHandler();
		
		//adding the handler to the logger
		logger.addHandler(handler);
		
		//do not use the parent loggers
		logger.setUseParentHandlers(false);
	}
	
	/**
	* This method logs the logging information 
	*/
	
	public void logMessage()
	{
		//first way, to ask the logger to do the logging
		logger.log(Level.INFO, "Through logger...");
		
		//second way, to use the handler to publish a LogRecord
		LogRecord record = new LogRecord(Level.WARNING, "Through the handler..");
		handler.publish(record);
		
		//any message with level below the handler level wont print
		handler.publish(new LogRecord(Level.FINE, "This wont print..."));
		
		//change the level of the handler and it works
		handler.setLevel(Level.FINER);
		handler.publish(new LogRecord(Level.FINE, "This is after changing the handler level..."));
	}
	public static void main(String[] args) {
		
		ConsoleHandlerDemo demo = new ConsoleHandlerDemo();
		demo.logMessage();
	}	
}
