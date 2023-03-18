<%-- 
    Document   : registro_usuario
    Created on : 27 oct. 2022, 20:58:07
    Author     : maria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    String mensaje = "";
    if (sesion.getAttribute("mensaje_error") != null) {
            mensaje = (String)sesion.getAttribute("mensaje_error");
            sesion.removeAttribute("mensaje_error");
        }
  
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>DAE 1 | Registro de Usuario</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${URL_APLICACION}/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="${URL_APLICACION}/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${URL_APLICACION}/dist/css/adminlte.min.css">
</head>
<body class="hold-transition register-page">
<div class="register-box">
  <div class="card card-outline card-primary">
    <div class="card-header text-center">
      <a href="../../index2.html" class="h1"><b>DAE1</b>ISIL</a>
    </div>
    
    <div class="card-body">
      <p class="login-box-msg">Regitrar un nuevo Usuario</p>
       <% 
          if(mensaje != ""){            
       %>
              <div class="alert alert-danger"><%=mensaje%></div>
       <%
           }          
       %>
       
      <form action="registro_usuario" method="post"  id="form">
        <div class="input-group mb-3">
            <input type="text" class="form-control" id="nombre" placeholder="Nombre completo" name="nombre">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-user"></span>
            </div>
          </div>   
           
                    
        </div>
        <p  id="mensajeNombre"></p>
        
        <div class="input-group mb-3">
            <input type="email" class="form-control" id="email" placeholder="Email" name="email" >
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div> 
        </div>
         <p  id="mensajeEmail"></p>
         
        <div class="input-group mb-3">
            <input type="password" class="form-control" id="password" placeholder="Password" name="password" >
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>  
        </div>
         <p  id="mensajepass1"></p>
         
        <div class="input-group mb-3">
            <input type="password" class="form-control" id="re_password" placeholder="Repetir password" name="repetir_password" >
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div> 
        </div>
          <p  id="mensajepass2"></p>
        <div class="row">
          <div class="col-8">
            
          </div>
          <!-- /.col -->
          <div class="col-4">
              <button type="submit" id="bEnviar"  class="btn btn-primary btn-block " >Registrate</button>
          </div>
          <!-- /.col -->
        </div>
      </form>     
     

      <a href="${URL_APLICACION}/login" class="text-center">Iniciar Sesión</a>
    </div>
    <!-- /.form-box -->

  </div><!-- /.card -->
</div>
<!-- /.register-box -->

<!-- jQuery -->
<script src="${URL_APLICACION}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${URL_APLICACION}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${URL_APLICACION}/dist/js/adminlte.min.js"></script>

<script >
    //validaciones :input
    const nombre=document.getElementById("nombre")
    const correo=document.getElementById("email")
    const pass=document.getElementById("password")
    const repass=document.getElementById("re_password")
    const form=document.getElementById("form")
    //mensajes
    const msnNombre = document.getElementById("mensajeNombre")
    const msnEmail = document.getElementById("mensajeEmail")
    const msnPass = document.getElementById("mensajepass1")
    const msnPass2 = document.getElementById("mensajepass2")
  
    let regexEmail= /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/
    
    //validacion nombre
    nombre.addEventListener("input",validarNombre);
    function validarNombre(e){
        if(nombre.value.length <6){
            msnNombre.style.color="red"
            msnNombre.innerHTML= "Nombre debe tener maximo 6 digitos"
        }else{
            msnNombre.style.color="green"
            msnNombre.innerHTML="Nombre correcto"
            
        }
    }
    
    correo.addEventListener("input",validarCorreo)
    function validarCorreo(e){
        if(!regexEmail.test(correo.value)){
            msnEmail.style.color="red"
            msnEmail.innerHTML="El correo no cumple los parametros"
        }else{
            msnEmail.style.color="green"
            msnEmail.innerHTML= "Correo correcto"
            
        }
    }
    
    pass.addEventListener("input",validarPass)
    function validarPass(e){
        if(pass.value.length < 6){
           msnPass.style.color="red"
           msnPass.innerHTML="La contraseña debe tener maximo 6 digitos"
        }else{           
           msnPass.style.color="green"
           msnPass.innerHTML= "Contraseña correcta"
        }
    }
    
    repass.addEventListener("input",validarPassIgual);
    //verificar contraseña igual
    function validarPassIgual(e){
        if(pass.value === repass.value){
             msnPass2.style.color="green"
             msnPass2.innerHTML= "Contraseña igual"
        }else{
             msnPass2.style.color="red"
             msnPass2.innerHTML= "La contraseña no es igual "
        }
    }
    
       
</script>


</body>
</html>
