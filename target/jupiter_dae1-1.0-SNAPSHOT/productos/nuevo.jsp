<%-- 
    Document   : nuevo
    Created on : 13 oct. 2022, 20:05:34
    Author     : maria
--%>
<%@page import="pe.isil.jupiter_dae1.model.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="card card-primary mt-3">
                    <div class="card card-header">
                        <h3 class="card-title">Registrar Nuevo Producto:</h3>
                    </div>
                        
                    <div class="card-body">                        
                        
                        <form id="form_regcli" action="${URL_APLICACION}/admin/productos/registrar" method="post" autocomplete="off" enctype="multipart/form-data">
                            
                            <div class="form-group">
                                <label for="exampleInputEmail1">Categoria_Id</label>
                                <input type="text" class="form-control" id="categoria_id" name="categoria_id" placeholder="Ingrese Categoria_id">
                            </div>
                            
                            <div class="form-group">
                                <label for="exampleInputEmail1">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese nombre">
                            </div>                            

                            <div class="form-group">
                                <label for="exampleInputEmail1">Imagen</label>
                                <input type="file"  id="imagen" name="imagen" placeholder="Ingrese imagen">
                            </div>
                            
                            <div class="form-group">
                                <label for="exampleInputEmail1">Precio</label>
                                <input type="text" class="form-control" id="precio" name="precio" placeholder="Ingrese precio">
                            </div>
                            
                            <div class="form-group">
                                <label for="exampleInputEmail1">Stock</label>
                                <input type="text" class="form-control" id="stock" name="stock" placeholder="Ingrese stock">
                            </div>
                            
                            <div class="justify-content-between">
                                <button type="submit" class="btn btn-primary">Registrar</button>
                                <a href="${URL_APLICACION}/admin/productos" class="btn btn-secondary">Cancelar</a>
                            </div>
                            
                        </form>
                        
                        
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
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${URL_APLICACION}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${URL_APLICACION}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${URL_APLICACION}/dist/js/adminlte.min.js"></script>
</body>
</html>

