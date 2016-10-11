

package com.apress.logging.log4j;

import javax.jms.*;
import org.apache.log4j.spi.LoggingEvent;

public class LogMessageListener implements MessageListener{
    
    /** Creates a new instance of LogMessageListener */
    public LogMessageListener() {
    }
    
     /**
     *This method listens to any message coming to the subscribe topic, checks if correct type
     *and prints the content.
     ***/
    public void onMessage(Message message) {
        TextMessage msg = null;
        System.out.println("Message received...");
        try {
           if(message instanceof ObjectMessage){
                System.out.println("Message recived: ");
                ObjectMessage obj =  (ObjectMessage)message;
                LoggingEvent event = (LoggingEvent)obj.getObject();
                System.out.println("The logger name: "+event.getLoggerName());
                System.out.println("The message: "+event.getMessage().toString());
            }else {
                
                System.out.println("Message of wrong type: " +
                message.getClass().getName());
            }
        } catch (JMSException e) {
            System.out.println("JMSException in onMessage(): " +
            e.toString());
        } catch (Throwable t) {
            System.out.println("Exception in onMessage():" +
            t.getMessage());
        }
    }
    
}
