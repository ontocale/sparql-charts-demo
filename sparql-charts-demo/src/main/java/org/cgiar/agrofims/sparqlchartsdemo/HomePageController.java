package org.cgiar.agrofims.sparqlchartsdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePageController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String QUERIES_DIR = "/queries/";

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("query1", loadResource(QUERIES_DIR + "query1.rq"));
        request.setAttribute("query2", loadResource(QUERIES_DIR + "query2.rq"));
        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);        
    }
    
    protected String loadResource(String resourcePath) {
        InputStream inputStream = this.getClass().getResourceAsStream(
                resourcePath);
        StringBuffer fileContents = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String ln;
            while ( (ln = reader.readLine()) != null) {
                fileContents.append(ln).append('\n');
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to load " + resourcePath, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return fileContents.toString();
    }
}
