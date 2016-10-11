/**
 *File: LoggingDemo.java
 */
package com.apress.logging.log4j;

import org.apache.log4j.Logger;

public class LoggingDemo
{
    private static Logger logger = Logger.getLogger("APPLICATION1");
    public LoggingDemo()
    {
    }

    public void doLogging()
    {
        logger.info("INFO MESSAGE...");
        logger.error("ERROR MESSAGE...");

    }

    public static void main(String args[])
    {
        LoggingDemo demo = new LoggingDemo();
        demo.doLogging();
    }
}
