<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CDS-UTEZ
  Date: 31/01/2023
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%String context = request.getContextPath();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<%=context%>/assets/bootstrap/bootstrap.min.css ">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=context%>/assets/general.css">
    <title>Register</title>
</head>
<body>
<jsp:include page="/ServletPersona?action=findAll" />

    <!--NAVBAR-->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">SIPE</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Personas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Registro de movimientos</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
    <!--NAVBAR-->

    <!--CONTENT-->
    <div class="container mt-4" onload="listarPersonas()">
        <h2>Listado de personas</h2>
        <hr>
        <div class="card border-0 shadow col-12">
            <div class="card-body">
                <div class="row">
                    <div class="col-1 text-center fw-bold">#</div>
                    <div class="col-5 fw-bold">Nombre de la persona</div>
                    <div class="col-3 text-center fw-bold">Fecha de nacimiento</div>
                    <div class="col-1 text-center fw-bold">Género</div>
                    <div class="col-2 text-center fw-bold">Acciones</div>
                </div>
            </div>
        </div>
        <div class="scrollable-custom" id="personList">
            <c:forEach items="${ListPersonas}" var="persona">
                <div class="card col-12 mt-2">
                    <div class="card-body py-2 px-3">
                        <div class="row d-flex align-items-center">
                            <div class="col-1 text-center">1</div>
                            <div class="col-5">${persona.nombre} ${persona.aPaterno} ${persona.aMaterno}</div>
                            <div class="col-3 text-center">${persona.fechaNacimiento}</div>
                            <div class="col-1 text-center"><span class="col-12 badge bg-primary">${persona.sexo}</span></div>
                            <div class="col-2 text-center d-flex flex-row justify-content-center justify-content-evenly">
                                <button class="btn btn-primary py-2"><i class="bi bi-info-circle-fill"></i></button>
                                <button class="btn btn-primary py-2"><i class="bi bi-pencil-fill"></i></button>
                                <button class="btn btn-danger py-2"><i class="bi bi-trash-fill"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>


<!--MODALS-->
<div class="modal fade" id="ModalRegistrar" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content col-sm-12">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel1">Registrar Persona</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="row g-3" id="frmRegistrar" method="POST" action="${context}/ServletPersona">
                    <input type="hidden" name="action" value="create">
                    <div class="col-sm-7">
                        <label  class="col-form-label">Nombre:</label>
                        <input type="text" class="form-control" id="txtnombre" name="txtnombre">
                    </div>
                    <div class="col-sm-5">
                        <label  class="col-form-label">Apellido Paterno</label>
                        <input type="text" class="form-control" id="txtapaterno" name="txtapaterno">
                    </div>
                    <div class="col-sm-12">
                        <label class="col-form-label">Apellido Materno</label>
                        <input type="text" class="form-control" id="txtamaterno" name="txtamaterno">
                    </div>
                    <div class="col-sm-12">
                        <label  class="col-form-label">Edad</label>
                        <input type="text" class="form-control" id="txtedad" name="txtedad">
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Sexo</label>
                        <input type="text" class="form-control" id="txtsexo" name="txtsexo">
                    </div>
                    <div class="col-sm-4">
                        <label for="txtestado1" class="col-form-label">Estado:</label>
                        <input type="" class="form-control" id="txtestado1" name="estado">
                    </div>
                    <div class="col-sm-4">
                        <label for="txtpais1" class="col-form-label">Pais:</label>
                        <input type="text" class="form-control" id="txtpais1" name="pais">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" id="btn-guardar1">Guardar</button>
            </div>
        </div>
    </div>
</div>


    <!--CONTENT-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script src="<%=context%>/assets/js/register.js"></script>
</body>
</html>
