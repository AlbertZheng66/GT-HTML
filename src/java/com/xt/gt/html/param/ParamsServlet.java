
package com.xt.gt.html.param;

import com.xt.core.log.LogWriter;
import com.xt.gt.sys.SystemConfiguration;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * 专门为JS提供参数的Servlet
 * @author Albert
 */
public class ParamsServlet extends HttpServlet {
    public static final String PARAMS_SERVLET_ENCODING = "UTF-8";
    
    private static final long serialVersionUID = -4707708474631057937L;
    
    private final static Logger logger = Logger.getLogger(ParamsServlet.class);
    
    private String contextPath;
    
    @Override
    public void init() throws ServletException {
        super.init();
        contextPath =  getServletContext().getContextPath();
        if (!contextPath.endsWith("/")) {
            contextPath += "/";
        }
        LogWriter.info2(logger, "ParamsServlet: initing.............");
    }
    
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, java.io.IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, java.io.IOException {
        LogWriter.debug(logger, "ParamsServlet: doPost................");
        String paramName = req.getParameter("name");
        if ("contextPath".equals(paramName)) {
            write(res, contextPath);
        } else if (SystemConfiguration.getInstance().contains(paramName)){
            // 暂时不处理
        }
    }

    private void write(HttpServletResponse res, String value) throws IOException {
        value = (value == null) ? "" : value;
        res.getOutputStream().write(value.getBytes(PARAMS_SERVLET_ENCODING));
    }
}
