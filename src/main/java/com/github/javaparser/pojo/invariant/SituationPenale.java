package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import fr.gouv.justice.cassiopee.referentiel.codification.model.CategoriePenale;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;

/**
 * Situation pénale de l'auteur : libre, détenu (retenu sous escorte, détenu pour autre cause). Si détenu, données
 * relatives à la détention.
 */
public class SituationPenale implements Serializable, Cloneable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -8176072465848162483L;

    /**
     * Prévoir le degré de dangerosité : utile pour les réquisitions d’extraction ou transfèrement. Cette indication
     * fournie par le procureur à l’escorte permet d’assurer la sécurité du transfert du détenu.
     */
    private Dangerosite dangerosite;

    /**
     * Numéro d’identification de la personne détenue. Exemples : 24569872, L142542.
     */
    private String numeroEcrou;

    /**
     * Quartier de détention spécifique de la maison d’arrêt ou du centre de détention.
     */
    private String quartierDetention;

    /** Date à laquelle est prévue la mise en liberté de la personne physique. */
    private Date dateLiberationPrevue;

    /**
     * La date de fraîcheur est valorisée avec la date de saisie de la situation pénale. Elle permet, notamment, de
     * retrouver la situation pénale précédente.
     */
    private Date dateFraicheur;

    /** Situation pénale d’une personne à un instant “t” de la procédure. */
    private CategoriePenale categoriePenale;

    /**
     * Etablissement pénitentiaire dans le cas où la personne physique est incarcérée. Exemple : Centre pénitentiaire de
     * Fresnes.
     */
    private ElementStructure etablissementPenitentiaire;

    private Set<Dup> dups;

    /**
     * @return categoriePenale
     */
    public CategoriePenale getCategoriePenale() {
        return categoriePenale;
    }

    /**
     * @param categoriePenale
     *            affecte categoriePenale
     */
    public void setCategoriePenale(CategoriePenale categoriePenale) {
        this.categoriePenale = categoriePenale;
    }

    /**
     * @return Retourne dangerosite
     */
    public Dangerosite getDangerosite() {
        return dangerosite;
    }

    /**
     * @param dangerosite
     *            affecte dangerosite
     */
    public void setDangerosite(Dangerosite dangerosite) {
        this.dangerosite = dangerosite;
    }

    /**
     * @return Retourne dateFraicheur
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
     * @return Retourne dateLiberationPrevue
     */
    public Date getDateLiberationPrevue() {
        return dateLiberationPrevue;
    }

    /**
     * @param dateLiberationPrevue
     *            affecte dateLiberationPrevue
     */
    public void setDateLiberationPrevue(Date dateLiberationPrevue) {
        this.dateLiberationPrevue = dateLiberationPrevue;
    }

    /**
     * @return Retourne etablissementPenitentiaire
     */
    public ElementStructure getEtablissementPenitentiaire() {
        return etablissementPenitentiaire;
    }

    /**
     * @param etablissementPenitentiaire
     *            affecte etablissementPenitentiaire
     */
    public void setEtablissementPenitentiaire(ElementStructure etablissementPenitentiaire) {
        this.etablissementPenitentiaire = etablissementPenitentiaire;
    }

    /**
     * @return Retourne numeroEcrou
     */
    public String getNumeroEcrou() {
        return numeroEcrou;
    }

    /**
     * @param numeroEcrou
     *            affecte numeroEcrou
     */
    public void setNumeroEcrou(String numeroEcrou) {
        this.numeroEcrou = numeroEcrou;
    }

    /**
     * @return Retourne quartierDetention
     */
    public String getQuartierDetention() {
        return quartierDetention;
    }

    /**
     * @param quartierDetention
     *            affecte quartierDetention
     */
    public void setQuartierDetention(String quartierDetention) {
        this.quartierDetention = quartierDetention;
    }

    /**
     * duplique une situation pénale
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        SituationPenale newSituation = (SituationPenale) super.clone();
        return newSituation;
    }

    /**
     * @return the dups
     */
    public Set<Dup> getDups() {
        return dups;
    }

    /**
     * @param dups
     *            the dups to set
     */
    public void setDups(Set<Dup> dups) {
        this.dups = dups;
    }

}
