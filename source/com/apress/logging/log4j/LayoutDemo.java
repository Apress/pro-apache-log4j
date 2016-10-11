

package com.apress.logging.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.TTCCLayout;
import org.apache.log4j.NDC;

public class LayoutDemo {
    private static Logger logger = Logger.getLogger(LayoutDemo.class.getPackage().getName());
    private ConsoleAppender appender = null;
    private TTCCLayout layout = null;
    
    /** Creates a new instance of LayoutDemo */
    public LayoutDemo() {
        /*
        //set the parent additivity to false
        logger.setAdditivity(false);
        
        //initialize the layout
        layout = new TTCCLayout("yyyy-MM-dd");
        
        //initialize the console appender with the layout
        appender = new ConsoleAppender(layout,"System.out");
        
        //adding the console appender to the logger
        logger.addAppender(appender);
        */
    }
    
    public void computeSquareRoot(double number) {
        NDC.push(new Double(number).toString());
        double sqrt = Math.sqrt(number);
        logger.info("The sqrt value: "+sqrt);
        NDC.pop();
        
    }
    
    public static void main(String args[]) {
        LayoutDemo demo = new LayoutDemo();
        demo.computeSquareRoot(22);
        demo.computeSquareRoot(44);
    }
}
