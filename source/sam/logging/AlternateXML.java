package sam.logging;

import java.util.logging.*;

public class AlternateXML
{

 public static void main(String args[])
 {
   try
   {
     FileHandler handler = new FileHandler("alterxml.xml");
    Logger logger = Logger.getLogger("sam.logging");
    logger.addHandler(handler);
    logger.log(Level.INFO, "alternative xml");
   }catch(Exception e)
   {
     e.printStackTrace();
   }
  }

}