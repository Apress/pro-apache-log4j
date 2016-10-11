/**
 * RemoteHandler.java
 * This Handler is designed for remote logging. It is a singleton and
 * obtains the reference of the remote logging object to log the
 * logging information
 */

package sam.logging.handler;

import java.util.logging.*;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import sam.logging.rmi.*;

public class RemoteHandler extends Handler {
    private static RemoteHandler handler = null;
    private Logging remoteLogger = null;
    private LogManager manager = null;
    private RemoteHandler(String serverName) {
        config();
        try {
            //obtain the remote server reference
            remoteLogger = (Logging)Naming.lookup(serverName);
        }catch(Exception re) {
            re.printStackTrace();
            System.exit(1);
        }
    }
    
    private void config() {
        manager = LogManager.getLogManager();
        System.out.println("in config: "+manager.getProperty("sam.logging.handler.RemoteHandler.level"));
        setLevel(Level.parse(manager.getProperty("sam.logging.handler.RemoteHandler.level")));
    }
    
    /**
     * This method returns a singleton instance of the
     * present Handler object.
     *@param serverName name of the remote server
     *@return RemoteHandler object
     */
    public static synchronized RemoteHandler getInstance(String serverName) {
        if(handler ==null) {
            handler = new RemoteHandler(serverName);
        }
        return handler;
    }
    /**
     * This is the overridden publish() method
     */
    public synchronized void publish(LogRecord record) {
        try {
            remoteLogger.logMessage(record);
        }catch(RemoteException re) {
            reportError(null, re, ErrorManager.WRITE_FAILURE);
        }
    }
    //overridden close() and publish() methods
    public void close(){ };
    public void flush(){ };
}

