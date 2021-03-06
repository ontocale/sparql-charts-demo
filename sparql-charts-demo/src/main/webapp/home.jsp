<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit="no">
    <link rel="stylesheet" href="charts-from-csv/styles.css">
    <title>SIMLESA Maize Yields</title>
  </head>
  <body>
  
    <div class="queryMenu">
  
    <h1>SIMLESA Maize Yields</h1>
    
    <h2>List of plots with maize yield</h2>
    
    <table><tr>
    
    <td><form method="GET" action="file">
      <input type="submit" class="nav-btn" value="SPARQL"/>
      <input type="hidden" name="resource" value="query1.rq"/>
    </form></td>
    
    <td><form method="GET" action="http://52.29.119.149/vivo/admin/sparqlquery">
      <input type="submit" class="nav-btn" value="Results"/>
      <input type="hidden" name="resultFormat" value="text/plain"/>
      <input type="hidden" name="query" value="${fn:escapeXml(query1)}"/>
    </form></td>
    
    </tr></table>
    
    <h2>Maize yield by locality</h2>
    
    <table><tr>
    
    <td><form method="GET" action="charts-from-csv/index.html">
      <input type="submit" class="nav-btn" value="SPARQL and Chart"/>
      <input name="q" type="hidden" value="${fn:escapeXml(query2)}"/>
    </form></td>
    
    </tr></table>
    
    <h2>List of available processes and participant types</h2>
    
    <table><tr>
    
    <td><form method="GET" action="file">
      <input type="submit" class="nav-btn" value="SPARQL"/>
      <input type="hidden" name="resource" value="query3.rq"/>
    </form></td>
    
    <td><form method="GET" action="http://52.29.119.149/vivo/admin/sparqlquery">
      <input type="submit" class="nav-btn" value="Results"/>
      <input type="hidden" name="resultFormat" value="text/plain"/>
      <input type="hidden" name="query" value="${fn:escapeXml(query3)}"/>
    </form></td>
    
    </tr></table>

    <h2>Maize yield under conservation agriculture and conventional practice</h2>
    
    <table><tr>
    
    <td><form method="GET" action="charts-from-csv/index.html">
      <input type="submit" class="nav-btn" value="SPARQL and Chart"/>
      <input name="q" type="hidden" value="${fn:escapeXml(query4)}"/>
    </form></td>
    
    </tr></table>
    
    <h2>Query comparing two sets of conditions</h2>
    
    <form action="generateQuery" method="GET">
    
    <table><tr>
    
    <td><select name="condition1a">
      <c:forEach items="${processesAndParticipants}" var="pp">
        <option value="${pp.value}">${pp.label}</option>
      </c:forEach>
    </select></td>
    
    <td>versus</td>
    
    <td><select name="condition2a">
      <c:forEach items="${processesAndParticipants}" var="pp">
        <option value="${pp.value}">${pp.label}</option>
      </c:forEach>
    </select></td>
    
    <td>&nbsp;</td>
    
    </tr><tr>
    
    <td><select name="condition1b">
      <c:forEach items="${processesAndParticipants}" var="pp">
        <option value="${pp.value}">${pp.label}</option>
      </c:forEach>
    </select></td>
    
    <td>&nbsp;</td>
    
    <td><select name="condition2b">
      <c:forEach items="${processesAndParticipants}" var="pp">
        <option value="${pp.value}">${pp.label}</option>
      </c:forEach>
    </select></td>
    
    <td><input type="submit" class="nav-btn" value="SPARQL and Chart"/></td>
    
    </tr></table>
    
    </form>
    
    </div> <!-- end queryMenu -->
    
  </body>
</html>
