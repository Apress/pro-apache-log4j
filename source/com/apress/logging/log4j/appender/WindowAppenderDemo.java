
package com.apress.logging.log4j.appender;

import org.apache.log4j.Logger;

public class WindowAppenderDemo {
    
    private static Logger logger1 = Logger.getLogger("Logger1");
    private static Logger logger2 = Logger.getLogger("Logger2");
    
    /** Creates a new instance of WindowAppenderDemo */
    public WindowAppenderDemo() {
    }
    
    public void doLogging()
    {
        logger1.info("Message from logger1");
        logger2.info("Message from logger2");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WindowAppenderDemo demo = new WindowAppenderDemo();
        demo.doLogging();
        AnotherClass anc = new AnotherClass();
        anc.logAnotherMessage();
        //logger1.getAppender("WINDOW").close();
    }
    
}
