package org.cgiar.agrofims.sparqlchartsdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URIBuilder;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.sparql.resultset.ResultsFormat;

public class HomePageController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected static final String QUERIES_DIR = "/queries/";

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("query1", loadResource(QUERIES_DIR + "query1.rq"));
        request.setAttribute("query2", loadResource(QUERIES_DIR + "query2.rq"));
        request.setAttribute("query3", loadResource(QUERIES_DIR + "query3.rq"));
        request.setAttribute("query4", loadResource(QUERIES_DIR + "query4.rq"));
        request.setAttribute("processesAndParticipants", getProcessesAndParticipants());
        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);        
    }
    
    private List<ValueAndLabel> getProcessesAndParticipants() {
        List<ValueAndLabel> processesAndParticipants = new ArrayList<ValueAndLabel>();
        String queryStr = loadResource(QUERIES_DIR + "query3.rq");
        URIBuilder builder;
        try {
            builder = new URIBuilder("http://35.156.71.103/vivo/admin/sparqlquery");         
            builder.addParameter("query", queryStr);
            builder.addParameter("resultFormat", "application/sparql-results+json");
            ResultSet rs = ResultSetFactory.load(builder.toString(), ResultsFormat.FMT_RS_JSON);             
            while(rs.hasNext()) {
                QuerySolution qsoln = rs.next();
                StringBuilder valueSb = new StringBuilder();
                StringBuilder labelSb = new StringBuilder();
                for(String var : rs.getResultVars()) {
                    String binding = getStringValue(qsoln.get(var));
                    if(valueSb.length() > 0) {
                        valueSb.append("|");    
                    }
                    valueSb.append(binding);                    
                    if(var.contains("Name")) {
                        if(labelSb.length() > 0 && !binding.isEmpty()) {
                            labelSb.append(" - ");
                        }
                        labelSb.append(binding);
                    }
                }
                ValueAndLabel vl = new ValueAndLabel(valueSb.toString(), labelSb.toString());
                processesAndParticipants.add(vl);
            }
            return processesAndParticipants;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
    public class ValueAndLabel {
        
        private String value;
        private String label;
        
        public ValueAndLabel(String value, String label) {
            this.value = value;
            this.label = label;
        }
        
        public String getValue() {
            return this.value;
        }
        
        public String getLabel() {
            return this.label;
        }
    }
    
    private String getStringValue(RDFNode node) {
        if(node == null) {
            return "";
        } else if(node.isLiteral()) {
            return node.asLiteral().getLexicalForm();
        } else if(node.isAnon()) {
            return "";
        } else {
            return node.asResource().getURI();
        }
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
