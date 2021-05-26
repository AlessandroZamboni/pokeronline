package it.prova.pokerrest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "{nome.notblank}")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "{cognome.notblank}")
    @Column(name = "cognome")
    private String cognome;

    @NotBlank(message = "{username.notblank}")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "{password.notblank}")
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private StatoUtente stato = StatoUtente.CREATO;

    @Column(name = "date_created")
    private Date dateCreated;

    @NotNull(message = "{creditoResiduo.notnull}")
    @Column(name = "credito_residuo")
    private Double creditoResiduo;

    @Min(value = 0)
    @Column(name = "esperienza_accumulata")
    private Double esperienzaAccumulata;

    @JsonIgnoreProperties(value= {"utenteCrezione","giocatori"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tavolo_id")
    private Tavolo tavolo;

    @ManyToMany
    @JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
    private Set<Ruolo> ruoli = new HashSet<>();

    public Utente() {   }

    public Utente(Long id, String nome, String cognome, String username, String password, StatoUtente stato, Date dateCreated, Double creditoResiduo, Double esperienzaAccumulata, Tavolo tavolo, Set<Ruolo> ruoli) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.stato = stato;
        this.dateCreated = dateCreated;
        this.creditoResiduo = creditoResiduo;
        this.esperienzaAccumulata = esperienzaAccumulata;
        this.tavolo = tavolo;
        this.ruoli = ruoli;
    }

    public Utente(String username, String password, String nome, String cognome, Date dateCreated, Double creditoResiduo, Double esperienzaAccumulata) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.dateCreated = dateCreated;
        this.creditoResiduo = creditoResiduo;
        this.esperienzaAccumulata = esperienzaAccumulata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatoUtente getStato() {
        return stato;
    }

    public void setStato(StatoUtente stato) {
        this.stato = stato;
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

    public Tavolo getTavolo() {
        return tavolo;
    }

    public void setTavolo(Tavolo tavolo) {
        this.tavolo = tavolo;
    }

    public Set<Ruolo> getRuoli() {
        return ruoli;
    }

    public void setRuoli(Set<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }

    public boolean isAdmin() {
        for (Ruolo ruoloItem : ruoli) {
            if (ruoloItem.getCodice().equals(Ruolo.ROLE_ADMIN))
                return true;
        }
        return false;
    }

    public boolean isSpecialPlayer() {
        for (Ruolo ruoloItem : ruoli) {
            if (ruoloItem.getCodice().equals(Ruolo.ROLE_SPECIAL_PLAYER))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utente utente = (Utente) o;

        if (id != null ? !id.equals(utente.id) : utente.id != null) return false;
        if (nome != null ? !nome.equals(utente.nome) : utente.nome != null) return false;
        if (cognome != null ? !cognome.equals(utente.cognome) : utente.cognome != null) return false;
        if (username != null ? !username.equals(utente.username) : utente.username != null) return false;
        if (password != null ? !password.equals(utente.password) : utente.password != null) return false;
        if (stato != utente.stato) return false;
        if (dateCreated != null ? !dateCreated.equals(utente.dateCreated) : utente.dateCreated != null) return false;
        if (creditoResiduo != null ? !creditoResiduo.equals(utente.creditoResiduo) : utente.creditoResiduo != null)
            return false;
        if (esperienzaAccumulata != null ? !esperienzaAccumulata.equals(utente.esperienzaAccumulata) : utente.esperienzaAccumulata != null)
            return false;
        if (tavolo != null ? !tavolo.equals(utente.tavolo) : utente.tavolo != null) return false;
        return ruoli != null ? ruoli.equals(utente.ruoli) : utente.ruoli == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (stato != null ? stato.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (creditoResiduo != null ? creditoResiduo.hashCode() : 0);
        result = 31 * result + (esperienzaAccumulata != null ? esperienzaAccumulata.hashCode() : 0);
        result = 31 * result + (tavolo != null ? tavolo.hashCode() : 0);
        result = 31 * result + (ruoli != null ? ruoli.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", stato=" + stato +
                ", dateCreated=" + dateCreated +
                ", creditoResiduo=" + creditoResiduo +
                ", esperienzaAccumulata=" + esperienzaAccumulata +
                '}';
    }
}
