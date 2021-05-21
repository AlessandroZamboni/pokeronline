package it.prova.pokerrest.web.api;

import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.service.utente.UtenteService;
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
    public List<Utente> getAll() {
        return utenteService.listAllElements();
    }

    // gli errori di validazione vengono mostrati con 400 Bad Request ma
    // elencandoli grazie al ControllerAdvice
    @PostMapping
    public Utente createNew(@Valid @RequestBody Utente utenteInput) {
        return utenteService.inserisciNuovo(utenteInput);
    }


}
