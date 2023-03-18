<%-- 
    Document   : Index
    Created on : 6 oct. 2022, 20:14:24
    Author     : maria
--%>

<%@page import="pe.isil.jupiter_dae1.model.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Categoria> listado = (List<Categoria>) request.getAttribute("listado");
%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>E-Comerce</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${URL_APLICACION}/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${URL_APLICACION}/dist/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

 <jsp:include page="../navbar.jsp"/>

 <jsp:include page="../sidebar.jsp"/>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="card card-primary card-outline mt-3">

                    <div class="card-body">
                        <h5 class="card-title">Administración de Categorías</h5>
                        
                        <div class="mt-3 mb-3 text-left">
                            <a href="${URL_APLICACION}/admin/categorias/nuevo" class="btn btn-success my-1 ml-1"> Nuevo</a>
                        </div>
                        
                        <table class="table table-hover" id="tabla_catagorias">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Nombres</th>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                  for(Categoria categoria :listado){
                                %>
                                   <tr>
                                       <td><%=categoria.getId() %></td>
                                       <td><%=categoria.getNombre() %></td>
                                       <td>
                                        <a href="${URL_APLICACION}/admin/categorias/editar/<%=categoria.getId() %>" class="btn btn-primary fas fa-edit"></a>
                                        <button onclick="abrirModal(<%=categoria.getId() %>)" class="btn btn-danger fas fa-trash"></button>
                                        </td>
                                   </tr>
                                <%
                                 }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>    
            </div>
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <jsp:include page="../footer.jsp"/>

</div>
  
  <div class="modal fade" id="modal-categoria">      
  </div>
  
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${URL_APLICACION}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${URL_APLICACION}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${URL_APLICACION}/dist/js/adminlte.min.js"></script>

<script src="${URL_APLICACION}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${URL_APLICACION}/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>

<script>
    
    function abrirModal(id){
        const enlace = '${URL_APLICACION}/admin/categorias/eliminar/' + id;
        $.ajax({
            method: "GET",
            url: enlace
        }).done(function(resultado){
            $('#modal-categoria').html(resultado)
            $('#modal-categoria').modal('show')
        });
    }
    
</script>

<script>
    $('#tabla_catagorias').DataTable();
</script>

</body>
</html>

