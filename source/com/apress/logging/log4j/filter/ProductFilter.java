/*
 * ProductFilter.java
 *
 * Created on 05 October 2002, 00:00
 */

package com.apress.logging.log4j.filter;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import com.apress.business.CustomerOrder;

public class ProductFilter extends Filter
{
    
    /** Creates a new instance of ProductFilter */
    public ProductFilter() {
    }
    
    public int decide(LoggingEvent event)
    {
        int result=this.ACCEPT;
        //obtaining the message object passed through Logger
        Object message = event.getMessage();
        //checking if the message object is of currect type
        if(message instanceof CustomerOrder)
        {
            CustomerOrder order = (CustomerOrder)message;
            int productCode = order.getProductCode();
            //checking for the product code greater than 100 only
            if(productCode<100)
            {
                result = this.DENY;
            }
        }else
        {
            //this filter can ignore this, pass to next filter
            result = this.NEUTRAL;
        }
        
        return result;
    }
    
}
