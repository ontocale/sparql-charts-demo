package org.cgiar.agrofims.sparqlchartsdemo;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URIBuilder;

public class GenerateQueryController extends HomePageController {

    // TODO add query resource file with replaceable parameters
    // build query based on submitted URL parameters
   
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String condition1a = request.getParameter("condition1a");
        String condition1b = request.getParameter("condition1b");
        String condition2a = request.getParameter("condition2a");
        String condition2b = request.getParameter("condition2b");        
        try {
            URIBuilder redirectBuilder = new URIBuilder("charts-from-csv/index.html");
            redirectBuilder.addParameter("q", condition1a + "\n" + condition1b + "\n" + condition2a + "\n" + condition2b);
            response.sendRedirect(redirectBuilder.build().toString());
        } catch (URISyntaxException e) {
            throw new ServletException(e);
        }
    }
    
}
