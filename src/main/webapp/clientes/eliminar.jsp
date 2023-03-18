<%-- 
    Document   : eliminar
    Created on : 27 oct. 2022, 20:18:47
    Author     : maria
--%>

<%@page import="pe.isil.jupiter_dae1.model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Cliente cliente = new Cliente();
    cliente = (Cliente) request.getAttribute("cliente");
%>

<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header bg-gradient-blue">
            <h4 class="modal-title">Eliminar Categoría</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">X</span>
            </button>
        </div>
        
        <form id="eliminar" action="${URL_APLICACION}/admin/clientes/eliminar/<%=cliente.getId() %>" method="post">
            <div class="modal-body">
                <div class="alert alert-danger">
                    ¿Estás seguro de eliminar la cliente: <%=cliente.getNombres_apellidos()%>?
                </div>    
            </div>
            
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">NO</button>
                <button type="submit" class="btn btn-danger">SI</button>
            </div>
        </form>
    </div>
</div>


