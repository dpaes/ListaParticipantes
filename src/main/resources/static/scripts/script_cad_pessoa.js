function campoVazio(campo){
    if(campo == ''){
        return true;
    } else {
        return false;
    }
}

function validaEnvio(){
    let podeEnviar = true;
    let cpf = $("#cpf").val();
    let nome = $("#nome").val();
    let email = $("#email").val();
    let tel = $("#tel").val();
    let senha = $("#senha").val();
    let confirm_senha = $("#confirm_senha").val();

    $("#errorMessage").text("");

    if(!validarCPF(cpf)){
        podeEnviar = false;
        $("#errorMessage").append('CPF Inválido!');
    }
    if(campoVazio(nome)){
        podeEnviar = false;
        $("#errorMessage").append('O nome precisa ser preenchido!');
    }
    if(campoVazio(email) && campoVazio(tel)){
        podeEnviar = false;
        $("#errorMessage").append('O e-mail ou telefone precisa ser preenchido!');
    }
    if(campoVazio(senha) || senha != confirm_senha){
        podeEnviar = false;
        $("#errorMessage").append('A senha precisa ser preenchida e igual a confirmação da senha!');
    }

    if(podeEnviar){
        $.ajax({
            type: "POST",
            url: "/cadastro",
            data: {
                nome:nome,
                cpf:cpf,
                tel:tel,
                email:email,
                password:senha,
                confirmation_password:confirm_senha,
            },
            success: function(data){
                if(data.status == false){
                    $("#errorMessage").html(data.mensagem+<br>);
                }else{
                    $("#errorMessage").html(data.mensagem+<br>);
                }
            },
            error: function(){
                alert("Falha na comunicação com o servidor!");
            }
        })
    }
}

$("#btn_submit").click(validaEnvio);