

package sam.logging;

import java.util.logging.*;

public class MemoryHandlerDemo 
{
    private ConsoleHandler handler = null;
    private MemoryHandler mHandler = null;
    private static Logger logger = Logger.getLogger("sam.logging");
    
    public MemoryHandlerDemo(int size, Level pushLevel) {
        handler = new ConsoleHandler();
        //instantiating the MemoryHandler object with the specified
        //size, pushLevel and a ConsoleHandler as target Handler
        mHandler = new MemoryHandler(handler, size, pushLevel);
        //adding the memory handler to the logger
        logger.addHandler(mHandler);
        //set the use of parent logger to false
        logger.setUseParentHandlers(false);
        
    }
    /**
     *This method publishes the log messages
     */
    public void logMessage() {
        LogRecord record1 = new LogRecord(Level.SEVERE, "This is SEVERE level message");
        LogRecord record2 = new LogRecord(Level.WARNING, "This is WARNING level message");
        
        logger.log(record1);
        logger.log(record2);
        
        /*
        //sequence that will force both the records
        logger.log(record2);
        logger.log(record1);

        //sequence that will discard one record accoriding to the size
        logger.log(record2);
        logger.log(record2);
        logger.log(record1);
        */
    }
    
    public static void main(String args[]) {
        //creating a MemoryHandler object with size limit 1000 bytes
        // and pushLevel as Level.SEVERE
        MemoryHandlerDemo demo = new MemoryHandlerDemo(2, Level.SEVERE);
        demo.logMessage();
    }
    
}

