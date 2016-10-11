/*
 * LoggerDemo.java
 */

package com.apress.logging.log4j;

import org.apache.log4j.*;

/** This class demonstrates the basic use of Logger class methods
 */
public class LoggerDemo {
    
    private static Logger logger = Logger.getLogger(LoggerDemo.class.getPackage().getName());
    
    /** Creates a new instance of LoggerDemo */
    public LoggerDemo(String rbName) {
        //setting the use of parent handler to false
        logger.setAdditivity(false);
        logger.debug("Set the parent additivity to false...");
        logger.setResourceBundle(java.util.ResourceBundle.getBundle(rbName));
        logger.debug("Set the resource bundle...");
        
    }
    
    /** demonstrates the basic level based logging methods
     * @param name name to say hello to
     */
    public void doLogging(String name) {
        logger.debug("Entered the doLogging method..");
        String str = "Hello ";
        String output = null;
        
        if(name == null) {
            output = "Anonymous";
            logger.warn("No name passed, set to anonymous...");
        }else {
            output = str.concat(name);
            logger.info("Constructe the string object..."+output);
        }
        
        logger.info("printing the message...");
        logger.debug("Exiting the doLogging method...");
    }
    
    /** demonstrates the localized logging methods
     */
    public void doLocalizedLogging() {
        
        logger.l7dlog(Level.DEBUG, "Entry", null);
        logger.l7dlog(Level.DEBUG, "Exit", null);
    }
    
    public static void main(String args[]) {
        String name = args[0];
        String rbName = args[1];
        LoggerDemo demo = new LoggerDemo(rbName);
        demo.doLogging(name);
        demo.doLocalizedLogging();
    }
    
}
