
package sam.logging;

import java.net.*;
import java.util.logging.*;
import java.io.*;

public class LoggingServer 
{
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    
    public LoggingServer(int port) 
    {
        try 
        {
            serverSocket = new ServerSocket(port);
            System.out.println("server started: ");
            socket = serverSocket.accept();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }  
        
    }
    
    /**
     *This mehtod starts receiveing the messages
     */
    public void acceptMessage() 
    {
        try 
        {
            //get the inputStream of the received socket
            InputStream inStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String str = null;
            while( (str = reader.readLine()) !=null) 
            {
                System.out.println(str);
            }
            
        }catch(IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }
    public static void main(String args[]) 
    {
        LoggingServer server = new LoggingServer(2020);
        server.acceptMessage();
    }
}
