package cat.itb.m9.uf1.projecte.projectespringsecurity.repositori;

import cat.itb.m9.uf1.projecte.projectespringsecurity.model.entitats.Usuari;
import org.springframework.data.repository.CrudRepository;

public interface RepositoriUsuaris extends CrudRepository<Usuari, String> {
}
