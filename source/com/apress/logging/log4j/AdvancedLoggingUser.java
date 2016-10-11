
package com.apress.logging.log4j;

public class AdvancedLoggingUser implements Runnable{
    
    private AdvancedLogging logging = null;
    /** Creates a new instance of AdvancedLoggingUser */
    public AdvancedLoggingUser() {
        logging = new AdvancedLogging("Sam");
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public void run() {
        
        while(true) {
            try {
                logging.depositBalance(200.00);
                Thread.sleep(2000);
            }catch(Exception e) {
                System.out.println("Error in thread: "+e.toString());
            }
        }
    }
    
    public static void main(String args[]) {
        AdvancedLoggingUser user = new AdvancedLoggingUser();
    }
    
}
