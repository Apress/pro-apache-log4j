package com.apress.logging.log4j.appender;

import org.apache.log4j.jdbc.JDBCAppender;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.PatternLayout;
import java.util.StringTokenizer;

public class CustomJDBCAppender extends JDBCAppender {
    
    /** Holds value of property values. */
    private String values;
    
    /**the prepared statement object**/
    private PreparedStatement stmt = null;
    
    /** Holds value of property preparedSQL. */
    private String preparedSQL;
    
    /** Creates a new instance of CustomJDBCAppender */
    public CustomJDBCAppender() {
    }
  
    public void append(LoggingEvent event) {
        buffer.add(event);
        if(buffer.size()>=bufferSize) {
            flushBuffer();
        }
    }

    /**
     *overridden method from the JDBCAppender. This method sets the parameters for the prepared statement
     *before execuing the statement
     **/
    public void execute(String sql) throws SQLException {
        PreparedStatement stmt = getPreparedStatement();
        StringTokenizer tokenizer = new StringTokenizer(sql, ",");
        int i=1;
        while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            stmt.setString(i, token);
            i++;
        }
        stmt.executeUpdate();
    }
    
    /**
     * This method obtains the prepared statement object
     **/
    private PreparedStatement getPreparedStatement() throws SQLException {
        //resuse the getConnection() method from the super class
        Connection conn = getConnection();
        if(stmt==null) {
            stmt = conn.prepareStatement(getPreparedSQL());
        }
        return stmt;
    }
    
    /** Getter for property values.
     * @return Value of property values.
     */
    public String getValues() {
        return this.values;
    }
    
    /** Setter for property values.
     * @param values New value of property values.
     */
    public void setValues(String values) {
        PatternLayout layout = new PatternLayout(values);
        this.setLayout(layout);
        this.values = values;
    }
    
    /** Getter for property preparedSQL.
     * @return Value of property preparedSQL.
     */
    public String getPreparedSQL() {
        return this.preparedSQL;
    }
    
    /** Setter for property preparedSQL.
     * @param preparedSQL New value of property preparedSQL.
     */
    public void setPreparedSQL(String preparedSQL) {
        this.preparedSQL = preparedSQL;
    }
    
}
