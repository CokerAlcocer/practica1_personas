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
        if(transaccion.datoNuevo != undefined || transaccion.datoNuevo != null){
            transaccion.datoNuevo = JSON.parse(transaccion.datoNuevo)
            if(transaccion.datoNuevo.json != undefined || transaccion.datoNuevo.json != null){
                transaccion.datoNuevo.json = JSON.parse(transaccion.datoNuevo.json)
            }else{
                transaccion.datoNuevo.json = JSON.parse(JSON.stringify(transaccion.datoNuevo))
            }

        }

     if(transaccion.datoViejo != undefined || transaccion.datoViejo != null){
         transaccion.datoViejo = JSON.parse(transaccion.datoViejo)
         if(transaccion.datoViejo.json != undefined || transaccion.datoViejo.json != null){
             transaccion.datoViejo.json = JSON.parse(transaccion.datoViejo.json)
         }else{
             transaccion.datoViejo.json = JSON.parse(JSON.stringify(transaccion.datoViejo))
         }
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