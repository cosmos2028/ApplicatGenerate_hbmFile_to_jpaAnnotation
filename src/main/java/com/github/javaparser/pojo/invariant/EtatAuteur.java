package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Cette classe permet d'historiser les �tats de l'auteur. <br/>
 * L'�tat de l'auteur est calcul� � partir des liens auteur-infraction (cf PERM025).
 */

public class EtatAuteur implements Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 6265935442596477320L;

    /** Identifiant de l'�tat de l'auteur */
    private Long id;

    /** Personne � laquelle est rattach�e l'EtatAuteur */
    private Personne personne;

    /**
     * Etat de la personne dans l�affaire. Exemples : mise en examen, t�moin assist�.
     */
    private RefEtatAuteur refEtatAuteur;

    /**
     * La date de fra�cheur est valoris�e avec la date de valorisation de l'�tat. Elle permet de retrouver l'�tat en
     * cours, l'�tat pr�c�dent, etc.
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