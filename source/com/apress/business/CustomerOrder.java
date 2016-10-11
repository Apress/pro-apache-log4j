/*
 * CustomerOrder.java
 */

package com.apress.business;

public class CustomerOrder {
    
    /** Holds value of property productName. */
    private String productName;
    
    /** Holds value of property productCode. */
    private int productCode;
    
    /** Holds value of property productPrice. */
    private int productPrice;
    
    /** Creates a new instance of CustomerOrder */
    public CustomerOrder() {
    }
    
    public CustomerOrder(String name, int code, int price)
    {
        this.productCode = code;
        this.productPrice = price;
        this.productName = name;
    }
        
    
    /** Getter for property productName.
     * @return Value of property productName.
     */
    public String getProductName() {
        return this.productName;
    }
    
    /** Setter for property productName.
     * @param productName New value of property productName.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    /** Getter for property productCode.
     * @return Value of property productCode.
     */
    public int getProductCode() {
        return this.productCode;
    }
    
    /** Setter for property productCode.
     * @param productCode New value of property productCode.
     */
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }
    
    /** Getter for property productPrice.
     * @return Value of property productPrice.
     */
    public int getProductPrice() {
        return this.productPrice;
    }
    
    /** Setter for property productPrice.
     * @param productPrice New value of property productPrice.
     */
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    
}
