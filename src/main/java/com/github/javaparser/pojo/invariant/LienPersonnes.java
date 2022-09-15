/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;

import fr.gouv.justice.cassiopee.commons.codification.service.enumeration.LienJuridiqueEnum;
import fr.gouv.justice.cassiopee.referentiel.codification.model.LienJuridique;
import fr.gouv.justice.cassiopee.referentiel.codification.model.LienSocial;

/**
 * Les personnes liées sont créés en relation avec une personne de l'affaire.<br>
 * Cette classe désigne la personne sur laquelle est créé le lien, ainsi que les types de lien entre les 2 personnes :
 * juridique et social.<br>
 * Un personne Tiers ou Représentant Légal d'une autre de rôle premier peut en devenir suite à un jugement Civilement
 * Responsable.
 */
public class LienPersonnes implements Serializable {

    /** Identifiant de sérialisation */
    private static final long serialVersionUID = 1144172192258330392L;

    private Long id;

    private Personne personneRolePremierOuLiee;

    private Personne personneLieeOuJeune;

    /**
     * Lien social entre la personne liée et la personne de rôle premier concernée par le lien.
     */
    private LienSocial lienSocial;

    /**
     * Lien juridique entre la personne liée et la personne de rôle premier concernée par le lien.
     */
    private LienJuridique lienJuridique;

    private Date dateDuLien;

    /**
     * Dans une affaire pénale : personne civilement responsable de l'auteur, suite à la saisie d’une mesure (portée par
     * une décision de juridiction de jugement).
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
     * Retourne le lien social entre la personne liée et la personne de rôle premier concernée par le lien.
     *
     * @return le lien social entre la personne liée et la personne de rôle premier concernée par le lien.
     */
    public LienSocial getLienSocial() {
        return lienSocial;
    }

    /**
     * Positionne le lien social entre la personne liée et la personne de rôle premier concernée par le lien.
     *
     * @param lienSociale
     *            le lien social entre la personne liée et la personne de rôle premier concernée par le lien.
     */
    public void setLienSocial(LienSocial lienSociale) {
        this.lienSocial = lienSociale;
    }

    /**
     * Retourne, pour une personne de rôle "liée", la personne de rôle premier qui lui est liée.<br>
     * Dans certains cas, cette personne peut être de rôle "liée".<br>
     * Pour un jeune, retourne le jeune qui lui est socialement lié.<br>
     * Lien de droite à gauche.
     *
     * @return pour une personne de rôle "liée", la personne de rôle premier qui lui est liée.
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
     * Retourne, pour une personne de rôle premier, la personne de rôle "liée" qui lui est juridiquement liée.<br>
     * Pour un jeune, retourne le jeune qui lui est socialement lié.<br>
     * Lien de gauche à droite.
     *
     * @return pour une personne de rôle premier, la personne de rôle "liée" qui lui est juridiquement liée.
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
     * Retourne le lien juridique entre la personne liée et la personne de rôle premier concernée par le lien.
     *
     * @return le lien juridique entre la personne liée et la personne de rôle premier concernée par le lien.
     */
    public LienJuridique getLienJuridique() {
        return lienJuridique;
    }

    /**
     * Positionne le lien juridique entre la personne liée et la personne de rôle premier concernée par le lien.
     *
     * @param lienJuridique
     *            le lien juridique entre la personne liée et la personne de rôle premier concernée par le lien.
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
