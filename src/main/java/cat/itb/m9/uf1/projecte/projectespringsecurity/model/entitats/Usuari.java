package cat.itb.m9.uf1.projecte.projectespringsecurity.model.entitats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuari {
    @Id
    String username;
    String password, rol;

    public Usuari(String username, String password) {
        this.username = username;
        this.password = password;
        this.rol = "USER";
    }
}
