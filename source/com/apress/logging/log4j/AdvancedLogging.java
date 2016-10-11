
package com.apress.logging.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
public class AdvancedLogging 
{
    private static Logger logger = Logger.getLogger(AdvancedLogging.class.getPackage().getName());
    private  String userName = null;
    private double balance;
    
    /** Creates a new instance of AdvancedLogging */
    public AdvancedLogging(String user) 
    {
        this.userName = user;
    }
    /**
     *Deposit some amount
     */
    public void depositBalance(double amount)
    {
        NDC.push(userName);
        balance += amount;
        logger.info("Deposited "+amount+" new balance: "+balance);
        NDC.pop();
    }
    /**
     *withdraw some amount
     */
    public void withDraw(double amount)
    {
        NDC.push(userName);
        if(balance>=amount)
        {
            balance -= amount;
            logger.info("Withdrawn "+amount+" new balance: "+balance);
        }else
        {
            System.out.println("Not enough balance");
            logger.error("Failed to withdraw: balance: "+balance+" attempted withdraw: "+amount);
        }
        NDC.pop();
    }
 
    public static void main(String args[])
    {
        AdvancedLogging demo = new AdvancedLogging("sam");
        demo.depositBalance(100.50);
        demo.withDraw(80);
        demo.withDraw(50);
        

    }
    
}
