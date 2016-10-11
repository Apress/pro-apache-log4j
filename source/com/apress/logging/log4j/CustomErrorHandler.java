/**
 *File: CustomErrorHandler.java
 */
package com.apress.logging.log4j;

import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.Logger;
import org.apache.log4j.Appender;

import java.util.Vector;

public class CustomErrorHandler implements ErrorHandler
{
    private Appender primary;
    private Appender backup;
    private Vector loggers = new Vector();

    public CustomErrorHandler()
    {

    }

    public void activateOptions()
    {

    }

    public void setLogger(Logger logger)
    {
        if (logger != null)
        {
            loggers.add(logger);
        }
    }

    public void error(String message, Exception e, int errorCode)
    {
        error(message, e, errorCode, null);
    }

    public void error(String message)
    {
        error(message, null, 0, null);
    }

    public void error(String message, Exception e, int errorCode, LoggingEvent event)
    {
        for (int i = 0; i < loggers.size(); i++)
        {
            Logger l = (Logger) loggers.elementAt(i);
            l.removeAppender(primary);
            l.addAppender(backup);
            l.error(message, e);
        }

    }

    public void setAppender(Appender appender)
    {
        this.primary = appender;
    }

    public void setBackupAppender(Appender appender)
    {
        this.backup = appender;
    }
}
