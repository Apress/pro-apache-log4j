/**
 * This is a Custom Handler object which publishes its logging information
 * to a separate Java window. This follows a singleton pattern to maintain the
 * writing of logging information to the same window as opposed to writing it
 * by opening a new window each time.
 * An application can potentially try to obtain an instance of this Handler at
 *serveral places. Each such attempt will generate a new window. To avoid that
 * a single instance is provided per application with the getInstance() method.
 * 
 */

package sam.logging.handler;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.logging.*;

public class WindowHandler extends Handler
{
	//the window to which the logging is done
	private LogWindow window = null;
	private Formatter formatter = null;
	private Level level = null;
	//the singleton instance
	private static WindowHandler handler = null;
	
	/**
	 * private constructor, preventing intialization
	 */
	private WindowHandler()
	{
		configure();
		if(window == null)
			window = new LogWindow("Logging window",500,200);
	}
	/**
	 *The getInstane method returns the singleton instance of the WindowHandler object
	 * It is synchronized to prevent two threads trying to crate an instance simultaneously.
	 *@ return WindowHandler object
	 */
	public static synchronized WindowHandler getInstance()
	{

		if(handler == null)
		{
			handler = new WindowHandler();
		}
		return handler;
	}
	/**
	 * This method loads the configuration properties from the JDK level configuration
	 * file with the help of LogManager class. It then sets its level, filter and formatter
	 * properties.
	 */
	private void configure()
	{
		LogManager manager = LogManager.getLogManager();
		String className = this.getClass().getName();
		String level = manager.getProperty(className+".level");
		String filter = manager.getProperty(className+".filter");
		String formatter = manager.getProperty(className+".formatter");
		
		//accessing super class methods to set the parameters
		setLevel(level !=null ? Level.parse(level):Level.INFO);
		setFilter(makeFilter(filter));
		setFormatter(makeFormatter(formatter));
		
	}
	/**
	 * private method constructing a Filter object with the filter name.
	 *@param filterName the name of the filter
	 *@return the Filter object
	 */
	private Filter makeFilter(String filterName)
	{
		Class c = null;
		Filter f = null;
		try
		{
			c = Class.forName(filterName);
			f = (Filter)c.newInstance();
		}catch(Exception e)
		{
			System.out.println("There was a problem to load the filter class: "+filterName);
		}
		return f;
	}
	/**
	 *private method creating a Formatter obejct with the formatter name. If no name is
	 *specified, it returns a SimpleFormatter object
	 *@param formatterName the name of the formatter
	 *@return Formatter object
	 */
	private Formatter makeFormatter(String formatterName)
	{
		Class c = null;
		Formatter f = null;
		
		try
		{
			c = Class.forName(formatterName);
			f = (Formatter)c.newInstance();
		}catch(Exception e)
		{
			f = new SimpleFormatter();
		}
		return f;
	}
	
	/**
	 *This is the overridden publish method of the abstract super class Handler.
	 * This method writes the logging information to the associated Java window.
	 *This method is synchronized to make it thread-safe. In case, there is a problem,
	 * it reports the problem with the ErrorManager, only once and silently ignores the
	 * others.
	 *@record the LogRecord object
	 *
	 */
	public synchronized void publish(LogRecord record)
	{
		String message = null;
		//check if the record is loggable
		if( !isLoggable(record))
			return;
		try
		{
			message = getFormatter().format(record);
		}catch(Exception e)
		{
			reportError(null, e, ErrorManager.FORMAT_FAILURE);
		}
		
		try
		{
			window.showInfo(message);
		}catch(Exception ex)
		{
			reportError(null, ex, ErrorManager.WRITE_FAILURE);
		}
		
	}
	
	public void close()
	{
	}
	
	public void flush()
	{
	}
	
}

