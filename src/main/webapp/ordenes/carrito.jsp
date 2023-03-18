<%-- 
    Document   : carrito
    Created on : 5 dic. 2022, 18:12:14
    Author     : maria
--%>

<%@page import="pe.isil.jupiter_dae1.model.OrdenDetalle"%>
<%@page import="pe.isil.jupiter_dae1.model.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<OrdenDetalle> listado= new ArrayList<>();
    
    listado=(ArrayList<OrdenDetalle>) request.getAttribute("listado");
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
        Double precio=0.0;      
        for(OrdenDetalle producto : listado)
        {   
          precio =(producto.getCantidad()*producto.getPrecio());
          total = precio + total ;
          

    %>
    <tr>
        <td><%=producto.getProducto_id()%></td>
        <td><%=producto.getCantidad()%></td>
        <td><%=producto.getPrecio()%></td>
        <td><%=precio%></td>
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


