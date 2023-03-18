<%-- 
    Document   : Login
    Created on : 29 oct. 2022, 19:56:45
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
  <title>AdminLTE 3 | Log in</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="../../index2.html"><b>Admin</b>LTE</a>
  </div>
  <!-- /.login-logo -->
  <div class="card">
    <div class="card-body login-card-body">
      <p class="login-box-msg">Iniciar Sesion:</p>
      <% 
          if(mensaje != ""){            
       %>
              <div class="alert alert-danger"><%=mensaje%></div>
       <%
           }          
       %>
      <form action="login" method="post">
        <div class="input-group mb-3">
            <input type="email" class="form-control" id="email" placeholder="Email" name="email">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
        </div>
          <p  id="mensajeEmail"></p>
          
        <div class="input-group mb-3">
            <input type="password" class="form-control" id="password" placeholder="Password" name="password">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <p  id="mensajepass1"></p>
        
        <div class="row">
          <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" id="remember">
              <label for="remember">
                Ingresar
              </label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-4">
            <button type="submit" class="btn btn-primary btn-block">Sign In</button>
          </div>
          <!-- /.col -->
        </div>
      </form>
     <a href="${URL_APLICACION}/registro_usuario" class="text-center">Registrarse</a>
     
    <!-- /.login-card-body -->
  </div>
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<script >
    //validaciones :input   
    const correo=document.getElementById("email")
    const pass=document.getElementById("password")    
    
    //mensajes
    const msnEmail = document.getElementById("mensajeEmail")
    const msnPass = document.getElementById("mensajepass1")
   
    let regexEmail= /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/
    
        
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
    
   
    
</script>
</body>
</html>



