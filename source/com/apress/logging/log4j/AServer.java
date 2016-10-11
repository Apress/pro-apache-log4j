/*
 * AServer.java
 *
 * Created on 16 September 2002, 18:59
 */

package com.apress.logging.log4j;

import org.apache.log4j.*;
public class AServer implements Runnable{
    
    private String client = null;
     Thread t = null;
    /** Creates a new instance of AServer */
    public AServer() {
        
    }
    /**
     *
     * @param clientId
     */
    public  void runServer(String clientId)
    {
        this.client = clientId;
        t  =new Thread(this);       
        t.start();
        System.out.println("** started thread with : "+client+" with priority: "+t.getPriority());

    }
    
    public void run() {
        System.out.println("Done your job client "+client);
    }
    
}
