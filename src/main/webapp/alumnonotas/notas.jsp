
<%@page import="pe.isil.jupiter_dae1.model.AlumnoNotas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    AlumnoNotas alumnonotas = new AlumnoNotas();
    
    alumnonotas = (AlumnoNotas)request.getAttribute("listado");

%>

<table class="table table-border table-hover table-sm">
    <tr>                        
        <th>EP1</th>
        <th>EP2</th>
        <th>EP3</th>
        <th>EP3</th>
        <th>PROMEDIO EP</th>
        <th>PARCIAL</th>
        <th>FINAL</th>
        <th>NOTA</th>
    </tr>
    
    <% 
    
    
    %>
    
    <tr>
        <td><%=alumnonotas.getEp1() %></td>
        <td><%=alumnonotas.getEp2() %></td>
        <td><%=alumnonotas.getEp3() %></td>
        <td><%=alumnonotas.getEp4() %></td>
        <td><%=alumnonotas.getPromedio_eps() %></td>
        <td><%=alumnonotas.getEp_parcial() %></td>
        <td><%=alumnonotas.getEp_final() %></td>
        <td><%=alumnonotas.getNota() %></td>
    </tr>
    
</table>
