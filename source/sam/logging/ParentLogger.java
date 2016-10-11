package sam.logging;

import java.util.logging.*;


public class ParentLogger
{
	
	private Logger logger = Logger.getLogger("sam.logging");
;
	private Level level = null;
	
	public ParentLogger()
	{

		level = Level.SEVERE;
		//setting the level as SEVERE
		logger.setLevel(level);
	}
	
	public void aMethod()
	{
		logger.log(level, "Severe message from Parent Logger");
	}
	
}
