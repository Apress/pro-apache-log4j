/*
 * Employee.java
 *
 * Created on 11 September 2002, 11:36
 */

package com.apress.business;

public class Employee implements java.io.Serializable{
    
    /** Holds value of property name. */
    private String name;
    
    /** Holds value of property age. */
    private int age;
    
    /** Holds value of property department. */
    private String department;
    
    /** Holds value of property code. */
    private int code;
    
    /** Creates a new instance of Employee */
    public Employee() {
    }
    
    /** Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }
    
    /** Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /** Getter for property age.
     * @return Value of property age.
     */
    public int getAge() {
        return this.age;
    }
    
    /** Setter for property age.
     * @param age New value of property age.
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /** Getter for property department.
     * @return Value of property department.
     */
    public String getDepartment() {
        return this.department;
    }
    
    /** Setter for property department.
     * @param department New value of property department.
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    /** Getter for property code.
     * @return Value of property code.
     */
    public int getCode() {
        return this.code;
    }
    
    /** Setter for property code.
     * @param code New value of property code.
     */
    public void setCode(int code) {
        this.code = code;
    }
    
}
