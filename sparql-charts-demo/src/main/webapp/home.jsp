<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="charts-from-csv/styles.css">
    <title>SIMLESA Maize Yields</title>
  </head>
  <body>
  
    <h1>SIMLESA Maize Yields</h1>
    
    <h2>List of plots with maize yield</h2>
    
    <table><tr>
    
    <td><form method="GET" action="TODO">
      <input type="submit" class="nav-btn" value="SPARQL">
    </form></td>
    
    <td><form method="GET" action="http://35.156.71.103/vivo/admin/sparqlquery">
      <input type="submit" class="nav-btn" value="Results"/>
      <input type="hidden" name="resultFormat" value="text/plain"/>
      <input type="hidden" name="query" value="${query1}"/>
    </form></td>
    
    </tr></table>
    
    <h2>Maize yield by locality</h2>
    
    <table><tr>
    
    <td><form method="GET" action="charts-from-csv/index.html">
      <input type="submit" class="nav-btn" value="SPARQL and Chart"/>
      <input name="q" type="hidden" value="${query2}"/>
    </form></td>
    
    </tr></table>
    
  </body>
</html>
