package sam.logging;

import java.util.logging.*;


public class CustomFormatter extends Formatter
{

 public CustomFormatter()
 {
 }

/**
 This method formats the given log record, in a java properties file style
*/
 

 public synchronized String format(LogRecord record)
 {
  
   String methodName = record.getSourceMethodName();
   String message = record.getMessage();
   StringBuffer buffer = new StringBuffer(50);
   buffer.append(methodName);
   buffer.append("=");
   buffer.append(message);

   return buffer.toString();
  }

  
}
   