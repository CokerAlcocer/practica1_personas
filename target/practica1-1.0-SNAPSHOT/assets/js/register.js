
const listarPersonas = () =>{
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    console.log("Path",contextPath)
    $.ajax({
        type: 'GET',
        url: contextPath + '/ServletPersona?action=findAll'
    }).done(function(response){
        console.log(response.ListPersonas);
        let listPersonas = response.ListPersonas;
    })
}

window.onload = function(){
    listarPersonas()
}