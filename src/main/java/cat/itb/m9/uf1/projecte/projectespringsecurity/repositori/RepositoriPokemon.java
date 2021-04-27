package cat.itb.m9.uf1.projecte.projectespringsecurity.repositori;

import cat.itb.m9.uf1.projecte.projectespringsecurity.model.entitats.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RepositoriPokemon extends JpaRepository<Pokemon, Integer> {
}
