package it.prova.pokerrest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "tavolo")
public class Tavolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "{esperienzaMin.notnull}")
    @Min(value = 0)
    @Column(name = "esperienza_min")
    private Double esperienzaMin;

    @NotNull(message = "{cifraMinima.notnull}")
    @Min(value = 0)
    @Column(name = "cifra_minima")
    private Double cifraMinima;

    @NotBlank(message = "{denominazione.notblank}")
    @Column(name = "denominazione")
    private String denominazione;

    @NotNull(message = "{dateCreated.notnull}")
    @Column(name = "date_created")
    private Date dateCreated;

    @NotNull(message = "{creditoResiduo.notnull}")
    @Column(name = "credito_residuo")
    private Double creditoResiduo;

    @NotNull(message = "{esperienzaAccumulata.notnull}")
    @Min(value = 0)
    @Column(name = "esperienza_accumulata")
    private Double esperienzaAccumulata;

    @JsonIgnoreProperties(value= {"tavolo"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo")
    private Set<Utente> giocatori = new HashSet<>();

    @JsonIgnoreProperties(value= {"tavolo"})
    @ManyToOne
    @JoinColumn(name="utente_creazione_id")
    private Utente utenteCreazione;

    public Tavolo() {
    }

    public Tavolo(Long id, Double esperienzaMin, Double cifraMinima, String denominazione, Date dateCreated, Double creditoResiduo, Double esperienzaAccumulata, Set<Utente> giocatori, Utente utenteCreazione) {
        this.id = id;
        this.esperienzaMin = esperienzaMin;
        this.cifraMinima = cifraMinima;
        this.denominazione = denominazione;
        this.dateCreated = dateCreated;
        this.creditoResiduo = creditoResiduo;
        this.esperienzaAccumulata = esperienzaAccumulata;
        this.giocatori = giocatori;
        this.utenteCreazione = utenteCreazione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEsperienzaMin() {
        return esperienzaMin;
    }

    public void setEsperienzaMin(Double esperienzaMin) {
        this.esperienzaMin = esperienzaMin;
    }

    public Double getCifraMinima() {
        return cifraMinima;
    }

    public void setCifraMinima(Double cifraMinima) {
        this.cifraMinima = cifraMinima;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Double getCreditoResiduo() {
        return creditoResiduo;
    }

    public void setCreditoResiduo(Double creditoResiduo) {
        this.creditoResiduo = creditoResiduo;
    }

    public Double getEsperienzaAccumulata() {
        return esperienzaAccumulata;
    }

    public void setEsperienzaAccumulata(Double esperienzaAccumulata) {
        this.esperienzaAccumulata = esperienzaAccumulata;
    }

    public Set<Utente> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<Utente> giocatori) {
        this.giocatori = giocatori;
    }

    public Utente getUtenteCreazione() {
        return utenteCreazione;
    }

    public void setUtenteCreazione(Utente utenteCreazione) {
        this.utenteCreazione = utenteCreazione;
    }

    @Override
    public String toString() {
        return "Tavolo{" +
                "id=" + id +
                ", esperienzaMin=" + esperienzaMin +
                ", cifraMinima=" + cifraMinima +
                ", denominazione='" + denominazione + '\'' +
                ", dateCreated=" + dateCreated +
                ", creditoResiduo=" + creditoResiduo +
                ", esperienzaAccumulata=" + esperienzaAccumulata +
                '}';
    }
}
