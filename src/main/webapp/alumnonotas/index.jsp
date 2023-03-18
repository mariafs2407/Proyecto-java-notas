<%-- 
    Document   : index
    Created on : 15 dic. 2022, 16:06:25
    Author     : RL123
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="pe.isil.jupiter_dae1.model.AlumnoNotas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    AlumnoNotas alumnonotas = new AlumnoNotas();
    ArrayList<Integer> notas = new ArrayList<>();
    for (int i = 0; i <= 20; i++) {
        notas.add(i);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notas</title>
        
        <!-- Google Font: Source Sans Pro -->
   <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${URL_APLICACION}/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${URL_APLICACION}/dist/css/adminlte.min.css">
        
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
      
  
  
    </head>
    <body>
        <div class="text-center mt-1">
          <h1>Ingresar notas de alumno</h1>  
        </div>

        <div>
    <!-- Main content -->
    <div class="content">
      <div class="container">
        <div class="row">
            
            <div class="col-md-6">
                <div class="card card-primary card-outline mt-3">
                    
                        <div class="card-header">
                            <h3 class="card-title">Notas:</h3>    
                        </div>
                    
                    <div class="card-body">      
    
                        
                       <form id="frmNotas" name="frmVenta" submit="return false" autocomplete="off">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label>Alumno</label>
                                        <input class="form-control" type="text" maxlength="200" name="alumno" id="txtalumno" required />
                                    </div>
                                    <div class="form-group">
                                        <label>Curso</label>
                                        <input class="form-control" type="text" maxlength="200" name="curso" id="txtcurso" required/>
                                    </div>
                                    <div class="d-flex">
                                        <div class="form-group col-6">
                                            <label>Evaluacion Permanente 1</label>
                                            <select class="form-control col-4" id="txtep1">
                                            <%for (int i = 0; i <= 20; i++) {%>
                                            <option value="<%=i%>"><%=i%></option>
                                            <%}%>
                                            </select>
                                        </div>
                                        <div class="form-group col-6">
                                            <label>Evaluacion Permanente 2</label>
                                            <select class="form-control col-4" id="txtep2">
                                            <%for (int i = 0; i <= 20; i++) {%>
                                            <option value="<%=i%>"><%=i%></option>
                                            <%}%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="d-flex">
                                        <div class="form-group col-6">
                                            <label>Evaluacion Permanente 3</label>
                                            <select class="form-control col-4" id="txtep3">
                                            <%for (int i = 0; i <= 20; i++) {%>
                                            <option value="<%=i%>"><%=i%></option>
                                            <%}%>
                                            </select>
                                        </div>
                                        <div class="form-group col-6">
                                            <label>Evaluacion Permanente 4</label>
                                            <select class="form-control col-4" id="txtep4">
                                            <%for (int i = 0; i <= 20; i++) {%>
                                            <option value="<%=i%>"><%=i%></option>
                                            <%}%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="d-flex">
                                        <div class="form-group col-6">
                                            <label>Evaluacion Parcial</label>
                                            <select class="form-control col-4" id="txtep_parcial">
                                            <%for (int i = 0; i <= 20; i++) {%>
                                            <option value="<%=i%>"><%=i%></option>
                                            <%}%>
                                            </select>
                                        </div>
                                        <div class="form-group col-6">
                                            <label>Evaluacion Final</label>
                                            <select class="form-control col-4" id="txtep_final">
                                            <%for (int i = 0; i <= 20; i++) {%>
                                            <option value="<%=i%>"><%=i%></option>
                                            <%}%>
                                            </select>
                                        </div> 
                                    </div>
                                    
                                </div>
                            </div>
                            
                            <div class="row">
                                                              
                                <div class="col-12">
                                     
                                    <div>
                                        <button type="button" class="btn btn-primary" onclick="AgregarNotas()">
                                                <i class="fa fa-calculator"></i> Calcular promedio
                                        </button><!-- comment -->
                                        
                                        <button id="botonregistrar" type="button" onclick="return validar()" value="submit" class="btn btn-success">
                                            <i class="fa fa-save"></i> Registrar notas
                                        </button><!-- comment -->
                                        
                                        <button type="button" class="btn btn-danger" onclick="Cancelar()">
                                                <i class="fa fa-trash-alt"></i> Cancelar
                                        </button>
                                    </div>
                                </div>
                            </div>

                       </form> 
                        
                    </div>
                </div>    
            </div>
             
                                      
                <div class="col-md-6">
                    <div id="div_notas" class="card card-primary card-outline mt-3">

                    </div>
               

                  
                </div>
                                 
                                           
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
        
    </body>
    
    
    <!-- jQuery -->
<script src="${URL_APLICACION}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${URL_APLICACION}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${URL_APLICACION}/dist/js/adminlte.min.js"></script>

<script src="${URL_APLICACION}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${URL_APLICACION}/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>

<!-- jquery-validation -->
<script src="plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="plugins/jquery-validation/additional-methods.min.js"></script>


<script>
     
    function AgregarNotas(){
        const enlace = "${URL_APLICACION}/admin/alumnonotas/agregar";
        $.ajax({
            method: "POST",
            url: enlace,
            data: {                
                "ep1": $('#txtep1 option:selected').val(),
                "ep2": $("#txtep2").val(),
                "ep3": $("#txtep3").val(),
                "ep4": $("#txtep4").val(),
                "ep_parcial": $("#txtep_parcial").val(),
                "ep_final": $("#txtep_final").val()
            }
        }).done(function(resultado){
            $('#div_notas').html(resultado)
        });
    }
    
    function GuardarDatos(){
        const enlace = "${URL_APLICACION}/admin/alumnonotas/guardar";
        $.ajax({
            method: "POST",
            url: enlace,
            data: {
                "nombre": $('#txtalumno').val(),
                "curso": $('#txtcurso').val(),
                "ep1": $('#txtep1').val(),
                "ep2": $("#txtep2").val(),
                "ep3": $("#txtep3").val(),
                "ep4": $("#txtep4").val(),
                "ep_parcial": $("#txtep_parcial").val(),
                "ep_final": $("#txtep_final").val()                
            }
        }).done(function(resultado){
            $('#div_notas').html("");
            $('#txtalumno').val("");
            $('#txtcurso').val("");
            $('#txtep1').val("0");
            $("#txtep2").val("0");
            $("#txtep3").val("0");
            $("#txtep4").val("0");
            $("#txtep_parcial").val("0");
            $("#txtep_final").val("0");
                    
        });
    }
    
    function Cancelar(){
        const enlace = "${URL_APLICACION}/admin/alumnonotas/cancelar";
        $.ajax({
            method: "POST",
            url: enlace,
            data: {
            }
        }).done(function(resultado){
            $('#div_notas').html("");
            $('#txtalumno').val("");
            $('#txtcurso').val("");
            $('#txtep1').val("0");
            $("#txtep2").val("0");
            $("#txtep3").val("0");
            $("#txtep4").val("0");
            $("#txtep_parcial").val("0");
            $("#txtep_final").val("0");
        });
    }          
    
    function validar(){
        if (document.getElementById("txtalumno").value=="" || document.getElementById("txtcurso").value==""){
            swal("Ingrese los datos del alumno y curso", "", "error");
            
            return false; 
        }
        else{
            swal("Notas registradas correctamente", "", "success");
            return GuardarDatos();
            
        }
    } 
       
    
</script>



</html>
