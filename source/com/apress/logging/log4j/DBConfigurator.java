/**
 *File: DBConfigurator.java
 */
package com.apress.logging.log4j;

import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.LogLog;

import java.net.URL;
import java.util.Properties;

public class DBConfigurator implements Configurator
{
    private String dbURL = null;
    private String dbUser = null;
    private String dbPwd = null;
    private String dbDriver = null;
    private DBConfigLoader configLoader = null;
    private Properties log4jProps = null;

    public DBConfigurator()
    {
        configLoader = new DBConfigLoader();
    }

    public void doConfigure(URL url, LoggerRepository repos)
    {
        Properties props = new Properties();
        try
        {
        //collect the db access information from the config file
        props.load(url.openStream());
        dbURL = (String)props.get("DB_URL");
        dbUser = (String)props.get("DB_USER");
        dbPwd = (String)props.get("DB_PWD");
        dbDriver = (String)props.get("DB_DRIVER");

        //configure the config loader
        configLoader.setDbDriver(dbDriver);
        configLoader.setDbUrl(dbURL);
        configLoader.setDbUser(dbUser);
        configLoader.setDbPwd(dbPwd);

        //get the log4j properties
        log4jProps = configLoader.getConfigData();
        //use the PropertyConfigurator to configure
        PropertyConfigurator.configure(log4jProps);
        }catch(Exception e)
        {
            LogLog.error("could not load the configuration ", e);
        }

    }
}
