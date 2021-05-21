package it.prova.pokerrest.web.api;

import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.service.utente.UtenteService;
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

        

        return utenteService.listAllElements();
    }

    // gli errori di validazione vengono mostrati con 400 Bad Request ma
    // elencandoli grazie al ControllerAdvice
    @PostMapping
    public Utente createNew(@Valid @RequestBody Utente utenteInput) {
        return utenteService.inserisciNuovo(utenteInput);
    }

    @GetMapping("/{id}")
    public Utente findById(@PathVariable(value = "id", required = true) long id) {
        Utente utente = utenteService.caricaSingoloElemento(id);

        if (utente == null)
            throw new UtenteNotFoundException("Utente not found con id: " + id);

        return utente;
    }

    @PutMapping("/{id}")
    public Utente update(@Valid @RequestBody Utente registaInput, @PathVariable(required = true) Long id) {
        Utente utente = utenteService.caricaSingoloElemento(id);

        if (utente == null)
            throw new UtenteNotFoundException("Utente not found con id: " + id);

        registaInput.setId(id);
        return utenteService.aggiorna(registaInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(required = true) Long id) {
        Utente utente = utenteService.caricaSingoloElemento(id);

        if (utente == null)
            throw new UtenteNotFoundException("Utente not found con id: " + id);

        utenteService.rimuovi(utente);
    }

    @PostMapping("/search")
    public List<Utente> search(@RequestBody Utente example) {
        return utenteService.findByExample(example);
    }


}
