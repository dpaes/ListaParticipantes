package com.MundoSenaiTDSN.ListaParticipantes.Service;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Pessoa;
import com.MundoSenaiTDSN.ListaParticipantes.Repository.R_Pessoa;
import org.springframework.stereotype.Service;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static String cadastrarPessoa(String nome, String cpf, String email, String telefone, String senha, String confirmation_password){
        String mensagem = "";
        Boolean salvar = true;

        if(!senha.equals(confirmation_password) || senha == null || senha.trim().equals("")){
            mensagem += "Senha e confirmação de devem ser iguais, e a senha deve ser informada";
            salvar = false;
        }

        if(!S_ValidadorCPF.validarCPF(cpf)){
            mensagem += "CPF Inválido";
            salvar = false;
        }

        if(nome == null || nome.trim().equals("")){
            mensagem += "O nome precisa ser informado";
            salvar = false;
        }

        if((email == null || email.trim().equals("")) && (telefone == null || telefone.trim().equals(""))){
            mensagem += "E-mail ou Telefone precisa ser informado";
            salvar = false;
        }

        if(salvar){
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(nome);
            m_pessoa.setEmail(email);
            m_pessoa.setSenha(senha);
            m_pessoa.setTelefone(Long.valueOf(telefone));
            m_pessoa.setCpf(Long.valueOf(cpf));
            r_pessoa.save(m_pessoa);
            mensagem += "Pessoa Cadastrada Com Sucesso!";
        }

        return mensagem;
    }
}
