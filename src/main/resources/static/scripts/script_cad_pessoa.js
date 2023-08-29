function campoVazio(campo){
    if(campo == ''){
        return true;
    } else {
        return false;
    }
}

function validaEnvio(){
    let cpf = $("#cpf").val();
    let nome = $("#nome").val();
    let email = $("#email").val();
    let tel = $("#tel").val();
    let senha = $("#senha_cad").val();
    let confirm_senha = $("#confirm_senha_cad").val();

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
    if(campoVazio(senha) && campoVazio(confirm_senha)){
        
    }
}

function validaCPF(cpf){

}