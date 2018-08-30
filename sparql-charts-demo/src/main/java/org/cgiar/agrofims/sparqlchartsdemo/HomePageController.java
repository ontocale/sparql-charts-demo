package org.cgiar.agrofims.sparqlchartsdemo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePageController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("body", "test message: x");
        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);        
    }
    
}
