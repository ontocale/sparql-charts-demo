package org.cgiar.agrofims.sparqlchartsdemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileServlet extends HomePageController {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String resourceName = request.getParameter("resource");
        if(resourceName == null) {
            throw new ServletException("URL parameter 'resource' must be specified");
        }
        String payload = loadResource(QUERIES_DIR + resourceName);
        response.setContentType("text/plain");
        response.getWriter().write(payload);        
    }
    
}
