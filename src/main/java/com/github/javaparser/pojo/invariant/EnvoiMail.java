package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.io.Serializable;
import java.util.Date;

import fr.gouv.justice.cassiopee.administration.structure.model.Tgi;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;

/**
 * Représente l'historique des envois d'email
 */
public class EnvoiMail implements Serializable {

    private static final long serialVersionUID = -2596732591832512425L;

    private Long id;

    private Affaire affaire;

    private Personne personne;

    private Evenement evenement;

    private String emailDestinataire;

    private Date dateEnvoi;

    private Tgi tgi;

    public EnvoiMail() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public String getEmailDestinataire() {
        return emailDestinataire;
    }

    public void setEmailDestinataire(String emailDestinataire) {
        this.emailDestinataire = emailDestinataire;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Tgi getTgi() {
        return tgi;
    }

    public void setTgi(Tgi tgi) {
        this.tgi = tgi;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((affaire == null) ? 0 : affaire.hashCode());
        result = prime * result
                + ((dateEnvoi == null) ? 0 : dateEnvoi.hashCode());
        result = prime
                * result
                + ((emailDestinataire == null) ? 0 : emailDestinataire
                        .hashCode());
        result = prime * result
                + ((evenement == null) ? 0 : evenement.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((personne == null) ? 0 : personne.hashCode());
        result = prime * result + ((tgi == null) ? 0 : tgi.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EnvoiMail other = (EnvoiMail) obj;
        if (affaire == null) {
            if (other.affaire != null)
                return false;
        } else if (!affaire.equals(other.affaire))
            return false;
        if (dateEnvoi == null) {
            if (other.dateEnvoi != null)
                return false;
        } else if (!dateEnvoi.equals(other.dateEnvoi))
            return false;
        if (emailDestinataire == null) {
            if (other.emailDestinataire != null)
                return false;
        } else if (!emailDestinataire.equals(other.emailDestinataire))
            return false;
        if (evenement == null) {
            if (other.evenement != null)
                return false;
        } else if (!evenement.equals(other.evenement))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (personne == null) {
            if (other.personne != null)
                return false;
        } else if (!personne.equals(other.personne))
            return false;
        if (tgi == null) {
            if (other.tgi != null)
                return false;
        } else if (!tgi.equals(other.tgi))
            return false;
        return true;
    }

}
