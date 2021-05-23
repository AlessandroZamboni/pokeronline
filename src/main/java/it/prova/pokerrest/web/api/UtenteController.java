package it.prova.pokerrest.web.api;

import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.service.utente.UtenteService;
import it.prova.pokerrest.web.api.exception.UtenteIsNotAdminException;
import it.prova.pokerrest.web.api.exception.UtenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<Utente> getAll(@RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        if(utente.isAdmin())
            return utenteService.listAllElements();
        else
            throw new UtenteIsNotAdminException("Utente is not admin, id: "+utente.getId());
    }

    // gli errori di validazione vengono mostrati con 400 Bad Request ma
    // elencandoli grazie al ControllerAdvice
    @PostMapping
    public Utente createNew(@Valid @RequestBody Utente utenteInput, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        if(utente.isAdmin())
            utenteInput = utenteService.inserisciNuovo(utenteInput);
        else
            throw new UtenteIsNotAdminException("Utente is not admin, id: "+utente.getId());

        return utenteInput;
    }

    @GetMapping("/{id}")
    public Utente findById(@PathVariable(value = "id", required = true) long id, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        Utente utenteCaricato = null;
        if(utente.isAdmin())
            utenteCaricato = utenteService.caricaSingoloElemento(id);
        else
            throw new UtenteIsNotAdminException("Utente is not admin, id: "+utente.getId());

        if (utenteCaricato == null)
            throw new UtenteNotFoundException("Utente not found, id: " + id);

        return utenteCaricato;
    }

    @PutMapping("/{id}")
    public Utente update(@Valid @RequestBody Utente utenteInput, @PathVariable(required = true) Long id,  @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        if(utente.isAdmin())
            utenteInput = utenteService.aggiorna(utenteInput);
        else
            throw new UtenteIsNotAdminException("Utente is not admin, id: "+utente.getId());

        if (utenteInput == null)
            throw new UtenteNotFoundException("Utente not found, id: " + id);

        return utenteInput;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(required = true) Long id, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        Utente utenteCaricato = null;
        if(utente.isAdmin())
            utenteCaricato = utenteService.caricaSingoloElemento(id);
        else
            throw new UtenteIsNotAdminException("Utente is not admin, id: "+utente.getId());

        if (utenteCaricato == null)
            throw new UtenteNotFoundException("Utente not found, id: " + id);

        utenteService.rimuovi(utenteCaricato);
    }

    @PostMapping("/search")
    public List<Utente> search(@RequestBody Utente example,@RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        if(utente.isAdmin())
            return utenteService.findByExample(example);
        else
            throw new UtenteIsNotAdminException("Utente is not admin, id: "+utente.getId());
    }


}
