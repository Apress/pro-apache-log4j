/*
 * LoggingServlet.java
 *
 */


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import org.apache.log4j.*;

public class LoggingServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoggingServlet.class);
    
    public void init()throws ServletException
    {
        super.init();
        String configFile = getInitParameter("log4j-conf");
        PropertyConfigurator.configure(configFile);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)
                       throws IOException, ServletException
    {
        String remoteAddress = req.getRemoteAddr();
        String remoteHost = req.getRemoteHost();
        
        //pushing to NDC
        NDC.push(remoteHost);
        //mapping in MDC
        MDC.put("remoteAddress", remoteAddress);
        logger.info("invoked the LoggingServlet...");
        PrintWriter writer = res.getWriter();
        writer.println("Check your web server console...");
        writer.flush();
        writer.close();
    }
        
                           
    
}
