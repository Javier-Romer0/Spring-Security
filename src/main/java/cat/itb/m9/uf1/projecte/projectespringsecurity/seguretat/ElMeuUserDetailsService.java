package cat.itb.m9.uf1.projecte.projectespringsecurity.seguretat;

import cat.itb.m9.uf1.projecte.projectespringsecurity.model.entitats.Usuari;
import cat.itb.m9.uf1.projecte.projectespringsecurity.model.serveis.ServeiUsuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ElMeuUserDetailsService implements UserDetailsService {
    @Autowired
    private ServeiUsuari serveiUsuari;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuari u= serveiUsuari.consultaPerId(s);
        User.UserBuilder builder=null;
        if(u!=null){
            builder=User.withUsername(s);
            builder.disabled(false);
            builder.password(u.getPassword());
            builder.roles(u.getRol());
        }
        else throw new UsernameNotFoundException("Usuari no trobat");
        return builder.build();
    }
}
