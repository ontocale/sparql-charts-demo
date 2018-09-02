package org.cgiar.agrofims.sparqlchartsdemo;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URIBuilder;

public class GenerateQueryController extends HomePageController {
   
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String condition1a = request.getParameter("condition1a");
        String condition1b = request.getParameter("condition1b");
        String condition2a = request.getParameter("condition2a");
        String condition2b = request.getParameter("condition2b");        ;
        String q = loadResource(QUERIES_DIR + "queryBase.rq");         
        q = bind(q, "column1Label", columnLabel(condition1a) + columnLabel(condition1b));
        q = bind(q, "column2Label", columnLabel(condition2a) + columnLabel(condition2b));
        q = bind(q, "oneProcessDetails", processDetails(condition1a, "oneA") + processDetailsOrder2(condition1b, "oneB"));
        q = bind(q, "twoProcessDetails", processDetails(condition2a, "twoA") + processDetailsOrder2(condition2b, "twoB"));
        try {
            URIBuilder redirectBuilder = new URIBuilder("charts-from-csv/index.html");            
            redirectBuilder.addParameter("q", q);
            response.sendRedirect(redirectBuilder.build().toString());
        } catch (URISyntaxException e) {
            throw new ServletException(e);
        }
    }
    
    String columnLabel(String condition) {
        String[] parts = condition.split("\\|");
        String salientPart;
        if(!parts[3].isEmpty()) {
            salientPart = parts[3];
        } else {            
            salientPart = parts[1];
        }
        return salientPart.replaceAll("\\W", "");
    }
    
    String processDetails(String condition, String prefix) {
        StringBuilder details = new StringBuilder();
        String[] parts = condition.split("\\|");
        details.append("  ").append("?").append(prefix).append("Process a <").append(parts[0]).append("> . \n");        
        if(!parts[2].isEmpty()) {
            details.append("  ?").append(prefix).append("participant a <").append(parts[2]).append("> . \n");
            details.append("  ").append("?").append(prefix).append("Process obo:RO_0000057 ?").append(prefix).append("participant . \n");
        }
        details.append("  ?").append(prefix).append("Process obo:BFO_0000066 ?plot . \n");
        return details.toString();
    }

    // Funky query optimization
    String processDetailsOrder2(String condition, String prefix) {
        StringBuilder details = new StringBuilder();
        String[] parts = condition.split("\\|");
        details.append("  ?").append(prefix).append("Process obo:BFO_0000066 ?plot . \n");        
        if(!parts[2].isEmpty()) {
            details.append("  ").append("?").append(prefix).append("Process obo:RO_0000057 ?").append(prefix).append("participant . \n");
            details.append("  ?").append(prefix).append("participant a <").append(parts[2]).append("> . \n");
        }
        details.append("  ").append("?").append(prefix).append("Process a <").append(parts[0]).append("> . \n");
        return details.toString();
    }

    
    private String bind(String origString, String varName, String value) {
        return origString.replaceAll("\\$\\{" + varName + "\\}", value);
    }
    
}
