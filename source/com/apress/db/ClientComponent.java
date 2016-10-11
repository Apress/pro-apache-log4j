/*
 * ClientComponent.java
 * This is the client component of the employee data management system.
 * This class is responsible for the following:
 * <li> It makes a remote call to the Server Component to obtain a list
 * of the employee object </li>
 * <li> It makes a JDBC operation to insert the obtained data to the
 * database</li>
 * <li> It uses another remote logging component RemoteLoggingServer and
 * passes all the log information for remote logging.
 *
 */

package com.apress.db;


import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.logging.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import com.apress.business.Employee;
import com.apress.server.ServerInterface;
import sam.logging.handler.RemoteHandler;
import sam.logging.handler.WindowHandler;

public class ClientComponent {

    private String driverName = null;
    private String jdbcURL = null;
    private String userName = null;
    private String password = null;
    private Properties props = null;
    private Logger logger = null;
    private RemoteHandler remoteHandler = null;
    private WindowHandler windowHandler = null;
    private ServerInterface server = null;

    /** Creates a new instance of ClientComponent */
    public ClientComponent(String propFile) {
        props = new Properties();
        try {
            props.load(new FileInputStream(propFile));
            driverName = props.getProperty("DriverName");
            jdbcURL = props.getProperty("URL");
            userName = props.getProperty("userName");
            password = props.getProperty("Password");
            System.out.println("driver name: "+driverName);
            System.out.println("url: "+jdbcURL);
            System.out.println("user name: "+userName);
            System.out.println("password: "+password);

            //obtain the reference to the rmi server
            server = (ServerInterface)Naming.lookup("rmi://oemcomputer/AppServer");

            //obtain an instance of the logger
            logger = Logger.getLogger("com.apress.db");
            //obtain an instance of the remote handler
            //talking to the logging server named LoggingServer
            remoteHandler = RemoteHandler.getInstance("rmi://oemcomputer/LoggingServer");
            //getting an instance of the window handler
            windowHandler = WindowHandler.getInstance();
            //adding the remote handler to the logger
            logger.addHandler(remoteHandler);
            //adding the window handler to the logger
            logger.addHandler(windowHandler);
            //set the logger level same as the handler level
            logger.setLevel(remoteHandler.getLevel());

            //configuration done
            logger.config("client configuration done...");
        }catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.out.println("Could not find the properties file: "+propFile);
            System.exit(1);
        }catch(IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Problem in reading the prop file: "+propFile);
            System.exit(1);
        }catch(NotBoundException re)
        {
            logger.severe("servert named AppServer is not bound "+re.toString());
        }

    }
    //privat helper method for obtaining a connection
    private Connection makeConnection()throws SQLException {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(jdbcURL, userName, password);
        }catch(ClassNotFoundException cnfe) {
            System.out.println("Class not found: "+driverName);
            System.exit(1);
        }catch(SQLException sqle) {
            throw sqle;
        }
        System.out.println("evaluation: "+(logger.getLevel().intValue()<Level.INFO.intValue()));
        logger.info("Connection obtained as: "+conn);
        return conn;
    }

     //private method for closing the connecton
    private void closeConnection(Connection conn) {
        try {
            if(conn !=null)
                conn.close();
        }catch(SQLException sqle) {
            //WARNING
            logger.warning("Could not close the connection..");
        }
        logger.info("connection closed..");
    }

    /**
     * Ths is a private method responsible for writing the employee object data
     * to the database.
     * @param conn the Connection object
     * @param employee the Employee object
     * @exception SQLException is thrown in case there is a problem
     */
    private void insertData(Connection conn, Employee employee) throws SQLException {
        logger.entering(ClientComponent.class.getName(), "insertData()");
        Statement stmt =null;
        StringBuffer queryString = new StringBuffer("INSERT INTO EMPLOYEE VALUES(");
        try {
            stmt = conn.createStatement();
            queryString.append(employee.getCode());
            queryString.append(",'");
            queryString.append(employee.getName());
            queryString.append("','");
            queryString.append(employee.getDepartment());
            queryString.append("',");
            queryString.append(employee.getAge());
            queryString.append(")");

            logger.info("The queryString is ; "+queryString.toString());
            stmt.execute(queryString.toString());
            logger.fine("inserted data..");
            conn.commit();
            stmt.close();
        }catch(SQLException sqle) {
            throw sqle;
        }

        logger.exiting(ClientComponent.class.getName(), "insertData()");

    }

    /**
     * This method is responsible for obtaining the employee data from the specified
     * file and write it to the database
     * @param docId the document name in the remote place containing the XML data.
     */
    public void setEmployeeData(String docId) throws SQLException {
        Connection conn = null;
        List empList = null;
        Iterator iterator = null;
        logger.entering(ClientComponent.class.getName(), "setEmployeeData()");
        try {
             conn = makeConnection();
             empList = server.readDocument(docId);
             iterator = empList.iterator();
             while(iterator.hasNext())
             {
                 Employee emp = (Employee)iterator.next();
                 insertData(conn, emp);
             }
             logger.info("Job done...");
        }catch(SQLException sqle) {
            logger.severe("SQL problem: "+sqle.toString());
        }catch(RemoteException re)
        {
            logger.severe("Remote exception in invoking the method readDocument() "+re.toString());
        }finally {
            closeConnection(conn);
        }
        logger.exiting(ClientComponent.class.getName(), "setEmployeeData()");

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String propFile = args[0];
        String fileName = args[1];

        ClientComponent comp = new ClientComponent(propFile);
        try {
            comp.setEmployeeData(fileName);
        }catch(SQLException sqle) {
            sqle.printStackTrace();
        }

    }

}
