/**
 *File: DBConfigLoader.java
 * This class reads the log4j configuration parameters
 * deifned in the database
 */
package com.apress.logging.log4j;

import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConfigLoader
{
    private Connection conn = null;
    private String dbUrl = null;
    private String dbDriver = null;
    private String dbUser = null;
    private String dbPwd = null;

    /**
     * Constructor initializing the db access params
     */
    public DBConfigLoader()
    {
        this.setDbDriver("sun.jdbc.odbc.JdbcOdbcDriver");
        this.setDbUrl("jdbc:odbc:dbdef");
        this.setDbUser("system");
        this.setDbPwd("manager");
    }

    /**
     * Sets the driver class for the db acess
     */
    private void setDriver()
    {
        try
        {
            //load the driver
            Class.forName(dbDriver);
        } catch (ClassNotFoundException cnfe)
        {
            System.out.println("Could not find the driver class " + dbDriver);
        }
    }

    /**
     * Method to obtain a db connection
     * @return Connection object
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException
    {
        System.out.println("CONNECTION PARAMS: ");
        System.out.println("DRIVER: " + dbDriver);
        System.out.println("URL: " + dbUrl);
        System.out.println("USER: " + dbUser);
        System.out.println("PWD: " + dbPwd);
        //load the driver
        setDriver();
        //getting the connection
        conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);

        return conn;

    }

    /**
     * Obtain the configuration data from the db
     * @param sql The sql to execute to get the data
     * @return ResultSet object containing the data
     * @throws SQLException
     */
    private ResultSet getConfigData(String sql) throws SQLException
    {
        //obtain a connection to the database
        conn = getConnection();
        //create a statement object to execute the query
        Statement stmt = conn.createStatement();
        //execute the query to get the resultset
        ResultSet rs = stmt.executeQuery(sql);

        return rs;
    }

    /**
     * constructs the SQL statement to execute
     * @return String the SQL statement
     */
    private String getSQL()
    {
        StringBuffer buffer = new StringBuffer("SELECT * FROM APPENDER_DEF AD, LOGGER_REPOSITORY LR WHERE ");
        //buffer.append(" LR.LOGGER_NAME='" + loggerName + "' ");
        buffer.append(" LR.APPENDER_ID=AD.APPENDER_ID");
        System.out.println("The SQL: " + buffer.toString());
        return buffer.toString();
    }

    /**
     * Loading all the configuration data in a Properties object
     * and returns the Properties object back to the caller
     */
    public Properties getConfigData()
    {
        //properties object to store the config data
        Properties props = new Properties();
        String loggerName = null;
        String loggerLevel = null;
        String appenderName = null;
        String appenderClass = null;
        String appenderLevel = null;
        String fileName = null;
        String maxFileSize = null;
        String jdbcURL = null;
        String jdbcUser = null;
        String jdbcPwd = null;
        String jdbcSQL = null;
        String layout = null;

        //get the sql to obtain the config data
        String sql = getSQL();
        try
        {
            //get the result set
            ResultSet rs = getConfigData(sql);
            while (rs.next())
            {

                //getting the appender class
                appenderClass = rs.getString("APPENDER_CLASS");
                //getting the file name properties (can be null)
                fileName = rs.getString("FILE_NAME");
                //getting the max file size (can be null)
                maxFileSize = rs.getString("MAX_FILE_SIZE");
                //getting the jdbc url   (can be null)
                jdbcURL = rs.getString("DB_URL");
                //getting the jdbc user id (can be null)
                jdbcUser = rs.getString("USER_ID");
                //getting the jdbc password (can be null)
                jdbcPwd = rs.getString("USER_PWD");
                //getting the SQL string
                jdbcSQL = rs.getString("DB_SQL");
                //getting the layout information
                layout = rs.getString("LAYOUT");
                //getting the appender name
                appenderName = rs.getString("APPENDER_NAME");
                //getting the level of the appender
                appenderLevel = rs.getString("APPENDER_LEVEL");

                //getting the logger name
                loggerName = rs.getString("LOGGER_NAME");
                //getting the logger level
                loggerLevel = rs.getString("LOGGER_LEVEL");

                //constructing the properties with key and value
                String loggerKey = "log4j.logger." + loggerName;
                String appenderKey = "log4j.appender." + appenderName;

                //properties for the logger  level and the appender
                props.put(loggerKey, loggerLevel + "," + appenderName);
                props.put(getKey(appenderKey, null), appenderClass);

                if (fileName != null)
                {
                    props.put(getKey(appenderKey, "File"), fileName);
                }
                if (maxFileSize != null)
                {
                    props.put(getKey(appenderKey, "MaxFileSize"), maxFileSize);
                }
                if (jdbcURL != null)
                {
                    props.put(getKey(appenderKey, "URL"), jdbcURL);
                }
                if (jdbcUser != null)
                {
                    props.put(getKey(appenderKey, "user"), jdbcUser);
                }
                if (jdbcPwd != null)
                {
                    props.put(getKey(appenderKey, "password"), jdbcPwd);
                }
                if (layout != null)
                {
                    props.put(getKey(appenderKey, "layout"), layout);
                }
            }
        } catch (SQLException sqle)
        {
            System.out.println("FAILED TO GET CONFIG DATA: " + sqle.toString());
            sqle.printStackTrace();

        } finally
        {
            closeConnection();
        }

        return props;
    }

    /**
     * constructs the key for the properties object
     * @param prefix the prefix to the key
     * @param suffix  the suffix to the key
     * @return
     */
    private String getKey(String prefix, String suffix)
    {
        StringBuffer buffer = new StringBuffer(prefix);

        if (suffix != null)
        {
            buffer.append(".");
            buffer.append(suffix);
        }
        //System.out.println("returning key....."+buffer.toString());
        return buffer.toString();
    }

    private void closeConnection()
    {
        try
        {
            if (conn != null)
            {
                conn.close();
            }
        } catch (SQLException sqle)
        {
            System.out.println("Problem closing the connection..");
        }
    }

    /**
     * Returns the db user name
     * @return  db user
     */

    public String getDbUser()
    {
        return dbUser;
    }

    /**
     * Sets the db user name
     * @param dbUser db user name
     */
    public void setDbUser(String dbUser)
    {
        this.dbUser = dbUser;
    }

    /**
     * Returns the database access password
     * @return the database password
     */
    public String getDbPwd()
    {
        return dbPwd;
    }

    /**
     * Sets the db access password
     * @param dbPwd  the db password
     */
    public void setDbPwd(String dbPwd)
    {
        this.dbPwd = dbPwd;
    }

    /**
     * Returns the db driver
     * @return the db driver
     */
    public String getDbDriver()
    {
        return dbDriver;
    }

    /**
     * Sets the db driver
     * @param dbDriver the db driver
     */
    public void setDbDriver(String dbDriver)
    {
        this.dbDriver = dbDriver;
    }

    /**
     * Returns the db address
     * @return   the db address
     */
    public String getDbUrl()
    {
        return dbUrl;
    }

    /**
     * Sets the database address
     * @param dbUrl  the database address
     */
    public void setDbUrl(String dbUrl)
    {
        this.dbUrl = dbUrl;
    }
}
