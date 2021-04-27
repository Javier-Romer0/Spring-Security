package cat.itb.m9.uf1.projecte.projectespringsecurity.model.serveis;

import cat.itb.m9.uf1.projecte.projectespringsecurity.model.entitats.Usuari;
import cat.itb.m9.uf1.projecte.projectespringsecurity.repositori.RepositoriUsuaris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ServeiUsuari {
    @Autowired
    private RepositoriUsuaris repositori;

    public Usuari consultaPerId(String username){
        return repositori.findById(username).orElse(null);
    }

    public List<Usuari> llistat() {
        List<Usuari> usuaris = new ArrayList<>();
        repositori.findAll().iterator().forEachRemaining(usuaris::add);
        return usuaris;
    }

    public void afegir(Usuari user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        repositori.save(user);
    }

    @PostConstruct
    public void init(){
        repositori.saveAll(Arrays.asList(
                new Usuari("Javier", passwordEncoder().encode("1234")),
                new Usuari("Ricardo", passwordEncoder().encode("AnonimoRicardo")),
                new Usuari("Admin", passwordEncoder().encode("Admin"), "ADMIN")
        ));
    }

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
