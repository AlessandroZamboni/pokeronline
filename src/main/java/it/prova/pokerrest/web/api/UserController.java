package it.prova.pokerrest.web.api;

import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.User;
import it.prova.pokerrest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(@RequestHeader("Authorization") String message) {

       return userService.listAllElements();
    }

    // gli errori di validazione vengono mostrati con 400 Bad Request ma
    // elencandoli grazie al ControllerAdvice
    @PostMapping
    public User createNew(@Valid @RequestBody User utenteInput, @RequestHeader("Authorization") String message) {
       return userService.inserisciNuovo(utenteInput);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id", required = true) long id, @RequestHeader("Authorization") String message) {
        return userService.caricaSingoloElemento(id);
    }

    @PutMapping("/{id}")
    public User update(@Valid @RequestBody User utenteInput, @PathVariable(required = true) Long id,  @RequestHeader("Authorization") String message) {
        return userService.aggiorna(utenteInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeStatus(@PathVariable(required = true) Long id, @RequestHeader("Authorization") String message) {
       User utenteCaricato = userService.caricaSingoloElementoEager(id);

       if(utenteCaricato.getStato().equals(StatoUtente.ATTIVO))
           userService.disabilita(utenteCaricato);
       else
           userService.abilita(utenteCaricato);
    }

    @PostMapping("/search")
    public List<User> search(@RequestBody User example,@RequestHeader("Authorization") String message) {
       return userService.findByExample(example);
    }


}
