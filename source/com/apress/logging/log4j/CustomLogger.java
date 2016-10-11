/*
 * CustomLogger.java
 *
 * Created on 02 December 2002, 21:03
 */

package com.apress.logging.log4j;

import org.apache.log4j.Logger;
import com.apress.logging.log4j.CustomLevel;

public class CustomLogger extends Logger {
    private String FQCN = CustomLogger.class.getName()+".";
    private static CustomLoggerFactory factory = new CustomLoggerFactory();
    public CustomLogger(String name) {
        super(name);
    }
    
    public void trace(Object message) {
        //call the super log method with throwable instance as null
        super.log(FQCN, CustomLevel.TRACE, message, null);
        
    }
    public static Logger getLogger(String name) {
        return Logger.getLogger(name, factory);
    }
}
