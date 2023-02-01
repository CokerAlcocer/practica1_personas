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
    <title>Registro de movimientos</title>
</head>
<body>

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
                    <a class="nav-link" aria-current="page" href="#">Personas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#">Registro de movimientos</a>
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
<div class="container mt-4">
    <h2>Registro de movimientos</h2>
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
    <div class="scrollable-custom">
        <div class="card col-12 mt-2">
            <div class="card-body py-2 px-3">
                <div class="row d-flex align-items-center">
                    <div class="col-1 text-center">1</div>
                    <div class="col-5">Angel Yazveck Alcocer Durán</div>
                    <div class="col-3 text-center">25/07/2002</div>
                    <div class="col-1 text-center"><span class="col-12 badge bg-primary">Hombre</span></div>
                    <div class="col-2 text-center d-flex flex-row justify-content-center justify-content-evenly">
                        <button class="btn btn-primary py-2"><i class="bi bi-info-circle-fill"></i></button>
                        <button class="btn btn-primary py-2"><i class="bi bi-pencil-fill"></i></button>
                        <button class="btn btn-danger py-2"><i class="bi bi-trash-fill"></i></button>
                    </div>
                </div>
            </div>
        </div>

        <div class="card col-12 mt-2">
            <div class="card-body py-2 px-3">
                <div class="row d-flex align-items-center">
                    <div class="col-1 text-center">2</div>
                    <div class="col-5">Hayase Nagatoro</div>
                    <div class="col-3 text-center">31/01/2004</div>
                    <div class="col-1 text-center"><span class="col-12 badge bg-danger">Mujer</span></div>
                    <div class="col-2 text-center d-flex flex-row justify-content-center justify-content-evenly">
                        <button class="btn btn-primary py-2"><i class="bi bi-info-circle-fill"></i></button>
                        <button class="btn btn-primary py-2"><i class="bi bi-pencil-fill"></i></button>
                        <button class="btn btn-danger py-2"><i class="bi bi-trash-fill"></i></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--CONTENT-->

</body>
</html>
