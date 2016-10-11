

package sam.logging.handler;
import java.util.logging.*;
public class RemoteHandlerDemo
{
	private Logger logger = null;
	private RemoteHandler handler = null;
	
	public RemoteHandlerDemo()
	{
		logger = Logger.getLogger("sam.logging.handler");
		handler = RemoteHandler.getInstance("LoggingServer");
		logger.addHandler(handler);
	}
	
	public void logMessage()
	{
		LogRecord record = new LogRecord(Level.SEVERE, "Servere message....");
		logger.log(record);
	}
	
	public static void main(String args[])
	{
		RemoteHandlerDemo demo = new RemoteHandlerDemo();
		demo.logMessage();
	}
		
		
		
}

