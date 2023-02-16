const listarPersonas = () =>{
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    $.ajax({
        type: 'GET',
        url: contextPath + '/ServletPersona?action=findAll'
    }).done(function(response){
        let listPersonas = response.ListPersonas;
        console.log(listPersonas)
    })
}

const listarPersona = (idPersona) =>{
    console.log("Unique")
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    $.ajax({
        type: 'GET',
        url: contextPath + '/ServletPersona?action=findById',
        data: {txtidpersona: idPersona}
    }).done(function(response){
        let persona = response.UniquePerson;
        console.log(persona)
        if($('#actiona').val() == "update"){
            $('#txtidpersonaU').val(persona.id),
                $('#txtnombreU').val(persona.nombre),
                $('#txtapaternoU').val(persona.aPaterno),
                $('#txtamaternoU').val(persona.aMaterno),
                $('#txtedadU').val(persona.edad),
                $('#txtsexoU').val(persona.sexo),
                $('#txttelefonoU').val(persona.telefono),
                $('#txtdireccionU').val(persona.direccion),
                $('#txtfechanacimientoU').val(persona.fechaNacimiento),
                $('#txtestadocivilU').val(persona.estadoCivil),
                $('#txtcorreoU').val(persona.correo),
                $('#txtcontrasenaU').val(persona.correo),
                $('#txtestadoU').val(persona.estado ? true : false)
        }else if($('#actiona').val() == "delete"){
             $('#txtidpersonaD').val(persona.id)
             $('#txtnombreD').val(persona.correo + ' ' + persona.aPaterno + ' ' + persona.aMaterno)
        }else if($('#actiona').val() == "info"){
                $('#txtnombreI').val(persona.nombre),
                $('#txtapaternoI').val(persona.aPaterno),
                $('#txtamaternoI').val(persona.aMaterno),
                $('#txtedadI').val(persona.edad),
                $('#txtsexoI').val(persona.sexo),
                $('#txttelefonoI').val(persona.telefono),
                $('#txtdireccionI').val(persona.direccion),
                $('#txtfechanacimientoI').val(persona.fechaNacimiento),
                $('#txtestadocivilI').val(persona.estadoCivil),
                $('#txtcorreoI').val(persona.correo),
                $('#txtcontrasenaI').val(persona.correo),
                $('#txtestadoI').val(persona.estado != true ? "Inactivo" : "Activo")
        }

    })
}


(function() {
    listarPersonas()

    $('#btn-guardar1').on('click', function () {
        $('#frmRegistrar').submit();
    });
    $('#btn-actualizar').on('click', function () {
        $('#frmActualizar').submit();
    });
    $('#btn-eliminar').on('click', function () {
        $('#frmEliminar').submit();
    });

    /*
    *
    * */

    $('#frmRegistrar').submit(function (e) {
        const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
        e.preventDefault();
        var form = $(this);
        var url = form.attr('action');
        let personCreate = {
            txtnombre: $('#txtnombre').val(),
            txtapaterno: $('#txtapaterno').val(),
            txtamaterno: $('#txtamaterno').val(),
            txtedad: $('#txtedad').val(),
            txtsexo: $('#txtsexo').val(),
            txttelefono: $('#txttelefono').val(),
            txtdireccion: $('#txtdireccion').val(),
            txtfechanacimiento: $('#txtfechanacimiento').val(),
            txtestadocivil: $('#txtestadocivil').val(),
            txtcorreo: $('#txtcorreo').val(),
            txtcontrasena: $('#txtcontrasena').val(),
            txtestado: $('#txtestado').val()
        }
        $.ajax({
            type: 'POST',
            url: contextPath + url,
            data: personCreate
        }).done(function (data) {
            listarPersonas();
            $('#ModalRegistrar').modal('hide');
        }).fail(function(data) {
            console.log("Error Al registrar");
        });
    })


    $('#frmActualizar').submit(function (e) {
        const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
        e.preventDefault();
        var form = $(this);
        var url = form.attr('action');
        let personUpdate = {
            txtnombreU: $('#txtnombreU').val(),
            txtapaternoU: $('#txtapaternoU').val(),
            txtamaternoU: $('#txtamaternoU').val(),
            txtedadU: $('#txtedadU').val(),
            txtsexoU: $('#txtsexoU').val(),
            txttelefonoU: $('#txttelefonoU').val(),
            txtdireccionU: $('#txtdireccionU').val(),
            txtfechanacimientoU: $('#txtfechanacimientoU').val(),
            txtestadocivilU: $('#txtestadocivilU').val(),
            txtcorreoU: $('#txtcorreoU').val(),
            txtcontrasenaU: $('#txtcontrasenaU').val(),
            txtestadoU: $('#txtestadoU').val()
        }
        $.ajax({
            type: 'POST',
            url: contextPath + url,
            data: personUpdate
        }).done(function (data) {
            listarPersonas();
            $('#ModalActualizar').modal('hide');
        }).fail(function(data) {
            console.log("Error Al registrar");
        });
    })

    $('#frmEliminar').submit(function (e) {
        const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
        e.preventDefault();
        var form = $(this);
        var url = form.attr('action');
        let personDelete = {
            txtidpersonaD: $('#txtidpersonaD').val()
        }
        $.ajax({
            type: 'POST',
            url: contextPath + url,
            data: personDelete
        }).done(function (data) {
            listarPersonas();
            $('#ModalEliminar').modal('hide');
        }).fail(function(data) {
            console.log("Error Al registrar");
        });
    })




})();
