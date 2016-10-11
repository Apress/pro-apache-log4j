/**
 * ConfigDemo.java
 *
 * @author Created by Omnicore CodeGuide
 */

package sam.logging.config;
import java.util.logging.LogManager;

public class ConfigDemo
{
	public static void main(String args[])
	{
		LogManager manager = LogManager.getLogManager();
		String level = manager.getProperty("java.util.logging.ConsoleHandler.level");
		System.out.println("The ConsoleHandler level is: "+level);
	}
		
}

