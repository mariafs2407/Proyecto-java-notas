<%-- 
    Document   : tabla
    Created on : 24 nov. 2022, 20:12:45
    Author     : maria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pe.isil.jupiter_dae1.model.Producto"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Producto> listado = new ArrayList<>();
    listado = (ArrayList<Producto>)request.getAttribute("listado");
%>


<%
    for(Producto producto : listado)
    {
%>
<tr>
    <td><%=producto.getId()%></td>
    <td><%=producto.getNombre()%></td>
    <td><%=producto.getPrecio()%></td>
    <td>
        <input class="form-control input-sm" id="txtCantidad<%=producto.getId()%>" value="1" type="number" min="1"/>
    </td>
    <td>
        <button type="button" class="btn btn-primary btn-sm" onclick="AgregarCarrito(<%=producto.getId()%>)">
            <i class="fa fa-plus-circle"></i>
        </button>
    </td>
    
</tr>

<%
    }
%>