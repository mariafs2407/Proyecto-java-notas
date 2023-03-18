<%-- 
    Document   : eliminar
    Created on : 21 oct. 2022, 14:48:29
    Author     : maria
--%>

<%@page import="pe.isil.jupiter_dae1.model.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Producto producto = new Producto();
    producto = (Producto) request.getAttribute("producto");
%>

<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header bg-gradient-blue">
            <h4 class="modal-title">Eliminar Producto:</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">X</span>
            </button>
        </div>
        
        <form id="eliminar" action="${URL_APLICACION}/admin/productos/eliminar/<%=producto.getId() %>" method="post">
            <div class="modal-body">
                <div class="alert alert-danger">
                    ¿Estás seguro de eliminar el producto: <%=producto.getNombre() %>?
                </div>    
            </div>
            
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">NO</button>
                <button type="submit" class="btn btn-danger">SI</button>
            </div>
        </form>
    </div>
</div>
