package it.prova.pokerrest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "user")
public class User {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_AUTHORITIES", joinColumns = {
            @JoinColumn(name = "USER_USERNAME", referencedColumnName = "USERNAME") }, inverseJoinColumns = {
            @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID") })
    private List<Authority> authorities;

    public User() {   }

    public User(Long id, String nome, String cognome, String username, String password, StatoUtente stato, Date dateCreated, Double creditoResiduo, Double esperienzaAccumulata, Tavolo tavolo, List<Authority> authorities) {
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
        this.authorities = authorities;
    }

    public User(String username, String password, String nome, String cognome, Date dateCreated, Double creditoResiduo, Double esperienzaAccumulata) {
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



    public boolean isAdmin() {
        for (Authority authorityItem : authorities) {
            if (authorityItem.getName().equals(AuthorityName.ROLE_ADMIN))
                return true;
        }
        return false;
    }

    public boolean isSpecialPlayer() {
        for (Authority authorityItem : authorities) {
            if (authorityItem.getName().equals(AuthorityName.ROLE_SPECIAL_PLAYER))
                return true;
        }
        return false;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (nome != null ? !nome.equals(user.nome) : user.nome != null) return false;
        if (cognome != null ? !cognome.equals(user.cognome) : user.cognome != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (stato != user.stato) return false;
        if (dateCreated != null ? !dateCreated.equals(user.dateCreated) : user.dateCreated != null) return false;
        if (creditoResiduo != null ? !creditoResiduo.equals(user.creditoResiduo) : user.creditoResiduo != null)
            return false;
        if (esperienzaAccumulata != null ? !esperienzaAccumulata.equals(user.esperienzaAccumulata) : user.esperienzaAccumulata != null)
            return false;
        if (tavolo != null ? !tavolo.equals(user.tavolo) : user.tavolo != null) return false;
        return authorities != null ? authorities.equals(user.authorities) : user.authorities == null;
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
        result = 31 * result + (authorities != null ? authorities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
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
