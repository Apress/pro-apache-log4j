
package sam.logging.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.logging.LogRecord;

public interface Logging extends Remote
{
	public void logMessage(LogRecord record) throws RemoteException;
}

