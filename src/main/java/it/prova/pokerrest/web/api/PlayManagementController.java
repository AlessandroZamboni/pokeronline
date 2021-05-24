package it.prova.pokerrest.web.api;

import it.prova.pokerrest.model.Tavolo;
import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.service.tavolo.TavoloService;
import it.prova.pokerrest.service.utente.UtenteService;
import it.prova.pokerrest.web.api.exception.TavoloNotFoundException;
import it.prova.pokerrest.web.api.exception.UtenteNotAuthorized;
import it.prova.pokerrest.web.api.exception.UtenteNotFoundException;
import it.prova.pokerrest.web.api.exception.UtenteNotInGameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("api/playmanagement")
public class PlayManagementController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private TavoloService tavoloService;

    @PutMapping("/addCredito")
    public Utente aggiungiCredito(@RequestBody Double credito, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);

        if(utente == null)
            throw new UtenteNotFoundException("Utente not found: "+message);

        utente.setCreditoResiduo(utente.getCreditoResiduo()+credito);
        utenteService.aggiorna(utente);

        return utente;
    }

    @PutMapping("/abbandona")
    public void leaveGame(@RequestBody Tavolo tavolo, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        tavolo = tavoloService.caricaSingoloTavoloEager(tavolo.getId());

        if(utente == null)
            throw new UtenteNotFoundException("Utente not found: "+message);

        System.out.println("Il tavolo contiene l'utente: "+tavolo.getGiocatori().contains(utente));

        if(!tavolo.getGiocatori().contains(utente))
            throw new UtenteNotInGameException("Utente not in game");
        else
            utenteService.lasciaPartita(utente);

    }

    @PostMapping("/search")
    public List<Tavolo> search(@RequestBody Tavolo example, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);

        example.setEsperienzaMin(utente.getEsperienzaAccumulata());

        return tavoloService.findByExample(example);
    }

    @PostMapping("/gioca")
    public void giocaPartita(@RequestBody Tavolo tavolo, @RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);
        tavolo = tavoloService.caricaSingoloTavoloEager(tavolo.getId());

        if(utente == null)
            throw new UtenteNotFoundException("Utente not found: "+message);

        if(tavolo == null)
            throw new TavoloNotFoundException("Tavolo not found, id:"+tavolo.getId());

        utente = gioca(tavolo,utente);

        if(utente.getCreditoResiduo() < 0) {
            utente.setCreditoResiduo(0d);
            System.out.println("Sono andato in banca rotta, credito: "+utente.getCreditoResiduo());
            utenteService.lasciaPartita(utente);
        }

    }

    public Tavolo lastGame(@RequestHeader("Authorization") String message) {
        Utente utente = utenteService.findByUsername(message);

        if(utente == null)
            throw new UtenteNotFoundException("Utente not found: "+message);

        if(utente.getTavolo() != null)
            return utente.getTavolo();
        else
            throw new TavoloNotFoundException("Tavolo not found");
    }

    private Utente gioca(Tavolo tavolo, Utente utente) {
        double segno = ThreadLocalRandom.current().nextDouble();
        Random rand = new Random();

        utente.setTavolo(tavolo);
        System.out.println("Numero casuale generato: "+segno);

        if(segno >= 0.5d)
            segno = 1d;
        else
            segno = -1d;

        System.out.println("Segno: "+segno);

        int somma = rand.nextInt(1000)+1;

        System.out.println("Somma: "+somma);

        double totale = segno*somma;

        System.out.println("Totale partita: "+totale);
        utente.setCreditoResiduo(utente.getCreditoResiduo()+totale);
        utenteService.aggiorna(utente);
        System.out.println("Credito rimanente: "+utente.getCreditoResiduo());

        return utente;
    }


}
