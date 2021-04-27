package cat.itb.m9.uf1.projecte.projectespringsecurity.controladors;

import cat.itb.m9.uf1.projecte.projectespringsecurity.model.entitats.Pokemon;
import cat.itb.m9.uf1.projecte.projectespringsecurity.model.serveis.ServeiPokemon;
import cat.itb.m9.uf1.projecte.projectespringsecurity.model.serveis.ServeiUsuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorPokemon {

    @Autowired
    private ServeiPokemon serveiPokemon;

    @GetMapping("/pokemon")
    public String pokemonList(Model m){
        m.addAttribute("llistaPokemon", serveiPokemon.llistat());
        return "pokemonlist";
    }

    @GetMapping("/pokemon/edit/{numero}")
    public String editPokemon(@PathVariable("numero") int numero, Model m){
        Pokemon pkm = serveiPokemon.consultaPerNumero(numero);
        m.addAttribute("formPokemon", pkm);
        return "formulari";
    }

    @PostMapping("pokemon/edit/submit")
    public String editSubmit(@ModelAttribute("formPokemon") Pokemon pokemon){
        serveiPokemon.substituir(pokemon);
        return "redirect:/pokemon";
    }

    @GetMapping("pokemon/new")
    public String addPokemon(Model m){
        m.addAttribute("formPokemon", new Pokemon());
        return "formulari";
    }

    @PostMapping("pokemon/new/submit")
    public String addSubmit(@ModelAttribute("formPokemon") Pokemon pkm){
        serveiPokemon.afegir(pkm);
        return "redirect:/pokemon";
    }

    @GetMapping("pokemon/eliminar/{numero}")
    public String eliminarPokemon(@PathVariable("numero") int numero){
        serveiPokemon.eliminar(numero);
        return "redirect:/pokemon";
    }

}
