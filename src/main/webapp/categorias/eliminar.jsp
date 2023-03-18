<%-- 
    Document   : eliminar
    Created on : 13 oct. 2022, 21:13:41
    Author     : maria
--%>
<%@page import="pe.isil.jupiter_dae1.model.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Categoria categoria = new Categoria();
    categoria = (Categoria) request.getAttribute("categoria");
%>

<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header bg-gradient-blue">
            <h4 class="modal-title">Eliminar Categoría</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">X</span>
            </button>
        </div>
        
        <form id="eliminar" action="${URL_APLICACION}/admin/categorias/eliminar/<%=categoria.getId() %>" method="post">
            <div class="modal-body">
                <div class="alert alert-danger">
                    ¿Estás seguro de eliminar la categoria: <%=categoria.getNombre() %>?
                </div>    
            </div>
            
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">NO</button>
                <button type="submit" class="btn btn-danger">SI</button>
            </div>
        </form>
    </div>
</div>


