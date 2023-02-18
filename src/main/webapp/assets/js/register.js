const listarPersonas = () =>{
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    $.ajax({
        type: 'GET',
        url: contextPath + '/ServletPersona?action=findAll'
    }).done(function(response){
        let listPersonas = JSON.stringify(response.ListPersonas);
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
            $('#txtidpersonaU').val(persona.id)
                $('#txtnombreU').val(persona.nombre)
                $('#txtapaternoU').val(persona.aPaterno)
                $('#txtamaternoU').val(persona.aMaterno)
                $('#txtedadU').val(persona.edad)
                $('#txtsexoU').val(persona.sexo)
                $('#txttelefonoU').val(persona.telefono)
                $('#txtdireccionU').val(persona.direccion)
                $('#txtfechanacimientoU').val(persona.fechaNacimiento)
                $('#txtestadocivilU').val(persona.estadoCivil)
                $('#txtcorreoU').val(persona.correo)
                $('#txtcontrasenaU').val(persona.contrasena)
               console.log(persona.estado)
                persona.estado == true ? $('#txtestadoU').prop('checked',true) : $('#txtestadoU').prop('checked',false)
        }
        if($('#actiona1').val() == "delete"){
             $('#txtidpersonaD').val(persona.id)
             $('#txtnombreD').val(persona.nombre + ' ' + persona.aPaterno + ' ' + persona.aMaterno)
        }
        if($('#actiona2').val() == "info"){
                $('#txtnombreI').val(persona.nombre) ,
                $('#txtapaternoI').val(persona.aPaterno),
                $('#txtamaternoI').val(persona.aMaterno),
                $('#txtedadI').val(persona.edad),
                $('#txtsexoI').val(persona.sexo),
                $('#txttelefonoI').val(persona.telefono),
                $('#txtdireccionI').val(persona.direccion),
                $('#txtfechanacimientoI').val(persona.fechaNacimiento),
                $('#txtestadocivilI').val(persona.estadoCivil),
                $('#txtcorreoI').val(persona.correo),
                $('#txtcontrasenaI').val(persona.contrasena)
                $('#txtestadoI').val(persona.estado != false ? "Activo" : "Inactivo")
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
        console.log(url)
        let personUpdate = {
            txtidpersonaU : $('#txtidpersonaU').val(),
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
            console.log("Error Al Actualizar");
        });
    })

    $('#frmEliminar').submit(function (e) {
        const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
        e.preventDefault();
        var form = $(this);
        var url = form.attr('action');
        console.log(url)
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
            console.log("Error Al Eliminar");
        });
    })




})();
