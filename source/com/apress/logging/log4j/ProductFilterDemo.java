/*
 * ProductFilterDemo.java
 */

package com.apress.logging.log4j;

import org.apache.log4j.Logger;
import com.apress.business.CustomerOrder;
import com.apress.logging.log4j.filter.ProductFilter;
import com.apress.logging.log4j.renderer.OrderRenderer;
public class ProductFilterDemo 
{
    private static Logger logger = Logger.getLogger(ProductFilterDemo.class.getPackage().getName());
    
    /** Creates a new instance of ProductFilterDemo */
    public ProductFilterDemo() {
    }
    
    public void processOrder(CustomerOrder order)
    {
        logger.info(order);
    }
    
    public static void main(String args[])
    {
        CustomerOrder order1 = new CustomerOrder("Beer", 101, 20);
        CustomerOrder order2 = new CustomerOrder("Lemonade", 95, 10);
        CustomerOrder order3 = new CustomerOrder("Chocolate", 223, 5);
        
        ProductFilterDemo demo = new ProductFilterDemo();
        demo.processOrder(order1);
        demo.processOrder(order2);
        demo.processOrder(order3);
    }
    
}
