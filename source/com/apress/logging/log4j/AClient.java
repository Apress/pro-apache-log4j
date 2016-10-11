/*
 * AClient.java
 *
 * Created on 16 September 2002, 20:18
 */

package com.apress.logging.log4j;

/**
 *
 * @author  Administrator
 */
public class AClient {
    
    /** Creates a new instance of AClient */
    public AClient() {
    }
    
    public static void main(String args[])
    {
        AServer server = new AServer();
        long start = System.currentTimeMillis();
        server.runServer("One");
        server.runServer("Two");
        long finish = System.currentTimeMillis();
        System.out.println("Total time: "+(finish-start));
    }
    
}
