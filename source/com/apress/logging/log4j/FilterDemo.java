

package com.apress.logging.log4j;

import org.apache.log4j.Logger;
public class FilterDemo 
{
    private static Logger logger = Logger.getLogger(FilterDemo.class.getPackage().getName());
    
    public FilterDemo() 
    {
    }
    public void doLogging()
    {
        logger.debug("DEBUG MESSAGE..");
        logger.info("INFO MESSAGE..");
        logger.error("ERROR MESSAGE..");
        logger.warn("WARN MESSAGE..");
        logger.fatal("FATAL MESSAGE...");
    }
    
    public static void main(String args[])
    {
        FilterDemo demo = new FilterDemo();
        demo.doLogging();
    }
        
    
}
