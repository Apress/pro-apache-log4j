
package com.apress.logging.log4j.appender;

import org.apache.log4j.Logger;

public class AnotherClass {
    private static Logger logger = Logger.getLogger("DEVELOPMENT");
    
    /** Creates a new instance of AnotherClass */
    public AnotherClass() {
    }
    
    public void logAnotherMessage()
    {
        logger.info("Message from Another class..");
    }
    
}
