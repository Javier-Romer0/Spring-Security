package cat.itb.m9.uf1.projecte.projectespringsecurity.model.serveis;

import cat.itb.m9.uf1.projecte.projectespringsecurity.model.entitats.Pokemon;
import cat.itb.m9.uf1.projecte.projectespringsecurity.model.entitats.Usuari;
import cat.itb.m9.uf1.projecte.projectespringsecurity.repositori.RepositoriPokemon;
import cat.itb.m9.uf1.projecte.projectespringsecurity.repositori.RepositoriUsuaris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ServeiPokemon {
    @Autowired
    private RepositoriPokemon repositori;

    public Pokemon consultaPerNumero(int pokemonNum){
        return repositori.findById(pokemonNum).orElse(null);
    }

    public List<Pokemon> llistat() {
        List<Pokemon> pokemons = new ArrayList<>();
        repositori.findAll().iterator().forEachRemaining(pokemons::add);
        return pokemons;
    }

    public void afegir(Pokemon pokemon){
        repositori.save(pokemon);
    }

    public void substituir(Pokemon pkm){
        for (Pokemon p : llistat()){
            if (p.getNumero() == pkm.getNumero()){
                repositori.save(pkm);
            }
        }
    }

    public void eliminar(int num){
        Pokemon p = consultaPerNumero(num);
        repositori.delete(p);
    }

    public List<Pokemon> llistatOrdenatPerNom(){
        List<Pokemon> llistaOrdenada = llistat();
        llistaOrdenada.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o1.getNom().compareToIgnoreCase(o2.getNom());
            }
        });
        return llistaOrdenada;
    }

    public List<Pokemon> llistatOrdenatPerTipus(){
        List<Pokemon> llistaOrdenada = llistat();
        llistaOrdenada.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o1.getTipus().compareToIgnoreCase(o2.getTipus());
            }
        });
        return llistaOrdenada;
    }

    @PostConstruct
    public void init(){
        repositori.saveAll(Arrays.asList(
                new Pokemon(1, "Bulbasaur", "Planta", 318),
                new Pokemon(4, "Charmander","Fuego", 309),
                new Pokemon(9, "Blastoise", "Agua", 530))
        );
    }
}
