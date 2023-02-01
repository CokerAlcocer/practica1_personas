<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alberto_89z
  Date: 01/02/2023
  Time: 01:18 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%String context = request.getContextPath();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=context%>/assets/css/changepswd.css">
    <title>Cambiar contraseña</title>
    <style>
        .error {
            color: #FF0000;
        }
        .bord {
            border-radius: 4px;
        }
    </style>
</head>
<body id="bodyLogin">
<div>
    <i id="return_back" class="bi bi-arrow-left"></i>
</div>
<div class="login-page container-fluid">
    <div class="form" style="border-radius: 1em;">
        <div class="login-form">
            <img src="https://us.123rf.com/450wm/alekseyvanin/alekseyvanin1704/alekseyvanin170403663/76699411-vector-de-icono-de-usuario-ilustraci%C3%B3n-de-logotipo-s%C3%B3lido-de-perfil-pictograma-aislado-en-blanco.jpg"
                 style="height: 150px; width: 150px; border-radius: 100%; border: 1px solid white;margin-bottom: 10px;">
            <br><hr>
            <p>albertoquinonez70@gmail.com</p>
            <!-- Arriba deberia ir el correo del usuario de quien quiere cambiar su contraseña -->
            <div class="form-group campo">
                <label for="pswdUpd" class="col-form-label"><strong>Ingresa la nueva contraseña:</strong></label>
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <label id="changeEye1" onclick="Toggle()" class="input-group-text"><i class="bi bi-eye-slash-fill"></i></label>
                    </div>
                    <input type="password" class="form-control" id="pswdUpd">
                </div>
            </div>
            <br>
            <div class="form-group campo">
                <label for="pswdConfirm" class="col-form-label"><strong>Confirma la contraseña:</strong></label>
                <div class="input-group mb-5">
                    <div class="input-group-prepend">
                        <label id="changeEye2" onclick="Toggle2()" class="input-group-text"><i class="bi bi-eye-slash-fill"></i></label>
                    </div>
                    <input type="password" class="form-control" id="pswdConfirm">
                </div>
            </div>
            <button id="btn_login" name="submit" style="border-radius: 10px;">
                <i class="bi bi-arrow-right"></i><strong>&nbsp;&nbsp;Cambiar contraseña</strong></button>
            <div id="errorLogin" class="error float-left" style="height: 5px;"></div><br><br>
        </div>
    </div>
</div><br>

<script>
    // Change the type of input to password or text
    function Toggle() {
        var temp = document.getElementById("pswdUpd");
        if (temp.type === "password") {
            temp.type = "text";
        } else {
            temp.type = "password";
        }

        var uno = document.getElementById('changeEye1');
        if (uno.innerHTML == '<i class="bi bi-eye-slash-fill"></i>')
            uno.innerHTML = '<i class="bi bi-eye-fill"></i>';
        else uno.innerHTML = '<i class="bi bi-eye-slash-fill"></i>';
    }

    function Toggle2() {
        var temp = document.getElementById("pswdConfirm");
        if (temp.type === "password") {
            temp.type = "text";
        } else {
            temp.type = "password";
        }

        var uno = document.getElementById('changeEye2');
        if (uno.innerHTML === '<i class="bi bi-eye-slash-fill"></i>')
            uno.innerHTML = '<i class="bi bi-eye-fill"></i>';
        else uno.innerHTML = '<i class="bi bi-eye-slash-fill"></i>';
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</body>
</html>
