/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;

import fr.gouv.justice.cassiopee.commons.codification.service.enumeration.LienJuridiqueEnum;
import fr.gouv.justice.cassiopee.referentiel.codification.model.LienJuridique;
import fr.gouv.justice.cassiopee.referentiel.codification.model.LienSocial;

/**
 * Les personnes li�es sont cr��s en relation avec une personne de l'affaire.<br>
 * Cette classe d�signe la personne sur laquelle est cr�� le lien, ainsi que les types de lien entre les 2 personnes :
 * juridique et social.<br>
 * Un personne Tiers ou Repr�sentant L�gal d'une autre de r�le premier peut en devenir suite � un jugement Civilement
 * Responsable.
 */
public class LienPersonnes implements Serializable {

    /** Identifiant de s�rialisation */
    private static final long serialVersionUID = 1144172192258330392L;

    private Long id;

    private Personne personneRolePremierOuLiee;

    private Personne personneLieeOuJeune;

    /**
     * Lien social entre la personne li�e et la personne de r�le premier concern�e par le lien.
     */
    private LienSocial lienSocial;

    /**
     * Lien juridique entre la personne li�e et la personne de r�le premier concern�e par le lien.
     */
    private LienJuridique lienJuridique;

    private Date dateDuLien;

    /**
     * Dans une affaire p�nale : personne civilement responsable de l'auteur, suite � la saisie d�une mesure (port�e par
     * une d�cision de juridiction de jugement).
     */
    private boolean civilementResponsable = false;

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
     * Retourne le lien social entre la personne li�e et la personne de r�le premier concern�e par le lien.
     *
     * @return le lien social entre la personne li�e et la personne de r�le premier concern�e par le lien.
     */
    public LienSocial getLienSocial() {
        return lienSocial;
    }

    /**
     * Positionne le lien social entre la personne li�e et la personne de r�le premier concern�e par le lien.
     *
     * @param lienSociale
     *            le lien social entre la personne li�e et la personne de r�le premier concern�e par le lien.
     */
    public void setLienSocial(LienSocial lienSociale) {
        this.lienSocial = lienSociale;
    }

    /**
     * Retourne, pour une personne de r�le "li�e", la personne de r�le premier qui lui est li�e.<br>
     * Dans certains cas, cette personne peut �tre de r�le "li�e".<br>
     * Pour un jeune, retourne le jeune qui lui est socialement li�.<br>
     * Lien de droite � gauche.
     *
     * @return pour une personne de r�le "li�e", la personne de r�le premier qui lui est li�e.
     */
    public Personne getPersonneRolePremierOuLiee() {
        return personneRolePremierOuLiee;
    }

    /**
     * @param personneRolePremierOuLiee
     *            affecte personne
     */
    public void setPersonneRolePremierOuLiee(Personne personneRolePremierOuLiee) {
        this.personneRolePremierOuLiee = personneRolePremierOuLiee;
    }

    /**
     * Retourne, pour une personne de r�le premier, la personne de r�le "li�e" qui lui est juridiquement li�e.<br>
     * Pour un jeune, retourne le jeune qui lui est socialement li�.<br>
     * Lien de gauche � droite.
     *
     * @return pour une personne de r�le premier, la personne de r�le "li�e" qui lui est juridiquement li�e.
     */
    public Personne getPersonneLieeOuJeune() {
        return personneLieeOuJeune;
    }

    /**
     * @param personneLieeOuJeune
     *            affecte personneLieeOuJeune
     */
    public void setPersonneLieeOuJeune(Personne personneLieeOuJeune) {
        this.personneLieeOuJeune = personneLieeOuJeune;
    }

    public boolean isCivilementResponsable() {
        return civilementResponsable;
    }

    public void setCivilementResponsable(boolean civilementResponsable) {
        this.civilementResponsable = civilementResponsable;
    }

    /**
     * Retourne le lien juridique entre la personne li�e et la personne de r�le premier concern�e par le lien.
     *
     * @return le lien juridique entre la personne li�e et la personne de r�le premier concern�e par le lien.
     */
    public LienJuridique getLienJuridique() {
        return lienJuridique;
    }

    /**
     * Positionne le lien juridique entre la personne li�e et la personne de r�le premier concern�e par le lien.
     *
     * @param lienJuridique
     *            le lien juridique entre la personne li�e et la personne de r�le premier concern�e par le lien.
     */
    public void setLienJuridique(LienJuridique lienJuridique) {
        if (lienJuridique != null && lienJuridique.getCode() != null) {
            this.lienJuridique = lienJuridique;
        } else {
            LienJuridique lienJuridiqueDefaut = new LienJuridique();
            lienJuridiqueDefaut.setCode(LienJuridiqueEnum.PAS_DE_LIEN_JURIDIQUE.getCode());
            this.lienJuridique = lienJuridiqueDefaut;
        }
    }

	/**
	 * @return the dateDuLien
	 */
	public Date getDateDuLien() {
		return dateDuLien;
	}

	/**
	 * @param dateDuLien the dateDuLien to set
	 */
	public void setDateDuLien(Date dateDuLien) {
		this.dateDuLien = dateDuLien;
	}

}
