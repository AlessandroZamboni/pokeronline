package it.prova.pokerrest.web.api;

import it.prova.pokerrest.model.Tavolo;
import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.service.tavolo.TavoloService;
import it.prova.pokerrest.service.utente.UtenteService;
import it.prova.pokerrest.web.api.exception.UtenteNotAuthorized;
import it.prova.pokerrest.web.api.exception.UtenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tavolo")
public class TavoloController {

    @Autowired
    private TavoloService tavoloService;

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<Tavolo> getAll(@RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);

        if (!utente.isAdmin() && !utente.isSpecialPlayer())
            throw new UtenteNotAuthorized("Utente not authorized, id: " + utente.getId());

        if (utente.isAdmin())
            return tavoloService.listAllElements();

        if (utente.isSpecialPlayer())
            return tavoloService.findByUtenteCreazione(utente);

        return null;
    }

    @PostMapping
    public Tavolo createNew(@Valid @RequestBody Tavolo tavoloInput, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);

        if (!utente.isAdmin() && !utente.isSpecialPlayer())
            throw new UtenteNotAuthorized("Utente not authorized, id: " + utente.getId());


        if(utente.isAdmin() || utente.isSpecialPlayer())
            tavoloInput = tavoloService.inserisciNuovo(tavoloInput);

        return tavoloInput;
    }

    @GetMapping("/{id}")
    public Tavolo findById(@PathVariable(value = "id", required = true) long id, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        Tavolo tavoloCaricato = null;
        if(utente.isAdmin())
            tavoloCaricato = tavoloService.caricaSingoloElemento(id);
        else
            throw new UtenteNotAuthorized("Utente is not admin, id: "+utente.getId());

        if (tavoloCaricato == null)
            throw new UtenteNotFoundException("Utente not authorized, id: " + id);

        return tavoloCaricato;
    }
}
