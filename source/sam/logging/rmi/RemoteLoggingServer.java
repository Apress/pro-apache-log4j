/**
 * RemoteLoggingServer.java
 * This is a Remote Logging server listening to any logging request
 * and logging the information with FileHandler in a file.
 */

package sam.logging.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;

import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;

public class RemoteLoggingServer extends UnicastRemoteObject implements Logging {
    private Logger logger = null;
    private FileHandler handler = null;
    private String defaultPattern = null;
    private String defaultAppend = null;
    private String defaultLevel = null;
    private LogManager manager = LogManager.getLogManager();;
    
    public RemoteLoggingServer() throws RemoteException {
        super();

        defaultPattern = manager.getProperty("java.util.logging.FileHandler.pattern");
        defaultAppend = manager.getProperty("java.util.logging.FileHandler.append");
        setLoggerConfig(defaultPattern, new Boolean(defaultAppend).booleanValue());
    }
    /**
     * constructor with the file handler pattern and append mode
     */
    public RemoteLoggingServer(String pattern, boolean append) throws RemoteException {
        super();
        setLoggerConfig(pattern, append);
        
    }
    
    private void setLoggerConfig(String pattern, boolean append) {
        try {
            //obtain a package specific logger
            logger = Logger.getLogger("sam.logging.rmi");
            //create the file handler with the given pattern
            handler = new FileHandler(pattern, append);
            //associate the handler with the logger
            logger.addHandler(handler);
            
            //setting the level of the logger via remote handler property
            defaultLevel = manager.getProperty(RemoteLoggingServer.class.getName()+".level");
            logger.setLevel(Level.parse(defaultLevel!=null?defaultLevel:"INFO"));
            
            //debug messages
            logger.info("started with logging level: "+logger.getLevel());
       
        }catch(Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }
    }
    
    
    /**
     * This method is the core method which publishes the logging
     * information to the desired file.
     */
    public void logMessage(LogRecord record) throws RemoteException {
        try {
            //logging the message
            logger.log(record);
        }catch(Exception e) {
            throw new RemoteException(e.toString());
        }
    }
    
    public static void main(String args[]) {
        //pattern to store in the current directory in log0.out,log1.out etc.
        String pattern = "log%u.out";
        boolean append = true;
        RemoteLoggingServer server = null;
        try {
            server = new RemoteLoggingServer();
            Naming.bind("rmi://oemcomputer/LoggingServer", server);
            System.out.println("Server started...");
        }catch(Exception re) {
            re.printStackTrace();
        }
    }
}

