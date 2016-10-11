
package com.apress.logging.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

public class LoggerWrapper
 {
    private final String name;
    private Logger log;

    protected LoggerWrapper(String name)
    {
        this.name = name;
        log = Logger.getLogger(name);
    }

    public String getName()
    {
        return name;
    }

    public boolean isTraceEnabled()
    {
        if(!log.isEnabledFor(CustomLevel.TRACE))
            return false;
        else
            return CustomLevel.TRACE.isGreaterOrEqual(log.getEffectiveLevel());
    }

    public void trace(Object message)
    {
        log.log(CustomLevel.TRACE, message);
    }

    public void trace(Object message, Throwable t)
    {
        log.log(CustomLevel.TRACE, message, t);
    }

    public boolean isDebugEnabled()
    {
        Level p = Level.DEBUG;
        if(!log.isEnabledFor(p))
            return false;
        else
            return p.isGreaterOrEqual(log.getEffectiveLevel());
    }

    public void debug(Object message)
    {
        log.log(Level.DEBUG, message);
    }

    public void debug(Object message, Throwable t)
    {
        log.log(Level.DEBUG, message, t);
    }

    public boolean isInfoEnabled()
    {
        Level p = Level.INFO;
        if(!log.isEnabledFor(p))
            return false;
        else
            return p.isGreaterOrEqual(log.getEffectiveLevel());
    }

    public void info(Object message)
    {
        log.log(Level.INFO, message);
    }

    public void info(Object message, Throwable t)
    {
        log.log(Level.INFO, message, t);
    }

    public void warn(Object message)
    {
        log.log(Level.WARN, message);
    }

    public void warn(Object message, Throwable t)
    {
        log.log(Level.WARN, message, t);
    }

    public void error(Object message)
    {
        log.log(Level.ERROR, message);
    }

    public void error(Object message, Throwable t)
    {
        log.log(Level.ERROR, message, t);
    }

    public void fatal(Object message)
    {
        log.log(Level.FATAL, message);
    }

    public void fatal(Object message, Throwable t)
    {
        log.log(Level.FATAL, message, t);
    }

    public void log(Level p, Object message)
    {
        log.log(p, message);
    }

    public void log(Level p, Object message, Throwable t)
    {
        log.log(p, message, t);
    }

    public static LoggerWrapper getLogger(String name)
    {
        LoggerWrapper logger = new LoggerWrapper(name);
        return logger;
    }

    public static LoggerWrapper getLogger(Class clazz)
    {
        LoggerWrapper logger = new LoggerWrapper(clazz.getName());
        return logger;
    }

}
