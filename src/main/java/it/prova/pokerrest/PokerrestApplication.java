package it.prova.pokerrest;

import it.prova.pokerrest.model.Ruolo;
import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.service.ruolo.RuoloService;
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

		//A DIFFERENZA DEGLI ALTRI PROGETTI CERCO SOLO PER USERNAME PERCHE' SE VADO ANCHE PER
		//PASSWORD OGNI VOLTA NE INSERISCE UNO NUOVO
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date(),0.0,0.0);
			admin.setStato(StatoUtente.ATTIVO);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			utenteServiceInstance.inserisciNuovo(admin);
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente player = new Utente("user", "user", "Antonio", "Verdi", new Date(),0.0,0.0);
			player.setStato(StatoUtente.ATTIVO);
			player.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Player User", "ROLE_PLAYER"));
			utenteServiceInstance.inserisciNuovo(player);
		}

		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente player1 = new Utente("user1", "user1", "Antonioo", "Verdii", new Date(),0.0,0.0);
			player1.setStato(StatoUtente.ATTIVO);
			player1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(player1);
		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente player2 = new Utente("user2","user2", "Antoniooo", "Verdiii", new Date(),0.0,0.0);
			player2.setStato(StatoUtente.ATTIVO);
			player2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(player2);
		}
	}
}
