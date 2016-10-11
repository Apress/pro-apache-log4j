/**
 *File: CustomLogManager.java
 */
package com.apress.logging.log4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Enumeration;

public class CustomLogManager extends LogManager
{
    static
    {
        System.out.println("IN THE CUSTOM MANAGER STATIC..");
    }

    public static void main(String args[])
    {
       DBConfigLoader config = new DBConfigLoader();
        //config.doConfigure(null, null);

        Logger logger = Logger.getLogger("DEVELOPMENT");
        System.out.println(logger);
        logger.info("Working...");
        System.out.println("repos: "+LogManager.getLoggerRepository().getCurrentLoggers());
        Enumeration enum = LogManager.getLoggerRepository().getCurrentLoggers();
        while(enum.hasMoreElements())
        {
            System.out.println( ((Logger)enum.nextElement()).getName());
        }
    }
}
