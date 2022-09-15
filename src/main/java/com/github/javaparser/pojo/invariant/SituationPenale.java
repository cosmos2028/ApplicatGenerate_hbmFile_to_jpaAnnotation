package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import fr.gouv.justice.cassiopee.referentiel.codification.model.CategoriePenale;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;

/**
 * Situation p�nale de l'auteur : libre, d�tenu (retenu sous escorte, d�tenu pour autre cause). Si d�tenu, donn�es
 * relatives � la d�tention.
 */
public class SituationPenale implements Serializable, Cloneable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -8176072465848162483L;

    /**
     * Pr�voir le degr� de dangerosit� : utile pour les r�quisitions d�extraction ou transf�rement. Cette indication
     * fournie par le procureur � l�escorte permet d�assurer la s�curit� du transfert du d�tenu.
     */
    private Dangerosite dangerosite;

    /**
     * Num�ro d�identification de la personne d�tenue. Exemples : 24569872, L142542.
     */
    private String numeroEcrou;

    /**
     * Quartier de d�tention sp�cifique de la maison d�arr�t ou du centre de d�tention.
     */
    private String quartierDetention;

    /** Date � laquelle est pr�vue la mise en libert� de la personne physique. */
    private Date dateLiberationPrevue;

    /**
     * La date de fra�cheur est valoris�e avec la date de saisie de la situation p�nale. Elle permet, notamment, de
     * retrouver la situation p�nale pr�c�dente.
     */
    private Date dateFraicheur;

    /** Situation p�nale d�une personne � un instant �t� de la proc�dure. */
    private CategoriePenale categoriePenale;

    /**
     * Etablissement p�nitentiaire dans le cas o� la personne physique est incarc�r�e. Exemple : Centre p�nitentiaire de
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
     * duplique une situation p�nale
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
