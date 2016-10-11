
package com.apress.logging.log4j;

import javax.jms.*;
import javax.naming.*;

public class JMSLogSubscriber {
    
    /** Creates a new instance of JMSLogSubscriber */
    public JMSLogSubscriber() {
    }
    
    public static void main(String args[]) {
        Context ctx;
        Topic topic;
        TopicSubscriber topicSubscriber;
        TextMessage message;
        TopicConnectionFactory topicFactory;
        TopicConnection topicConnection;
        TopicSession topicSession;
        //collect the topic name from command line
        String topicName = args[0];
        try {
            //creating a default J2EE initial context
            ctx = new InitialContext();
            //obtaining the topic connection factory
            topicFactory = (TopicConnectionFactory)ctx.lookup("TopicConnectionFactory");
            //creating the topic
            topic = (Topic)ctx.lookup(topicName);
            //opening a topic connection
            topicConnection = topicFactory.createTopicConnection();
            //creating a session to AUTO_ACKNOWLEDGE the receipt of the message
            topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            //subsdcribe to the topic
            topicSubscriber = topicSession.createSubscriber(topic);
            //custom listener to listen to the topic for any message and handle it
            LogMessageListener listener = new LogMessageListener();
            //adding the listener to this subscriber
            topicSubscriber.setMessageListener(listener);
            //start the session
            topicConnection.start();
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        
        
    }
    
}
