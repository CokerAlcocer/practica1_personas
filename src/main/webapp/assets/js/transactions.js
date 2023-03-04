const listarTrasaction = (idTra)=>{
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    console.log("Context" , contextPath,"idTransaccion",idTra)
    $.ajax({
        type: 'GET',
        url: contextPath + '/ServletPersona?action=transactionById',
        data: {txtidtransaction: idTra}
    }).done(function(response){
        console.log(response)
        let transaccion = response.UniqueTransaction;
        transaccion.datoNuevo = JSON.parse(transaccion.datoNuevo)
        transaccion.datoNuevo.json = JSON.parse(transaccion.datoNuevo.json)

     if(transaccion.datoViejo != undefined || transaccion.datoViejo != null){
         transaccion.datoViejo = JSON.parse(transaccion.datoViejo)
     }

        console.log(transaccion)
        $('#oldD').text(JSON.stringify(transaccion.datoViejo.json) )
        $('#newD').text(JSON.stringify(transaccion.datoNuevo.json))
    })
}

(function(){
    $('#showP').on('click',function (){

    })

})()