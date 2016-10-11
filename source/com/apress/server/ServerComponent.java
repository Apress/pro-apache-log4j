/*
 * ServerComponent.java
 *
 * This class is the remote component over RMI which reads a specified
 *XML employee data file, converts each record to an Employee object,
 *puts them to in a java List and rturns it to the caller.
 */

package com.apress.server;

/**
 *
 * @author  Administrator
 */
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.io.File;
import java.util.Vector;
import java.util.List;
import java.util.Iterator;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.JDOMException;
import org.jdom.Element;
import java.util.logging.*;
import com.apress.business.Employee;

public class ServerComponent extends UnicastRemoteObject implements ServerInterface{

    private Document doc = null;
    private SAXBuilder builder = null;
    private Vector empList = null;
    private Logger logger = null;
    private FileHandler handler = null;
    /** Creates a new instance of ServerComponent */
    public ServerComponent() throws RemoteException {
        super();
        builder = new SAXBuilder();
        empList = new Vector();

        try {
            //obtain a logger instance
            logger = Logger.getLogger("com.apress.server");
            //creating a FileHandler object with pattern
            handler = new FileHandler("%h/employeeLog%g.out");
            //setting the hnadler to the logger
            logger.addHandler(handler);
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * This method reads the specified XML document from the current working
     * directory. It creates a list of Employee object out of the XML data
     * file and return it to the caller.
     * @param docId the name of the XML document file
     * @return the list of the Employee object
     * @exception RemoteException in case there is a problem in reading the file
     */
    public List readDocument(String docId) throws RemoteException {
        logger.entering("com.apress.server.ServerComponent", "readDocument");
        String name= null;
        String code=null;
        String dept = null;
        String age = null;
        try {
            //obtaining a DOM instance from the document
            doc = builder.build(new File(docId));
            //finding the root element
            Element root = doc.getRootElement();
            //getting all the children
            List list = root.getChildren();
            Iterator iterator = list.iterator();
            //iterating through the records and creating the Employee objects
            while(iterator.hasNext()) {
                Element element = (Element)iterator.next();
                name = element.getChildText("name");
                code = element.getChildText("code");
                dept = element.getChildText("dept");
                age = element.getChildText("age");
                Employee emp = new Employee();
                emp.setName(name);
                emp.setCode(Integer.parseInt(code));
                emp.setAge(Integer.parseInt(age));
                emp.setDepartment(dept);
                empList.add(emp);
            }
        }catch(JDOMException jdome) {
         logger.severe("Exception in parsing the XML file: "+jdome.toString());
        }
        logger.exiting("com.apress.server.ServerComponent", "readDocument");

        return empList;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerComponent comp = new ServerComponent();
            Naming.bind("rmi://oemcomputer/AppServer", comp);
            System.out.println("Server started...");
            System.out.println("Current dir: "+System.getProperty("current.dir"));
            List list = comp.readDocument("100");
            Iterator it = list.iterator();
            while(it.hasNext()) {
                System.out.println("Name: "+ ((Employee)it.next()).getName());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
