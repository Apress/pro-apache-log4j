package sam.logging;

import java.util.logging.*;

public class FilterDemo
{
	private Logger logger = null;
	private AgeFilter filter = null;
	
	public FilterDemo()
	{
		//obtaining a logger object
		logger = Logger.getLogger("sam.logging");
		//creating a AgeFilter object
		filter = new AgeFilter();
		//attaching the filter to the logger
		logger.setFilter(filter);
	}
	
	/**
	 * This method logs the message
	 */
	public void logMessage(Person person)
	{
		//logging the message with Person object as parameter
		logger.log(Level.INFO, "Person has age "+person.getAge(), person);
		
	}
	
	public static void main(String args[])
	{
		FilterDemo demo = new FilterDemo();
		//creating  Person objects
		Person person1 = new Person("Paul", 32);
		Person person2 = new Person("sam", 29);
		//logging with the each Person object
		demo.logMessage(person1);
		demo.logMessage(person2);
	}
}

