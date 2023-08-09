package com.MundoSenaiTDSN.ListaParticipantes.Controller;

import com.MundoSenaiTDSN.ListaParticipantes.Service.S_Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class C_Pessoa {

    @GetMapping("/")
    public String landPage(){
        return "Login/login";
    }

    @PostMapping("/")
    public String loginPessoa(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha){
        return "Home/home";
    }

    @GetMapping("/cadastro")
    public String landPageCad(){
        return "Cad_Pessoa/cad_pessoa";
    }

    @PostMapping("/cadastro")
    public String createAccountPessoa(@RequestParam("nome") String nome,
                                      @RequestParam("cpf") String cpf,
                                      @RequestParam("tel") String tel,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestParam("confirmation_password") String confirmation_password){

        S_Pessoa.cadastrarPessoa(nome,cpf,email,tel,password,confirmation_password);
        return "redirect:/";
    }
}
