package com.MundoSenaiTDSN.ListaParticipantes.Service;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Pessoa;
import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Resposta;
import com.MundoSenaiTDSN.ListaParticipantes.Repository.R_Pessoa;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static M_Resposta cadastrarPessoa(String nome, String cpf, String email, String telefone, String senha, String confirmation_password){
        String mensagem = "";
        Boolean salvar = true;

        if(!senha.equals(confirmation_password) || senha == null || senha.trim().equals("")){
            mensagem += "Senha e confirmação de devem ser iguais, e a senha deve ser informada";
            salvar = false;
        }

        if(!S_ValidadorCPF.validarCPF(cpf)){
            mensagem += "<br>CPF Inválido";
            salvar = false;
        }

        if(nome == null || nome.trim().equals("")){
            mensagem += "<br>O nome precisa ser informado";
            salvar = false;
        }

        if((email == null || email.trim().equals("")) && (telefone == null || telefone.trim().equals(""))){
            mensagem += "<br>E-mail ou Telefone precisa ser informado";
            salvar = false;
        }

        if(salvar){
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(nome);
            m_pessoa.setEmail(email);
            m_pessoa.setSenha(senha);
            m_pessoa.setCpf(Long.valueOf(S_LimpaNumero.limpar(cpf)));
            telefone = S_LimpaNumero.limpar(telefone);
            if(telefone == ""){
                m_pessoa.setTelefone(null);
            } else {
                m_pessoa.setTelefone(Long.valueOf(telefone));
            }
            try {
                r_pessoa.save(m_pessoa);
                mensagem += "<br>Pessoa Cadastrada Com Sucesso!";
            } catch(DataIntegrityViolationException e){
                mensagem += e.getMessage();
                salvar = false;
            }
        }
        M_Resposta m_resposta = new M_Resposta(salvar,mensagem);

        return m_resposta;
    }
}
