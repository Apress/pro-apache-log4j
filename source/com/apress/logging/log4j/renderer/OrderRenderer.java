/*
 * OrderRenderer.java
 */

package com.apress.logging.log4j.renderer;

import org.apache.log4j.or.ObjectRenderer;
import com.apress.business.CustomerOrder;

public class OrderRenderer implements ObjectRenderer
{
    private static final String separator = "-";
    
    /** Creates a new instance of OrderRenderer */
    public OrderRenderer() {
    }
    
    public String doRender(Object obj) 
    {
        StringBuffer buffer = new StringBuffer(50);
        CustomerOrder order = null;
        String productName = null;
        int productCode = 0;
        int productPrice = 0;
        //check if the instance is of correct type CustomerOrder
        if(obj instanceof CustomerOrder)
        {
            order = (CustomerOrder)obj;
            productName = order.getProductName();
            productCode = order.getProductCode();
            productPrice = order.getProductPrice();
            
            buffer.append(productName);
            buffer.append(separator);
            buffer.append(new Integer(productCode).toString());
            buffer.append(separator);
            buffer.append(new Integer(productPrice).toString());
        }
        
        return buffer.toString();
    }
    
}
