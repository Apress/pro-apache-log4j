/**
 *File: CustomLevel.java
 */
package com.apress.logging.log4j;

import org.apache.log4j.Level;

public class CustomLevel extends Level {

  static public final int  TRACE_INT   = Level.DEBUG_INT - 1;
  private static String TRACE_STR  = "TRACE";


  public static final CustomLevel TRACE = new CustomLevel(TRACE_INT, TRACE_STR, 7);


  protected CustomLevel(int level, String strLevel, int syslogEquiv) {
    super(level, strLevel, syslogEquiv);
  }


  public static Level toLevel(String sArg) {
    return (Level) toLevel(sArg, CustomLevel.TRACE);
  }


  public static Level toLevel(String sArg, Level defaultValue) {

    if(sArg == null) {
      return defaultValue;
    }
    String stringVal = sArg.toUpperCase();

    if(stringVal.equals(TRACE_STR)) {
      return CustomLevel.TRACE;
    }
    return Level.toLevel(sArg, (Level) defaultValue);
  }


  public static Level toLevel(int i) {
    switch(i) {
    case TRACE_INT: return CustomLevel.TRACE;
    }
    return Level.toLevel(i);
  }

}

