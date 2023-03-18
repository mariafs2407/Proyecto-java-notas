<%-- 
    Document   : index
    Created on : 24 nov. 2022, 19:34:26
    Author     : maria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
  <link rel="stylesheet" href="${URL_APLICACION}/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
  <link rel="stylesheet" href="${URL_APLICACION}/plugins/toastr/toastr.min.css">
  
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
                    
                        <div class="card-header">
                            <h3 class="card-title">Venta</h3>    
                        </div>
                    
                    <div class="card-body">      
    
                        
                        <form id="frmVenta" name="frmVenta" submit="return false" autocomplete="off">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label>Fecha</label>
                                        <input class="form-control" type="date" name="fecha" id="fecha" />
                                    </div>
                                    <div class="form-group">
                                        <label>DNI - Cliente</label>
                                        <input class="form-control" type="text" name="dni" id="dni" />
                                    </div>
                                                                        <div class="form-group">
                                        <label>NOMBRES Y APELLIDOS - Cliente</label>
                                        <input class="form-control" type="text" name="nombres_apellidos" id="nombres_apellidos" />
                                    </div>
                                                                        <div class="form-group">
                                        <label>DIRECCION - Cliente</label>
                                        <input class="form-control" type="text" name="direccion" id="direccion" />
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-group">
                                        <div class="input-group">                                        
                                            <input class="form-control" type="search" name="producto" id="producto" placeholder="Buscar.."/>
                                            <div class="input-group-addon">
                                                <button type="button" class="btn btn-default" onclick="BuscarProducto()">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </div>
                                        </div>
                                    
                                        <div>
                                            <table class="table table-border table-hover table-sm">
                                                <thead>
                                                    <th>ID</th>
                                                    <th>PRODUCTO</th>
                                                    <th>PRECIO</th>
                                                    <th>CANTIDAD</th>
                                                </thead>
                                                <tbody id="div_productos">

                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                    
                                    
                                </div>
                                
                                <div class="col-6">
                                    <div class="col-12" id="div_carrito">
                                    </div>
                                    
                                    <div>
                                        <button type="button" class="btn btn-primary" onclick="GuardarVenta()">
                                                <i class="fa fa-save"></i> Guardar
                                        </button><!-- comment -->
                                        
                                        <button type="button" class="btn btn-danger" onclick="CancelarVenta()">
                                                <i class="fa fa-trash-alt"></i> Cancelar
                                        </button>
                                    </div>
                                </div>
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
<script src="${URL_APLICACION}/plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="${URL_APLICACION}/plugins/toastr/toastr.min.js"></script>

<script>
    
    function BuscarProducto(){
        
        const nombre = document.getElementById('producto').value;
        const enlace = "${URL_APLICACION}/admin/ventas";
        $.ajax({
            method: "POST",
            url: enlace,
            data: {
                "p_nombre": nombre
            }
        }).done(function(resultado){
            $('#div_productos').html(resultado)
        });
    }
    
    function AgregarCarrito(id){
        const enlace = "${URL_APLICACION}/admin/ventas/agregar_producto";
        $.ajax({
            method: "POST",
            url: enlace,
            data: {
                "producto_id": id,
                "cantidad": $("#txtCantidad"+id).val()
            }
        }).done(function(resultado){
            $('#div_carrito').html(resultado)
        });
    }
    
    function GuardarVenta(){
        const enlace = "${URL_APLICACION}/admin/ventas/guardar";
        $.ajax({
            method: "POST",
            url: enlace,
            data: {
                "fecha": $("#fecha").val(),
                "dni": $("#dni").val(),
                "nombres_apellidos": $("#nombres_apellidos").val(),
                "direccion": $("#direccion").val()
            }
        }).done(function(resultado){
            
            
            $('#div_carrito').html("");
            $('#fecha').val("");
            $('#dni').val("");
            $('#nombres_apellidos').val("");
            $('#direccion').val("");
            $('#div_productos').html("");
            
        
            Swal.fire(resultado);

        });
    }
    
    function CancelarVenta(){
        const enlace = "${URL_APLICACION}/admin/ventas/cancelar";
        $.ajax({
            method: "POST",
            url: enlace,
            data: {
            }
        }).done(function(resultado){
            $('#div_carrito').html("");
            $('#fecha').val("");
            $('#dni').val("");
            $('#nombres_apellidos').val("");
            $('#direccion').val("");
            $('#div_productos').html("");
        });
    }
    
</script>

</body>
</html>