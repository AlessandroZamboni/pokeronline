package it.prova.pokerrest;

import it.prova.pokerrest.model.Ruolo;
import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.Tavolo;
import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.service.ruolo.RuoloService;
import it.prova.pokerrest.service.tavolo.TavoloService;
import it.prova.pokerrest.service.utente.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PokerrestApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;

	@Autowired
	private TavoloService tavoloServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(PokerrestApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Player User", "ROLE_PLAYER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Player User", "ROLE_PLAYER"));
		}
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Special Player User", "ROLE_SPECIAL_PLAYER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Special Player User", "ROLE_SPECIAL_PLAYER"));
		}

		// A DIFFERENZA DEGLI ALTRI PROGETTI CERCO SOLO PER USERNAME PERCHE' SE VADO
		// ANCHE PER
		// PASSWORD OGNI VOLTA NE INSERISCE UNO NUOVO
		Utente admin = null;
		if (utenteServiceInstance.findByUsername("admin") == null) {
			admin = new Utente("admin", "admin", "Mario", "Rossi", new Date(), 0.0, 0.0);
			admin.setStato(StatoUtente.ATTIVO);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			utenteServiceInstance.inserisciNuovo(admin);
		}

		if (utenteServiceInstance.findByUsername("player") == null) {
			Utente player = new Utente("player", "asd", "Antonio", "Verdi", new Date(), 0.0, 0.0);
			player.setStato(StatoUtente.ATTIVO);
			player.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Player User", "ROLE_PLAYER"));
			utenteServiceInstance.inserisciNuovo(player);
		}

		if (utenteServiceInstance.findByUsername("SuperPlayer") == null) {
			Utente player1 = new Utente("SuperPlayer", "asd", "Antonioo", "Verdii", new Date(), 0.0, 0.0);
			player1.setStato(StatoUtente.ATTIVO);
			player1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Special Player User", "ROLE_SPECIAL_PLAYER"));
			utenteServiceInstance.inserisciNuovo(player1);
		}

		//tavoloServiceInstance.inserisciNuovo(new Tavolo(0d,0d,"Primo Tavolo",new Date(),admin));

	}
}
