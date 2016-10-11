/*
 * SimpleLogging.java
 *
 * Created on 17 September 2002, 20:07
 */

package com.apress.logging.log4j;

import org.apache.log4j.*;

public class SimpleLogging {
    
    /** Creates a new instance of SimpleLogging */
    public SimpleLogging() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(SimpleLogging.class.getPackage().getName());
        logger.info("Hello this is a info message");
    }
    
  
}
