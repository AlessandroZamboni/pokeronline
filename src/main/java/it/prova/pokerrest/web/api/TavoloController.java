package it.prova.pokerrest.web.api;

import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.Tavolo;
import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.service.tavolo.TavoloService;
import it.prova.pokerrest.service.utente.UtenteService;
import it.prova.pokerrest.web.api.exception.TavoloNotFoundException;
import it.prova.pokerrest.web.api.exception.UtenteNotAuthorized;
import it.prova.pokerrest.web.api.exception.UtenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Tavolo tavoloCaricato = tavoloService.caricaSingoloElemento(id);

        if (tavoloCaricato == null)
            throw new TavoloNotFoundException("Tavolo not found, id: " + id);

        if(utente.isAdmin() || tavoloCaricato.getUtenteCreazione().equals(utente))
            return tavoloCaricato;
        else
            throw new UtenteNotAuthorized("Utente not authorized, id: " + utente.getId());

    }
    @PutMapping("/{id}")
    public Tavolo update(@Valid @RequestBody Tavolo tavoloInput, @PathVariable(required = true) Long id,  @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        tavoloInput = tavoloService.caricaSingoloElemento(tavoloInput.getId());

        if(utente == null)
            throw new UtenteNotFoundException("Utente not found: "+message);

        if(tavoloInput == null)
            throw new TavoloNotFoundException("Tavolo not found, id: " + id);

        if(utente.isAdmin() || tavoloInput.getUtenteCreazione().equals(utente)){
            tavoloInput.setId(id);
            return tavoloService.aggiorna(tavoloInput);
        } else {
            throw new UtenteNotAuthorized("Utente not authorized, id: " + utente.getId());
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(required = true) Long id, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        Tavolo tavoloCaricato = tavoloService.caricaSingoloElemento(id);

        if(tavoloCaricato == null)
            throw new TavoloNotFoundException("Tavolo not found, id: " + id);

        if(utente.isAdmin() || tavoloCaricato.getUtenteCreazione().equals(utente))
            tavoloService.rimuovi(tavoloCaricato);
        else
            throw new UtenteNotAuthorized("Utente not authorized, id: "+utente.getId());

    }


    @PostMapping("/search")
    public List<Tavolo> search(@RequestBody Tavolo example,@RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);

        if(!utente.isAdmin() && !utente.isSpecialPlayer())
            throw new UtenteNotAuthorized("Utente not authorized, id: "+utente.getId());

        if(utente.isSpecialPlayer())
            example.setUtenteCreazione(utente);

        return tavoloService.findByExample(example);
    }


}
