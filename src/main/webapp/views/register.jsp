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
                        <a class="nav-link active" aria-current="page" href="<%=context%>/views/register.jsp">Personas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=context%>/views/transactions.jsp">Registro de movimientos</a>
                    </li>
                </ul>
                <form class="d-flex justify-content-evenly" method="POST" action="<%=context%>/ServletPersona?action=logout">
                    <small class="text-black mx-1 mt-2"><strong>Sesión:</strong> ${usuario.correo}</small>
                    <button class="btn btn-outline-secondary" type="submit">Salir</button>
                </form>
            </div>
        </div>
    </nav>
    <!--NAVBAR-->

    <!--CONTENT-->
    <div class="container mt-4" >
        <h2>Listado de personas</h2>
        <div class="row">
            <div class="col align-content-end" >
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ModalRegistrar">Registrar</button>
            </div>
        </div>
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
                            <div class="col-1 text-center">${index}</div>
                            <div class="col-5">${persona.nombre} ${persona.aPaterno} ${persona.aMaterno}</div>
                            <div class="col-3 text-center">${persona.fechaNacimiento}</div>
                            <div class="col-1 text-center"><span class="col-12 badge bg-primary">${persona.sexo}</span></div>
                            <div class="col-2 text-center d-flex flex-row justify-content-center justify-content-evenly">
                                <button class="btn btn-primary py-2" data-bs-toggle="modal" data-bs-target="#modalInformacion" onclick="listarPersona(${persona.id})"><i class="bi bi-info-circle-fill"></i></button>
                                <button class="btn btn-primary py-2" data-bs-toggle="modal" data-bs-target="#modalActualizar" onclick="listarPersona(${persona.id})"><i class="bi bi-pencil-fill"></i></button>
                                <button class="btn btn-danger py-2" data-bs-toggle="modal" data-bs-target="#modalEliminar" onclick="listarPersona(${persona.id})"><i class="bi bi-trash-fill"></i></button>
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
            <div class="modal-header bg-success">
                <h5 class="modal-title" id="exampleModalLabel1">Registrar Persona</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="row g-3" id="frmRegistrar" method="POST" action="/ServletPersona?action=create">
                    <div class="col-sm-7">
                        <label  class="col-form-label">Nombre:</label>
                        <input type="text" class="form-control" id="txtnombre" name="txtnombre">
                    </div>
                    <div class="col-sm-5">
                        <label  class="col-form-label">Apellido Paterno</label>
                        <input type="text" class="form-control" id="txtapaterno" name="txtapaterno">
                    </div>
                    <div class="col-sm-4">
                        <label class="col-form-label">Apellido Materno</label>
                        <input type="text" class="form-control" id="txtamaterno" name="txtamaterno">
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Edad</label>
                        <input type="number" class="form-control" id="txtedad" name="txtedad">
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Sexo</label>
                        <input type="text" class="form-control" id="txtsexo" name="txtsexo">
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Telefono</label>
                        <input type="number" class="form-control" id="txttelefono" name="txttelefono">
                    </div>
                    <div class="col-sm-8">
                        <label  class="col-form-label">Dirección</label>
                        <input type="text" class="form-control" id="txtdireccion" name="txtdireccion">
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Fecha Nacimiento</label>
                        <input type="date" class="form-control" id="txtfechanacimiento" name="txtfechanacimiento">
                    </div>
                    <div class="col-sm-3">
                        <label  class="col-form-label">Estado Civil</label>
                        <input type="text" class="form-control" id="txtestadocivil" name="txtestadocivil">
                    </div>
                    <div class="col-sm-5">
                        <label  class="col-form-label">Correo</label>
                        <input type="email" class="form-control" id="txtcorreo" name="txtcorreo">
                    </div>
                    <div class="col-sm-6">
                        <label for="txtcontrasena" class="form-label">Contraseña</label>
                        <div class="input-group ">
                            <button type="button" class="btn btn-light input-group-text" id="showP"><i class="bi bi-eye-fill"></i></button>
                            <input type="password" class="form-control" id="txtcontrasena" name="txtcontrasena" aria-label="txtcontrasena" aria-describedby="basic-addon1" >
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <label  class="col-form-label">Estado:</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="txtestado" name="txtestado">
                        </div>
