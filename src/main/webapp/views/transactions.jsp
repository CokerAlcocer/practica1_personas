<%--
  Created by IntelliJ IDEA.
  User: CDS-UTEZ
  Date: 31/01/2023
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%String context = request.getContextPath();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<%=context%>/assets/bootstrap/bootstrap.min.css ">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=context%>/assets/general.css">
    <title>Registro de movimientos</title>
</head>
<body>
<jsp:include page="/ServletPersona?action=findTransactions" />
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
                    <a class="nav-link" aria-current="page" href="<%=context%>/views/register.jsp">Personas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<%=context%>/views/transactions.jsp">Registro de movimientos</a>
                </li>
            </ul>
            <form class="d-flex justify-content-evenly my-1" method="POST" action="<%=context%>/ServletPersona?action=logout">
                <small class="text-black mx-1 mt-2"><strong>Sesión:</strong> ${usuario.correo}</small>
                <button class="btn btn-outline-secondary" type="submit">Salir</button>
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
                <div class="col-4 fw-bold">Nombre de la persona</div>
                <div class="col-3 text-center fw-bold">Fecha del movimiento</div>
                <div class="col-2 text-center fw-bold">Tipo</div>
                <div class="col-2 text-center fw-bold">Detalles</div>
            </div>
        </div>
    </div>
    <div class="scrollable-custom">
<c:forEach items="${ListTransactions}"  var="transaction">

    <div class="card col-12 mt-2">
        <div class="card-body py-2 px-3">
            <div class="row d-flex align-items-center">
                <div class="col-4">${transaction.nombrePersona}</div>
                <div class="col-3 text-center">${transaction.fechaMovimiento}</div>
                <c:if test="${transaction.tipo == 'INSERT'}">
                    <div class="col-2 text-center"><span class="col-12 badge bg-success">CREATE</span></div>
                </c:if>
                <c:if test="${transaction.tipo == 'UPDATE'}">
                    <div class="col-2 text-center"><span class="col-12 badge bg-primary">UPDATE</span></div>
                </c:if>
                <c:if test="${transaction.tipo == 'DELETE'}">
                    <div class="col-2 text-center"><span class="col-12 badge bg-danger">DELETE</span></div>
                </c:if>
                <c:if test="${transaction.tipo == 'LOGIN'}">
                    <div class="col-2 text-center"><span class="col-12 badge bg-dark">LOGIN</span></div>
                </c:if>
                <c:if test="${transaction.tipo == 'LOGOUT'}">
                    <div class="col-2 text-center"><span class="col-12 badge bg-secondary">LOGOUT</span></div>
                </c:if>
                <c:if test="${transaction.tipo == 'FIND_ALL'}">
                    <div class="col-2 text-center"><span class="col-12 badge bg-warning">FIND ALL</span></div>
                </c:if>
                <c:if test="${transaction.tipo == 'FIND_BY'}">
                    <div class="col-2 text-center"><span class="col-12 badge bg-warning">FIND BY ID</span></div>
                </c:if>
                <c:if test="${transaction.tipo == 'SIGNUP'}">
                    <div class="col-2 text-center"><span class="col-12 badge bg-info">SIGNUP</span></div>
                </c:if>
                <div class="col-2 text-center d-flex flex-row justify-content-center justify-content-evenly">
                    <button class="btn btn-primary py-2" data-bs-target="#modalInfo" data-bs-toggle="modal" onclick="listarTrasaction(${transaction.id})"><i class="bi bi-eye-fill"></i></button>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
    </div>


<!--MODAL INFO-->
<div class="modal fade" id="modalInfo" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content border-0">
            <div class="card">
                <div class="card-header"><h4>Detalles del movimiento</h4></div>
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <label >Datos Anteriores</label>
                            <p id="oldD"></p>
                        </div>
                        <div class="col">
                            <label >Nuevos Datos</label>
                            <p id="newD"></p>
                        </div>
                    </div>


                </div>
                <div class="card-footer text-end">
                    <button class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--MODAL INFO-->
</div>
<!--SCRIPTS-->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="<%=context%>/assets/bootstrap/bootstrap.bundle.min.js"></script>
<script src="<%=context%>/assets/js/transactions.js"></script>
<!--SCRIPTS-->

</body>
</html>
