/*
 * ServerInterface.java
 *
 * This is the remote interface for the server side component
 * which will read the XML employe data file
 */

package com.apress.server;

/**
 *
 * @author  Administrator
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote
{
    /**
     * This is the remote method declaration
     * @param docId the document
     * @return list the list containing Employee objects
     * @exception RemoteException
     */
    public List readDocument(String docId) throws RemoteException;

}
