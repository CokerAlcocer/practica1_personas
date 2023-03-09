const listarPersonas = () =>{
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    $.ajax({
        type: 'GET',
        url: contextPath + '/ServletPersona?action=findAll'
    }).done(function(response){
        let listPersonas = JSON.stringify(response.ListPersonas);
    }).fail(function(){
        Swal.fire({
            icon: 'error',
            title: 'Ooops..',
            text: 'Algo salío mal',
            timer: 1000
        })
    })
}

//Función para permitir visualizar los datos, después de cierto tiempo
function sleep (time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}
const listarPersona = (idPersona) =>{
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    $.ajax({
        type: 'GET',
        url: contextPath + '/ServletPersona?action=findById',
        data: {txtidpersona: idPersona}
    }).done(function(response){
        let persona = response.UniquePerson;
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

    $('#showP').on('click',function (){
        let type = $('#txtcontrasena').attr('type')
        if(type == 'password'){
            $('#txtcontrasena').prop('type','text')
        }else{
            $('#txtcontrasena').prop('type','password')
        }
    });
    $('#showP1').on('click',function (){
        let type = $('#txtcontrasenaU').attr('type')
        if(type == 'password'){
            $('#txtcontrasenaU').prop('type','text')
        }else{
            $('#txtcontrasenaU').prop('type','password')
        }
    });

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
            txtestado: $('#txtestado').prop('checked')
        }
        $('#ModalRegistrar').modal('hide');
        Swal.fire({
            icon: 'info',
            title: 'Registro',
            text: 'Seguro de registrar?',
            confirmButtonText: 'Aceptar',
            showCancelButton: true
        }).then((result) => {
            if(result.isConfirmed){
                $.ajax({
                    type: 'POST',
                    url: contextPath + url,
                    data: personCreate
                }).done(function (data) {

                    Swal.fire({
                        icon: 'success',
                        title: 'Registro',
                        text: 'Se registro a la persona',
                        showConfirmButton: false,
                        timer: 1000
                    })
                    sleep(1000).then(()=>{
                        listarPersonas()
                        window.location.reload()
                    })
                }).fail(function(data) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Ooops..',
                        text: 'Algo salío mal',
                        timer: 1000
                    })
                });
            }

        })


    })


    $('#frmActualizar').submit(function (e) {
        const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
        e.preventDefault();
        var form = $(this);
        var url = form.attr('action');
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
            txtestadoU: $('#txtestadoU').prop('checked')
        }
        $('#ModalActualizar').modal('hide');
        Swal.fire({
            icon: 'info',
            title: 'Modificación',
            text: 'Seguro de modificar?',
            confirmButtonText: 'Aceptar',
            showCancelButton: true
        }).then((result) => {
            if(result.isConfirmed){
                $.ajax({
                    type: 'POST',
                    url: contextPath + url,
                    data: personUpdate
                }).done(function (data) {

                    Swal.fire({
                        icon: 'success',
                        title: 'Modificar',
                        text: 'Se modifico a la persona',
                        showConfirmButton: false,
                        timer: 1000
                    })
                    sleep(1000).then(()=>{
                        listarPersonas()
                        window.location.reload()
                    })
                }).fail(function(data) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Ooops..',
                        text: 'Algo salío mal',
                        timer: 1000
                    })
                });
            }
        })


    })

    $('#frmEliminar').submit(function (e) {
        const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
        e.preventDefault();
        var form = $(this);
        var url = form.attr('action');
        let personDelete = {
            txtidpersonaD: $('#txtidpersonaD').val()
        }
        $('#ModalEliminar').modal('hide');
        Swal.fire({
            icon: 'info',
            title: 'Eliminación',
            text: 'Seguro de eliminar?',
            confirmButtonText: 'Aceptar',
            showCancelButton: true
        }).then((result) => {
            if(result.isConfirmed){
                $.ajax({
                    type: 'POST',
                    url: contextPath + url,
                    data: personDelete
                }).done(function (data) {
                 console.log(data.data)
                    Swal.fire({
                        icon: 'success',
                        title: 'Eliminar',
                        text: 'Se elimino a la persona',
                        showConfirmButton: false,
                        timer: 850
                    })
                    sleep(1000).then(()=>{
                        listarPersonas()
                        window.location.reload()
                    })
                }).fail(function(data) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Ooops..',
                        text: 'Algo salío mal',
                        timer: 1000
                    })
                });
            }
        })

    })




})();
