

$("#submitLogin").click(function(){
    var inputUsuario = $("#usuario").val();
    var inputSenha = $("#senha").val();

    $.ajax({
        type: "POST",
        url: "/",
        data: { 
            usuario: inputUsuario,
            senha: inputSenha
        },
        success: function(data){
            $("")
        },
        error: function(){
            $("")
        }
    });
});