<%--                        <input type="radio" class="form-control" id="txtestado" name="txtestado">--%>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-success" id="btn-guardar1">Guardar</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="ModalActualizar" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content col-sm-12">
            <div class="modal-header bg-primary">
                <h5 class="modal-title" id="exampleModalLabel2">Actualizar Persona</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="row g-3" id="frmActualizar" method="POST" action="/ServletPersona?action=update">
                    <input type="hidden" id="actiona" value="update">
                    <input type="hidden" name="txtidpersonaU" id="txtidpersonaU" value="">
                    <div class="col-sm-7">
                        <label  class="col-form-label">Nombre:</label>
                        <input type="text" class="form-control" id="txtnombreU" name="txtnombreU">
                    </div>
                    <div class="col-sm-5">
                        <label  class="col-form-label">Apellido Paterno</label>
                        <input type="text" class="form-control" id="txtapaternoU" name="txtapaternoU">
                    </div>
                    <div class="col-sm-4">
                        <label class="col-form-label">Apellido Materno</label>
                        <input type="text" class="form-control" id="txtamaternoU" name="txtamaternoU">
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Edad</label>
                        <input type="number" class="form-control" id="txtedadU" name="txtedadU">
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Sexo</label>
                        <input type="text" class="form-control" id="txtsexoU" name="txtsexoU">
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Telefono</label>
                        <input type="number" class="form-control" id="txttelefonoU" name="txttelefonoU">
                    </div>
                    <div class="col-sm-8">
                        <label  class="col-form-label">Dirección</label>
                        <input type="text" class="form-control" id="txtdireccionU" name="txtdireccionU">
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Fecha Nacimiento</label>
                        <input type="date" class="form-control" id="txtfechanacimientoU" name="txtfechanacimientoU">
                    </div>
                    <div class="col-sm-3">
                        <label  class="col-form-label">Estado Civil</label>
                        <input type="text" class="form-control" id="txtestadocivilU" name="txtestadocivilU">
                    </div>
                    <div class="col-sm-5">
                        <label  class="col-form-label">Correo</label>
                        <input type="email" class="form-control" id="txtcorreoU" name="txtcorreoU">
                    </div>
                    <div class="col-sm-6">
                        <label for="txtcontrasenaU" class="form-label">Contraseña</label>
                        <div class="input-group ">
                            <button type="button" class="btn btn-light input-group-text" id="showP1"><i class="bi bi-eye-fill"></i></button>
                            <input type="password" class="form-control" id="txtcontrasenaU" name="txtcontrasenaU" aria-label="txtcontrasenaU" aria-describedby="basic-addon2" >
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <label  class="col-form-label">Estado:</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="txtestadoU" name="txtestadoU">
                        </div>
                        <%--                        <input type="radio" class="form-control" id="txtestado" name="txtestado">--%>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" id="btn-actualizar">Guardar</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="ModalEliminar" tabindex="-1" aria-labelledby="exampleModalLabel3" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content col-sm-12">
            <div class="modal-header bg-danger">
                <h5 class="modal-title" id="exampleModalLabel3">Eliminar Persona</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="row g-3" id="frmEliminar" method="POST" action="/ServletPersona?action=delete">
                    <input type="hidden" id="actiona1" value="delete">
                    <input type="hidden" name="txtidpersonaD" id="txtidpersonaD" value="">
                    <div class="col-sm-12">
                        <label  class="col-form-label">Nombre:</label>
                        <input type="text" class="form-control" id="txtnombreD" name="txtnombreD" readonly>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-warning" id="btn-eliminar">Confirmar</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalInformacion" tabindex="-1" aria-labelledby="exampleModalLabel4" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content col-sm-12">
            <div class="modal-header bg-info">
                <h5 class="modal-title" id="exampleModalLabel4">Información Persona</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="row g-3" id="frmInformacion" method="POST" action="/ServletPersona">
                    <input type="hidden" id="actiona2" value="info">
                    <div class="col-sm-7">
                        <label  class="col-form-label">Nombre:</label>
                        <input type="text" class="form-control" id="txtnombreI" name="txtnombreI" readonly>
                    </div>
                    <div class="col-sm-5">
                        <label  class="col-form-label">Apellido Paterno</label>
                        <input class="form-control" id="txtapaternoI" name="txtapaternoI" readonly>
                    </div>
                    <div class="col-sm-4">
                        <label class="col-form-label">Apellido Materno</label>
                        <input class="form-control" id="txtamaternoI" name="txtamaternoI" readonly>
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Edad</label>
                        <input class="form-control" id="txtedadI" name="txtedadI" readonly>
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Sexo</label>
                        <input class="form-control" id="txtsexoI" name="txtsexoI" readonly>
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Telefono</label>
                        <input class="form-control" id="txttelefonoI" name="txttelefonoI" readonly>
                    </div>
                    <div class="col-sm-8">
                        <label  class="col-form-label">Dirección</label>
                        <input class="form-control" id="txtdireccionI" name="txtdireccionI" readonly>
                    </div>
                    <div class="col-sm-6">
                        <label  class="col-form-label">Fecha Nacimiento</label>
                        <input class="form-control" id="txtfechanacimientoI" name="txtfechanacimientoI" readonly>
                    </div>
                    <div class="col-sm-6">
                        <label  class="col-form-label">Estado Civil</label>
                        <input class="form-control" id="txtestadocivilI" name="txtestadocivilI" readonly>
                    </div>
                    <div class="col-sm-5">
                        <label  class="col-form-label">Correo</label>
                        <input class="form-control" id="txtcorreoI" name="txtcorreoI" readonly>
                    </div>
                    <div class="col-sm-4">
                        <label  class="col-form-label">Contraseña</label>
                        <input class="form-control" id="txtcontrasenaI" name="txtcontrasenaI" readonly>
                    </div>
                    <div class="col-sm-3 my-auto mx-auto">
                            <label  class="col-form-label">Estado:</label>
                        <input class="form-control" id="txtestadoI" name="txtestadoI" value="" readonly>
                        <%--                        <input type="radio" class="form-control" id="txtestado" name="txtestado">--%>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>

    <!--CONTENT-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="<%=context%>/assets/js/register.js"></script>
</body>
</html>
