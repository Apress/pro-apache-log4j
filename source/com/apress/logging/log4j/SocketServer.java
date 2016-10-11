package com.apress.logging.log4j;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;

import org.apache.log4j.spi.LoggingEvent;

public class SocketServer implements Runnable{
    
    private String portNumber = null;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private LoggingEvent event = null;
    /** Creates a new instance of SocketServer */
    public SocketServer(String portNumber) {
        this.portNumber = portNumber;
        try {
            //listen to the port specified
            serverSocket = new ServerSocket(Integer.parseInt(portNumber));
            socket = serverSocket.accept();
            //creating a ObjectInputStream from the socket input stream
            inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            new Thread(this).start();
        }catch(Exception e) {
            System.out.println("Error: "+e.toString());
        }
    }
    
    
    public void run() {
        try {
            
            while(true) {
                
                //cast back to the LoggingEvent object
                event = (LoggingEvent)inStream.readObject();
                //print the mesage and logger name in this logging event
                System.out.println("THE LOGGER NAME: "+event.getLoggerName());
                System.out.println("THE MESSAGE: "+event.getMessage().toString());
            }
        }catch(Exception e) {
            System.out.println("Error: here"+e.toString());
        }
    }
    
    public static void main(String args[]) {
        String port = args[0];
        new SocketServer(port);
        
    }
    
}
