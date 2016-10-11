
package sam.logging;

import java.util.logging.*;

public class Logging {
	
	private Logger logger = null;
	private Level level = null;
	
	public Logging()
	{
	}
	
	public void logMessage()
	{
		log("This is in the sam.logging.logMessage method");
	}
	
	public void log(String message)
	{
		logger = Logger.getLogger("sam.logging");
		logger.log(level, message);
	}
	
	public void setLogger(String loggerName)
	{
	}
	public Logger getLogger()
	{
		return this.logger;
	}
	
	public void setLevel(Level level)
	{
		this.level = level;
		this.logger.setLevel(level);
	}
	public Level getLevel()
	{
		return this.level;
	}
		
		
}
