/*
 * FileBasedLoggingDemo.java
 */

package com.apress.logging.log4j;

import org.apache.log4j.*;

/** This class performs a file based logging. It creates a thread
 * to repeatedly log message to a particular file.
 */
public class FileBasedLoggingDemo implements Runnable{
    
    private static Logger logger = Logger.getLogger(FileBasedLoggingDemo.class.getPackage().getName());
    /** Creates a new instance of FileBasedLoggingDemo */
    public FileBasedLoggingDemo() 
    {
    }
    
    /** This method is called by the application. This method creates
     * a new thread to start logging
     */    
    public void doLogging() 
    {
        Thread t = new Thread(this);
        t.start();   
    }
 
   
    /** The thread's run() method, which does repeated logging
     * at an interval of 60secs.
     */    
    public void run() 
    {
        int count=1;
        while(true) {
            //logging information
            try {
                logger.debug("Logging the information..."+count);
                Thread.sleep(60*1000);
                count++;
            }catch(Exception e) {
                logger.warn("Exception occured", e);
            }
        }
    }
    /** the main method
     */    
    public static void main(String args[]) 
    {
        FileBasedLoggingDemo demo = new FileBasedLoggingDemo();
        demo.doLogging();
    }
    
}
