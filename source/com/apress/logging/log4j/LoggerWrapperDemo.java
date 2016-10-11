
package com.apress.logging.log4j;

public class LoggerWrapperDemo
 {
    private static LoggerWrapper logger = LoggerWrapper.getLogger(LoggerWrapperDemo.class.getPackage().getName());
    public LoggerWrapperDemo()
    {

    }
    public void doLogging(String message)
    {
          logger.trace(message);
    }

    public static void main(String args[])
    {
        LoggerWrapperDemo demo = new LoggerWrapperDemo();
        demo.doLogging("USING LOGGER WRAPPER TO DISPLAY TRACE LEVEL MESSAGE");

    }
}
