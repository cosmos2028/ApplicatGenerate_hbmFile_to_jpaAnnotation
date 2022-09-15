package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Cette classe permet d'historiser les états de l'auteur. <br/>
 * L'état de l'auteur est calculé à partir des liens auteur-infraction (cf PERM025).
 */

public class EtatAuteur implements Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 6265935442596477320L;

    /** Identifiant de l'état de l'auteur */
    private Long id;

    /** Personne à laquelle est rattachée l'EtatAuteur */
    private Personne personne;

    /**
     * Etat de la personne dans l’affaire. Exemples : mise en examen, témoin assisté.
     */
    private RefEtatAuteur refEtatAuteur;

    /**
     * La date de fraîcheur est valorisée avec la date de valorisation de l'état. Elle permet de retrouver l'état en
     * cours, l'état précédent, etc.
     */
    private Date dateFraicheur;

    /**
     * @return retourne dateFraicheur.
     */
    public Date getDateFraicheur() {
        return dateFraicheur;
    }

    /**
     * @param dateFraicheur
     *            affecte dateFraicheur
     */
    public void setDateFraicheur(Date dateFraicheur) {
        this.dateFraicheur = dateFraicheur;
    }

    /**
     * @return retourne id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            affecte id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return retourne personne.
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @param personne
     *            affecte personne
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    /**
     * @return retourne refEtatAuteur.
     */
    public RefEtatAuteur getRefEtatAuteur() {
        return refEtatAuteur;
    }

    /**
     * @param refEtatAuteur
     *            affecte refEtatAuteur
     */
    public void setRefEtatAuteur(RefEtatAuteur refEtatAuteur) {
        this.refEtatAuteur = refEtatAuteur;
    }

}