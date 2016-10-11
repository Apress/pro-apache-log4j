/*
 * CustomLoggerFactory.java
 *
 * Created on 02 December 2002, 21:04
 */

package com.apress.logging.log4j;

import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.Logger;

public class CustomLoggerFactory implements LoggerFactory {
    public Logger makeNewLoggerInstance(String name) {
        return new CustomLogger(name);
    }
}
