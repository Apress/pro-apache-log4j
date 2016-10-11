/**
 * RemoteConfigReader.java
 * This class reads the configuration file from a remote HTTP location
 * and populates the LogManager initialization proeprties
 */

package sam.logging.config;

import java.util.logging.LogManager;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.IOException;

public class RemoteConfigReader
{
	private String urlString = "http://www.xyz.com/config.properties";
	private URL url = null;
	private URLConnection urlConn = null;
	private InputStream inStream = null;
	private LogManager manager = null;
	/**
	 * The constructor obtains a connection to the URL specified in the
	 * urlString object, obtains an InputStream on the URL and
	 * calls the readConfiguration(InputStream) of the LogManager class
	 * to perform the initialization
	 */
	public RemoteConfigReader()
	{
		try
		{
			url = new URL(urlString);
			urlConn = url.openConnection();
			inStream = urlConn.getInputStream();
			manager = LogManager.getLogManager();
			manager.readConfiguration(inStream);
		}catch(MalformedURLException mue)
		{
			System.out.println("could not open url: "+urlString);
		}catch(IOException ioe)
		{
			System.out.println("IOException occured in reading: "+urlString);
		}catch(SecurityException se)
		{
			System.out.println("Security exception occured in class RemoteConfigLoader");
		}
	}
			
		
}

