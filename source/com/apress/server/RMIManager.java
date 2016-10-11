/*
 * RMIManager.java
 *This is a manager class which starts up the ServerComponent
 *and the RemoteLoggingServer services by binding them to 
 *the rmiregistry at port 1099.
 *It starts or stops the services depending on a command line
 *option "start"/"stop".
 * 
 */

package com.apress.server;

import java.rmi.registry.*;
import java.rmi.Naming;
import sam.logging.rmi.RemoteLoggingServer;
public class RMIManager {
    
    /** Creates a new instance of RMIManager */
    public RMIManager() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if(args[0].equalsIgnoreCase("start")) {
                //binding the services by creating a registry at port 1099(default)
                Registry reg = LocateRegistry.createRegistry(1099);
                Naming.rebind("rmi://oemcomputer/AppServer", new ServerComponent());
                Naming.rebind("rmi://oemcomputer/LoggingServer", new RemoteLoggingServer());
                System.out.println("AppServer started...");
            }else if(args[0].equalsIgnoreCase("stop")) {
                //unbinding the services
                Naming.unbind("rmi://oemcomputer/AppServer");
                Naming.unbind("rmi://oemcomputer/LoggingServer");
                System.exit(1);
            }
            
        }catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    
}
