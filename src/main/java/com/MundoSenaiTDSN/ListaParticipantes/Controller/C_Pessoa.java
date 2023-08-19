package com.MundoSenaiTDSN.ListaParticipantes.Controller;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Resposta;
import com.MundoSenaiTDSN.ListaParticipantes.Service.S_Login;
import com.MundoSenaiTDSN.ListaParticipantes.Service.S_Pessoa;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class C_Pessoa {

    @GetMapping("/")
    public String landPage(){
        return "Login/login";
    }

    @PostMapping("/")
    public String loginPessoa(@RequestParam("usuario") String usuario,
                              @RequestParam("senha") String senha,
                              HttpSession session){
        session.setAttribute("usuario",S_Login.validaLogin(usuario,senha));
        if(session.getAttribute("usuario") != null){
            return "redirect:/Home";
        } else {
            return "redirect:/";
        }

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
                                      @RequestParam("confirmation_password") String confirmation_password,
                                      RedirectAttributes redirectAttributes){

        M_Resposta m_resposta = S_Pessoa.cadastrarPessoa(nome,cpf,email,tel,password,confirmation_password);

        if(m_resposta.getStatus()){
            redirectAttributes.addFlashAttribute("mensagem",m_resposta.getMensagem());
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("mensagem",m_resposta.getMensagem());
            redirectAttributes.addFlashAttribute("nome",nome);
            redirectAttributes.addFlashAttribute("cpf",cpf);
            redirectAttributes.addFlashAttribute("tel",tel);
            redirectAttributes.addFlashAttribute("email",email);
            redirectAttributes.addFlashAttribute("password", password);
            redirectAttributes.addFlashAttribute("confimation_password",confirmation_password);
            return "redirect:/cadastro";
        }
    }
}
