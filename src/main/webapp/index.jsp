<%String context = request.getContextPath();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>SIPE - Login</title>
    <link rel="stylesheet" href="<%=context%>/assets/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>

<section class="form-login">
    <form action="<%=context%>/ServletPersona?action=login" method="POST" id="frmLogin">
        <h1 style="text-align: center;">SIPE</h1>
        <h5 style="text-align: center;">¡Bienvenido!</h5>
        <input id="correo_usuario" class="controls" type="text" name="txtusuarioR" value="" placeholder="E-mail" required>
        <input id="contrasena" class="controls" type="password" name="txtcontrasenaR" value="" placeholder="Contraseña" required>
    </form>
    <button class="button" type="button" id="btLog" >Iniciar sesión</button>
    <a href="<%=context%>/views/"></a>
</section>



<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="<%=context%>/assets/js/login.js"></script>
</body>

</html>