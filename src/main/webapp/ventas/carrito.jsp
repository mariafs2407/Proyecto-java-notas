<%-- 
    Document   : carrito
    Created on : 24 nov. 2022, 21:45:24
    Author     : maria
--%>

<%@page import="pe.isil.jupiter_dae1.model.VentaDetalle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pe.isil.jupiter_dae1.model.Producto"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<VentaDetalle> listado = new ArrayList<>();
    listado = (ArrayList<VentaDetalle>)request.getAttribute("listado");
%>

<table class="table table-bordered table-hover">
    <tr>
        <th>PRODUCTO</th>
        <th>CANTIDAD</th>
        <th>PRECIO</th>
        <th>TOTAL</th>
    </tr>
    <%
        Double total = 0.0;
        for(VentaDetalle producto : listado)
        {
            total = total + producto.getTotal();
    %>
    <tr>
        <td><%=producto.getProducto_id()%></td>
        <td><%=producto.getCantidad()%></td>
        <td><%=producto.getPrecio()%></td>
        <td><%=producto.getTotal()%></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="3" align="right">
            <b>TOTAL</b>
        </td>
        <td>
            <b><%=total%></b>
        </td>
    </tr>
    
</table>


