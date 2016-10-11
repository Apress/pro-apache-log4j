/*
 * AsyncLogging.java
 */

package com.apress.logging.log4j;

import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;
/** This class demonstrates the asynchronous logging
 */
public class AsyncLogging 
{
    
    private static Logger logger = Logger.getLogger(AsyncLogging.class.getPackage().getName());
    private AsyncAppender asyncAppender = null;
    private ConsoleAppender consoleAppender = null;
    
    /** Creates a new instance of AsyncLogging */
    public AsyncLogging() 
    {
        
        try {
            logger.setAdditivity(false);
            asyncAppender = (AsyncAppender)logger.getRootLogger().getAppender("ASYNC");
            asyncAppender.setBufferSize(4);
        }catch(Exception e) {
            System.out.println("error: "+e.toString());
        }
        
    }
    
    /** This method simply logs the messages
     */    
    public void doLogging() 
    {
        logger.debug("Hello 1");
        logger.debug("Hello 2");
        logger.debug("Hello 3");
        logger.debug("Hello 4");
        logger.debug("Hello 5");
        //LogManager.shutdown();
    }
    
    /** the main method
     */    
    public static void main(String args[]) 
    {
        AsyncLogging demo = new AsyncLogging();
        demo.doLogging();
    }
    
}
