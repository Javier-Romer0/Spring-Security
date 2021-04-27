package cat.itb.m9.uf1.projecte.projectespringsecurity.controladors;

import cat.itb.m9.uf1.projecte.projectespringsecurity.model.entitats.Usuari;
import cat.itb.m9.uf1.projecte.projectespringsecurity.model.serveis.ServeiUsuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorLogin {

    @Autowired
    private ServeiUsuari serveiUsuari;

    @GetMapping("/users/list")
    public String usersList(Model m){
        m.addAttribute("llistaUsers", serveiUsuari.llistat());
        return "users";
    }

    @GetMapping("/register")
    public String register(Model m){
        m.addAttribute("registerForm", new Usuari());
        return "register";
    }

    @PostMapping("register/submit")
    public String registerSubmit(@ModelAttribute("registerForm") Usuari usuari){
        usuari.setRol("USER");
        serveiUsuari.afegir(usuari);
        return "redirect:/";
    }


}