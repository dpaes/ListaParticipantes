package com.MundoSenaiTDSN.ListaParticipantes.Service;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Pessoa;
import com.MundoSenaiTDSN.ListaParticipantes.Repository.R_Pessoa;
import org.springframework.stereotype.Service;

@Service
public class S_Login {
    private static R_Pessoa r_pessoa;

    public S_Login(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static M_Pessoa validaLogin(String cpf, String senha){
        Long nCpf;
        if(S_LimpaNumero.limpar(cpf).equals("")){
            nCpf = null;
        } else {
            nCpf = Long.valueOf(S_LimpaNumero.limpar(cpf));
        }
        return r_pessoa.findByCPFeSenha(nCpf,senha);
    }
}
