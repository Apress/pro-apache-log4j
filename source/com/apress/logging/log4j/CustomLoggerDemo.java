/*
 * CustomLoggerDemo.java
 *
 * Created on 02 December 2002, 20:59
 */

package com.apress.logging.log4j;

import org.apache.log4j.Logger;

/**
 *
 * @author  Administrator
 */
public class CustomLoggerDemo {
    private static CustomLogger logger = (CustomLogger)CustomLogger.getLogger(CustomLoggerDemo.class.getPackage().getName());
    //private static CustomLogger logger = new CustomLogger("Hello");
    
    /** Creates a new instance of CustomLoggerDemo */
    public CustomLoggerDemo() 
    {
    }
    
    public void doLogging()
    {
        logger.trace("THIS IS A TRACE LEVEL MESSAGE...");
    }
    
    public static void main(String args[])
    {
        CustomLoggerDemo demo = new CustomLoggerDemo();
        demo.doLogging();
    }
        
    
}
